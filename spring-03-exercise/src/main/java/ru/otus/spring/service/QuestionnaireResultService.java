package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.api.IOService;
import ru.otus.spring.api.LocalizedIOService;
import ru.otus.spring.api.MessageService;
import ru.otus.spring.domain.QuestionnaireResult;

@Service
public class QuestionnaireResultService {
    private final LocalizedIOService localizedIOService;
    public QuestionnaireResultService( LocalizedIOService localizedIOService) {
        this.localizedIOService = localizedIOService;
    }

    public void showQuestionnaireResult(int passingScore,QuestionnaireResult questionnaireResult) {
        String result = "";
        if (questionnaireResult.getNumberOfRightAnswer() < passingScore) {
            result = "string.badresult";
        }
        if (questionnaireResult.getNumberOfRightAnswer() >= passingScore) {
            result = "string.goodresult";
        }
        localizedIOService.printMessage("string.result","");
        localizedIOService.printMessage(result,String.valueOf(questionnaireResult.getNumberOfRightAnswer()), String.valueOf(passingScore),questionnaireResult.getRespondentToString());
    }
}
