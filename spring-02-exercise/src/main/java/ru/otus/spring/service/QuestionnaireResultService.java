package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.api.IOService;
import ru.otus.spring.domain.QuestionnaireResult;
import ru.otus.spring.domain.Respondent;

@Service
public class QuestionnaireResultService {
    private final IOService ioService;

    public QuestionnaireResultService(IOService ioService) {
        this.ioService = ioService;

    }

    public void showQuestionnaireResult(int passingScore,QuestionnaireResult questionnaireResult) {
        String result = "";
        if (questionnaireResult.getNumberOfRightAnswer() < passingScore) {
            result = "The number of correct answers is " + questionnaireResult.getNumberOfRightAnswer()
                    + ". The passing score is " + passingScore + "." + questionnaireResult.getRespondentToString() + ", you didn't pass the test.";
        }
        if (questionnaireResult.getNumberOfRightAnswer() >= passingScore) {
            result = "The number of correct answers is " + questionnaireResult.getNumberOfRightAnswer()
                    + ". The passing score is " + passingScore + ". " +
                    questionnaireResult.getRespondentToString() + ", you have passed the test.";
        }
        this.ioService.out("Questionnaire result" + "\n" + result);
    }
}
