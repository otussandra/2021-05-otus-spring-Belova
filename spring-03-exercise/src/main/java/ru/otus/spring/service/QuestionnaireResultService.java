package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.api.IOService;
import ru.otus.spring.domain.QuestionnaireResult;

@Service
public class QuestionnaireResultService {
    private final IOService ioService;
    private final MessageSource messagesource;
    public QuestionnaireResultService(IOService ioService, MessageSource messagesource) {
        this.ioService = ioService;

        this.messagesource = messagesource;
    }

    public void showQuestionnaireResult(int passingScore,QuestionnaireResult questionnaireResult) {
        String result = "";
        if (questionnaireResult.getNumberOfRightAnswer() < passingScore) {
            result = "string.badresult";
        }
        if (questionnaireResult.getNumberOfRightAnswer() >= passingScore) {
            result = "string.goodresult";
        }
        this.ioService.out("string.result",messagesource,new String[] {""});
        this.ioService.out(result,messagesource,new String[] {String.valueOf(questionnaireResult.getNumberOfRightAnswer()), String.valueOf(passingScore),questionnaireResult.getRespondentToString()});
    }
}
