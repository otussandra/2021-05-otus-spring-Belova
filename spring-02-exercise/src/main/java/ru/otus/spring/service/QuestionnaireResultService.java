package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.api.IOService;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.Respondent;
@Service
public class QuestionnaireResultService {
    private final IOService ioService;

    public QuestionnaireResultService(IOService ioService) {
        this.ioService = ioService;
    }

    public void showQuestionnaireResult(Respondent respondent, Questionnaire qest, int passingScore){
        String result = "";
        if (respondent.getNumberOfRightAnswer() < passingScore){
            result = "The number of correct answers is " + Integer.toString(respondent.getNumberOfRightAnswer())
                    + ". The passing score is " + Integer.toString( passingScore) + ". You didn't pass the test.";
        }
        if (respondent.getNumberOfRightAnswer() >=  passingScore){
            result =  "The number of correct answers is " + Integer.toString(respondent.getNumberOfRightAnswer())
                    + ". The passing score is " + Integer.toString( passingScore) + ". " +
                    respondent.getLastName() + respondent.getFirstName() + ", you have passed the test.";
        }
        this.ioService.out("Questionnaire result" + "\n" + result );
    }
}
