package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionnaireDao;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.QuestionnairePart;
import ru.otus.spring.domain.Respondent;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService{
    private QuestionnaireDao dao;

    public QuestionnaireServiceImpl(QuestionnaireDao dao) {
               this.dao = dao;
          }
    @Override
    public void StartQuestionnaire() {
        Questionnaire questionnaire = dao.LoadQuestionnaire();
        int rightAnswerCounter;
        if(questionnaire != null) {
            Scanner in = new Scanner(System.in);
            System.out.println("Start Questionnaire"+"\n");
            System.out.println("Please enter your Last name"+"\n");
            String lastName  = in.nextLine();
            System.out.println("Please enter your First name"+"\n");
            String firstName =  in.nextLine();
            int youAnswerNumber = 0;
            if(lastName.length()>0 && firstName.length()>0){
                Respondent respondent = new Respondent(lastName,firstName);
                rightAnswerCounter =0;
                for (int i = 0; i < questionnaire.getQuestions().size(); i++) {
                    QuestionnairePart element = questionnaire.getQuestions().get(i);
                    System.out.println(element.getQuestionText()+"\n");
                    List<String> answers = element.getQuestionAnswers();
                    String answerString = "";
                    for (int j = 0; j < answers.size(); j++) {
                    answerString = answerString + Integer.toString(j + 1) + ")" + " " + answers.get(j) + " ";

                    }
                    System.out.println(answerString + "\n");

                    System.out.print("press 1 or 2" + "\n");
                    try {
                        youAnswerNumber = in.nextInt();
                        if(youAnswerNumber != 1 || youAnswerNumber !=2){
                            while(youAnswerNumber != 1 && youAnswerNumber !=2){
                                System.out.print("press 1 or 2" + "\n");
                                youAnswerNumber = in.nextInt();
                            }
                        }
                    }catch (InputMismatchException inputMismatchException){
                        System.out.print("You need to press 1 or 2" + "\n" + "Start the questionnaire from the beginning");
                        return;
                    }

                    System.out.println("your answer is: " + youAnswerNumber + "\n");
                    System.out.println("The right answer is: " + element.getRightAnswer() + "\n");
                    if(element.getRightAnswer().equals(element.getQuestionAnswers().get((youAnswerNumber-1)))){
                        rightAnswerCounter++;
                    }
                }
                respondent.seyNumberOfRightAnswer(rightAnswerCounter);
                String questionnaireResult = questionnaire.getQuestionnaireResult(respondent);
                System.out.println("Questionnaire result" +  "\n" + questionnaireResult);
            }else{
                System.out.println("You didn't introduce yourself"+ "\n");
                return;
            }

        }
    }
}
