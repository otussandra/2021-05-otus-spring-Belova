package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.api.*;
import ru.otus.spring.config.QuestionnaireConfig;
import ru.otus.spring.dao.QuestionnaireDao;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.QuestionnaireResult;
import ru.otus.spring.domain.Respondent;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final QuestionnaireDao dao;
    private final QuestionnaireConfig config;
    private final QuestionnaireRespondentIntroduceService questRespIntroduceServ;
    private final ExecuteQuestionnaireService execQuestionnaireServ;
    private final QuestionnaireResultService questionResultServ;
    private final LocalizedIOService localizedIOService;

    public QuestionnaireServiceImpl(QuestionnaireDao dao,  QuestionnaireConfig config,
                                    QuestionnaireRespondentIntroduceService questRespIntroduceServ,
                                    ExecuteQuestionnaireService execQuestionnaireServ,
                                    QuestionnaireResultService questionResultServ, LocalizedIOService localizedIOService) {
        this.dao = dao;
        this.config =  config;
        this.questRespIntroduceServ = questRespIntroduceServ;
        this.execQuestionnaireServ = execQuestionnaireServ;
        this.questionResultServ = questionResultServ;
        this.localizedIOService = localizedIOService;
    }



    @Override
    public void startQuestionnaire() {
        try {
            Questionnaire questionnaire = dao.loadQuestionnaire();
            int rightAnswerCounter;
            try {
                Respondent respondent = questRespIntroduceServ.questionnaireRespondentIntroduce();
               rightAnswerCounter = execQuestionnaireServ.executeQuestionnaire(questionnaire);
                questionResultServ.showQuestionnaireResult(config.getPassingScore(), new QuestionnaireResult(respondent, rightAnswerCounter));

            } catch (RespondentIntroduceException e) {
                localizedIOService.printMessage("strings.introduceError","");
                e.printStackTrace();
            }
        } catch (QuestionnaireLoadingException e) {
            localizedIOService.printMessage("string.questionnairesourceError","");
            e.printStackTrace();
        }
    }


}
