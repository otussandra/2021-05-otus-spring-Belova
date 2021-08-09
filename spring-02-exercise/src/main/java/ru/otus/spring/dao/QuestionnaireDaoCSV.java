package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.api.IOService;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.QuestionnairePart;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionnaireDaoCSV implements QuestionnaireDao {
    private final String questionnaireSours;
    private final char delimiter;

    private final IOService ioService;

    public QuestionnaireDaoCSV(@Value("${questionnaireSours}") String questionnaireSours,
                               @Value("${delimiter}") char delimiter, IOService ioService) {
        this.questionnaireSours = questionnaireSours;
        this.delimiter = delimiter;
        this.ioService = ioService;
    }


    @Override
    public Questionnaire loadQuestionnaire() {
        List<QuestionnairePart> questionnaire = new ArrayList<>();
        try (InputStream is = QuestionnaireDaoCSV.class.getResourceAsStream(questionnaireSours);
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
        } catch (FileNotFoundException e) {
            ioService.out("Questionnaire sours " + questionnaireSours + " not found.");
            e.printStackTrace();
        } catch (IOException e) {
            ioService.out("Questionnaire sours file " + questionnaireSours + " read error.");
            e.printStackTrace();
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
        List<String> result = new ArrayList();
        for (int i = 0; i < pair.length; i++) {
            if (pair[i].startsWith(key)) {
                result.add(pair[i].substring(key.length() + 1));
            }
        }
        return result;
    }

}
