package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.api.QuestionnaireLoadingException;
import ru.otus.spring.api.RespondentIntroduceException;
import ru.otus.spring.config.QuestionnaireConfig;
import ru.otus.spring.dao.QuestionnaireDao;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.QuestionnaireResult;
import ru.otus.spring.domain.Respondent;
import ru.otus.spring.api.IOService;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final QuestionnaireDao dao;
    private final IOService ioService;
    private final QuestionnaireConfig config;
    private final int passingScore;
    private final QuestionnaireRespondentIntroduceService questrespintroduceserv;
    private final ExecuteQuestionnaireService execquestionnaireserv;
    private final QuestionnaireResultService questionresultserv;

    public QuestionnaireServiceImpl(QuestionnaireDao dao, IOService ioService, QuestionnaireConfig config,
                                    QuestionnaireRespondentIntroduceService questrespintroduceserv,
                                    ExecuteQuestionnaireService execquestionnaireserv,
                                    QuestionnaireResultService questionresultserv) {
        this.dao = dao;
        this.ioService = ioService;
        this.config =  config;
        this.passingScore = config.getPassingScore();
        this.questrespintroduceserv = questrespintroduceserv;
        this.execquestionnaireserv = execquestionnaireserv;
        this.questionresultserv = questionresultserv;
    }

    @Override
    public void startQuestionnaire() {
        try {
            Questionnaire questionnaire = dao.loadQuestionnaire();
            int rightAnswerCounter;
            try {
                Respondent respondent = questrespintroduceserv.questionnaireRespondentIntroduce();
                rightAnswerCounter = execquestionnaireserv.executeQuestionnaire(questionnaire);
                questionresultserv.showQuestionnaireResult(passingScore, new QuestionnaireResult(respondent, rightAnswerCounter));

            } catch (RespondentIntroduceException e) {
                ioService.out("You didn't introduce yourself. Please try from the beginning.");
                e.printStackTrace();
            }
        } catch (QuestionnaireLoadingException e) {
            ioService.out("Have a problem with a questionnaire source may be it's  not found.");
            e.printStackTrace();
        }
    }


}
