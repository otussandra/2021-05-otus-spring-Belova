package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionnaireDao;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.QuestionnairePart;
import ru.otus.spring.domain.Respondent;
import ru.otus.spring.api.IOService;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final QuestionnaireDao dao;
    private final IOService ioService;

    public QuestionnaireServiceImpl(QuestionnaireDao dao, IOService ioService) {
        this.dao = dao;
        this.ioService = ioService;
    }

    @Override
    public void startQuestionnaire() throws IOException {
        Questionnaire questionnaire = dao.LoadQuestionnaire();
        int rightAnswerCounter;
        Respondent respondent = questionnaiRerespondentIntroduce();
        rightAnswerCounter = executeQuestionnaire(questionnaire);
        if (respondent != null) {
            respondent.seyNumberOfRightAnswer(rightAnswerCounter);
            String questionnaireResult = getQuestionnaireResult(respondent,questionnaire);
            ioService.out("Questionnaire result" + "\n" + questionnaireResult);
        }
    }

    public int executeQuestionnaire(Questionnaire qest) {
        int rightAnswerCounter;
        if (qest != null) {
            ioService.out("Start Questionnaire" + "\n");
            int youAnswerNumber = 0;
            rightAnswerCounter = 0;

            for (int i = 0; i < qest.getQuestions().size(); i++) {
                QuestionnairePart element = qest.getQuestions().get(i);
                ioService.out(element.getQuestionText() + "\n");
                List<String> answers = element.getQuestionAnswers();
                String answerString = "";
                for (int j = 0; j < answers.size(); j++) {
                    answerString = answerString + Integer.toString(j + 1) + ")" + " " + answers.get(j) + " ";
                }
                ioService.out(answerString + "\n");
                ioService.out("press 1 or 2" + "\n");
                try {
                    youAnswerNumber = ioService.readInteger();
                    if (youAnswerNumber != 1 || youAnswerNumber != 2) {
                        while (youAnswerNumber != 1 && youAnswerNumber != 2) {
                            ioService.out("press 1 or 2" + "\n");
                            youAnswerNumber = ioService.readInteger();
                        }
                    }
                } catch (InputMismatchException inputMismatchException) {
                    ioService.out("You need to press 1 or 2" + "\n" + "Start the questionnaire from the beginning");
                    return 0;
                }
                ioService.out("your answer is: " + youAnswerNumber + "\n");
                ioService.out("The right answer is: " + element.getRightAnswer() + "\n");
                if (element.getRightAnswer().equals(element.getQuestionAnswers().get((youAnswerNumber - 1)))) {
                    rightAnswerCounter++;
                }
            }
            return rightAnswerCounter;
        } else {
            return 0;
        }
    }

    public Respondent questionnaiRerespondentIntroduce() {
        try {
            ioService.out("Please enter your Last name" + "\n");
            String lastName = ioService.readString();
            ioService.out("Please enter your First name" + "\n");
            String firstName = ioService.readString();
            if (lastName.length() > 0 && firstName.length() > 0) {
                return new Respondent(lastName, firstName);
            } else {
                ioService.out("You didn't introduce yourself" + "\n");
                return null;
            }
        } catch (InputMismatchException inputMismatchException) {

        }
        return null;
    }
    public String getQuestionnaireResult(Respondent respondent,Questionnaire qest){
        if (respondent.getNumberOfRightAnswer() < qest.getPassingScore()){
            return "The number of correct answers is " + Integer.toString(respondent.getNumberOfRightAnswer())
                    + ". The passing score is " + Integer.toString( qest.getPassingScore()) + ". You didn't pass the test.";
        }
        if (respondent.getNumberOfRightAnswer() >=  qest.getPassingScore()){
            return "The number of correct answers is " + Integer.toString(respondent.getNumberOfRightAnswer())
                    + ". The passing score is " + Integer.toString( qest.getPassingScore()) + ". " +
                    respondent.getLastName() + respondent.getFirstName() + ", you have passed the test.";
        }
        return "";
    }
}
