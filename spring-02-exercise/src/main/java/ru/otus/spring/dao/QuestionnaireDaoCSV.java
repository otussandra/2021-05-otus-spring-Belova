package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.QuestionnairePart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionnaireDaoCSV implements QuestionnaireDao {
    private final String questionnaireSours;
    private final char delimiter;
    private final int passingScore;
    public QuestionnaireDaoCSV(@Value("${questionnaireSours}") String questionnaireSours,@Value("${delimiter}") char delimiter,@Value("${passingScore}") int passingScore) {
        this.questionnaireSours = questionnaireSours;
        this.delimiter = delimiter;
        this.passingScore = passingScore;
    }


    @Override
    public Questionnaire LoadQuestionnaire() throws IOException{
        List<QuestionnairePart> questionnaire = new ArrayList<>();
        InputStream is = QuestionnaireDaoCSV.class.getResourceAsStream(questionnaireSours);
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
        try {
             while (true) {
                String line = null;
                try {
                    line = br.readLine();
                    if(line != null ) {
                        questionnaire.add(createQuestionnairePart(line));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (line == null)
                    break;
            }
        } finally {
            br.close();
        }
        return new Questionnaire(questionnaire,passingScore);
    }
    private QuestionnairePart createQuestionnairePart(String soursLine){
        String question = null;
        String rightAnswer = null;
        List<String> answers = null;
        if(findByKeyInString("Answer",soursLine,delimiter).size()>0) {
            answers = findByKeyInString("Answer", soursLine, delimiter);
        }
        if(findByKeyInString("Question",soursLine,delimiter).size()>0) {
            question = findByKeyInString("Question", soursLine, delimiter).get(0);
        }
        if(findByKeyInString("RightAnswer",soursLine,delimiter).size()>0) {
            rightAnswer = findByKeyInString("RightAnswer", soursLine, delimiter).get(0);
        }

        return new QuestionnairePart(question,answers,rightAnswer);
    }

    List<String> findByKeyInString(String key,String soursLine,Character delimiter){
        String[] pair = new String[0];
        if(soursLine != null) {
            pair = soursLine.split(String.valueOf(delimiter));
        }
        List<String> result = new ArrayList();
        for (int i =0;i<pair.length;i++){
            if(pair[i].startsWith(key)){
                result.add(pair[i].substring(key.length()+1,pair[i].length()));
            }
        }
        return result ;
    }

}
