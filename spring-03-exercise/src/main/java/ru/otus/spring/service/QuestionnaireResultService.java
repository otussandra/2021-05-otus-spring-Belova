package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.api.IOService;
import ru.otus.spring.api.MessageService;
import ru.otus.spring.domain.QuestionnaireResult;

@Service
public class QuestionnaireResultService {
    private final IOService ioService;
    private final MessageService messageService;
    public QuestionnaireResultService(IOService ioService, MessageService messageService) {
        this.ioService = ioService;

        this.messageService = messageService;
    }

    public void showQuestionnaireResult(int passingScore,QuestionnaireResult questionnaireResult) {
        String result = "";
        if (questionnaireResult.getNumberOfRightAnswer() < passingScore) {
            result = "string.badresult";
        }
        if (questionnaireResult.getNumberOfRightAnswer() >= passingScore) {
            result = "string.goodresult";
        }
        this.ioService.out(messageService.getMessage("string.result",""));
        this.ioService.out(messageService.getMessage(result,String.valueOf(questionnaireResult.getNumberOfRightAnswer()), String.valueOf(passingScore),questionnaireResult.getRespondentToString()));
    }
}
