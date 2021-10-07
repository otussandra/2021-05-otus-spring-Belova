package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.api.LocalizedIOService;
import ru.otus.spring.config.QuestionnaireResultConfig;
import ru.otus.spring.domain.QuestionnaireResult;

@Service
public class QuestionnaireResultServiceImpl implements QuestionnaireResultService{
    private final LocalizedIOService localizedIOService;
    private final QuestionnaireResultConfig config;
    private int passingScore;
    public QuestionnaireResultServiceImpl( LocalizedIOService localizedIOService,QuestionnaireResultConfig config) {
        this.localizedIOService = localizedIOService;
        this.config = config;
        this.passingScore = config.getPassingScore();
    }

    public void showQuestionnaireResult(QuestionnaireResult questionnaireResult) {
        String result = "";
        if (questionnaireResult.getNumberOfRightAnswer() < passingScore) {
            result = "string.badresult";
        }
        if (questionnaireResult.getNumberOfRightAnswer() >= passingScore) {
            result = "string.goodresult";
        }
        localizedIOService.printMessage("string.result");
        localizedIOService.printMessage(result,String.valueOf(questionnaireResult.getNumberOfRightAnswer()), String.valueOf(passingScore),questionnaireResult.getRespondentToString());
    }
}
