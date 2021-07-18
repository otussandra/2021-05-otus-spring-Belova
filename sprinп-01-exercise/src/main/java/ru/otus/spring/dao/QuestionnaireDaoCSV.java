package ru.otus.spring.dao;

import ru.otus.spring.Main;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.QuestionnairePart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionnaireDaoCSV implements QuestionnaireDao{
    private String questionnaireSours;
    private char delimiter;

    public void setQuestionnaireSours(String questionnaireSours) {
        this.questionnaireSours = questionnaireSours;
    }
    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }
    @Override
    public Questionnaire LoadQuestionnaire() {
        List<QuestionnairePart> questionnaire = new ArrayList();
         try {

             InputStream is = QuestionnaireDaoCSV.class.getResourceAsStream(questionnaireSours);
             BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
             while (true) {
                String line = null;
                try {
                    line = br.readLine();
                    if(line != null ) {
                        questionnaire.add(CreateQuestionnairePart(line));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (line == null)
                    break;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new Questionnaire(questionnaire);
    }
    QuestionnairePart CreateQuestionnairePart(String soursLine){
        String question = null;
        String rightAnswer = null;
        List<String> answers = null;
        if(FindByKeyInString("Answer",soursLine,delimiter).size()>0) {
            answers = FindByKeyInString("Answer", soursLine, delimiter);
        }
        if(FindByKeyInString("Question",soursLine,delimiter).size()>0) {
            question = FindByKeyInString("Question", soursLine, delimiter).get(0);
        }
        if(FindByKeyInString("RightAnswer",soursLine,delimiter).size()>0) {
            rightAnswer = FindByKeyInString("RightAnswer", soursLine, delimiter).get(0);
        }

        return new QuestionnairePart(question,answers,rightAnswer);
    }

    List<String> FindByKeyInString(String key,String soursLine,Character delimiter){
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
