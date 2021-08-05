package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionnaireDao;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.QuestionnairePart;

import java.util.List;
import java.util.Scanner;

public class QuestionnaireServiceImpl implements QuestionnaireService{
    private QuestionnaireDao dao;

    public QuestionnaireServiceImpl(QuestionnaireDao dao) {
               this.dao = dao;
          }
    @Override
    public void StartQuestionnaire() {
        Questionnaire questionnaire = dao.LoadQuestionnaire();
        if(questionnaire != null) {
            System.out.println("Start Questionnaire"+"\n");
            for (int i = 0; i < questionnaire.getQuestions().size(); i++) {
                QuestionnairePart element = questionnaire.getQuestions().get(i);
                System.out.println(element.getQuestionText()+"\n");
                List<String> answers = element.getQuestionAnswers();
                String answerString = "";
                for (int j = 0; j < answers.size(); j++) {
                    answerString = answerString + Integer.toString(j + 1) + ")" + " " + answers.get(j) + " ";

                }
                System.out.println(answerString + "\n");
                Scanner in = new Scanner(System.in);
                System.out.print("press 1 or 2" + "\n");
                int youAnswerNumber = in.nextInt();
                System.out.println("your answer is: " + youAnswerNumber + "\n");
                System.out.println("The right answer is: " + element.getRightAnswer() + "\n");
            }
        }
    }
}
