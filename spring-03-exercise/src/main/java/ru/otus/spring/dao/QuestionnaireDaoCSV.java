package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.spring.api.QuestionnaireLoadingException;
import ru.otus.spring.config.QuestionnaireConfig;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.QuestionnairePart;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionnaireDaoCSV implements QuestionnaireDao {
    private final String questionnaireSource;
    private final char delimiter;
    private final QuestionnaireConfig config;

    public QuestionnaireDaoCSV(QuestionnaireConfig config) {
        this.config = config;
        this.questionnaireSource = config.getQuestionnaireSource();
        this.delimiter = config.getDelimiter();
    }


    @Override
    public Questionnaire loadQuestionnaire() throws QuestionnaireLoadingException {
        List<QuestionnairePart> questionnaire = new ArrayList<>();
        try (InputStream is = QuestionnaireDaoCSV.class.getResourceAsStream(questionnaireSource);
             BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));) {
            while (true) {
                String line = null;
                line = br.readLine();
                if (line != null) {
                    questionnaire.add(createQuestionnairePart(line));
                }
                if (line == null)
                    break;
            }
        } catch (IOException e) {
            throw new QuestionnaireLoadingException("Questionnaire sours " + questionnaireSource + " not found.", e);
        }
        return new Questionnaire(questionnaire);
    }

    private QuestionnairePart createQuestionnairePart(String soursLine) {
        String question = null;
        String rightAnswer = null;
        List<String> answers = null;
        if (findByKeyInString("Answer", soursLine, delimiter).size() > 0) {
            answers = findByKeyInString("Answer", soursLine, delimiter);
        }
        if (findByKeyInString("Question", soursLine, delimiter).size() > 0) {
            question = findByKeyInString("Question", soursLine, delimiter).get(0);
        }
        if (findByKeyInString("RightAnswer", soursLine, delimiter).size() > 0) {
            rightAnswer = findByKeyInString("RightAnswer", soursLine, delimiter).get(0);
        }

        return new QuestionnairePart(question, answers, rightAnswer);
    }

    List<String> findByKeyInString(String key, String soursLine, Character delimiter) {
        String[] pair = new String[0];
        if (soursLine != null) {
            pair = soursLine.split(String.valueOf(delimiter));
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < pair.length; i++) {
            if (pair[i].startsWith(key)) {
                result.add(pair[i].substring(key.length() + 1));
            }
        }
        return result;
    }

}
