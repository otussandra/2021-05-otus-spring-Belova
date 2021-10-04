package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.api.MessageService;
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
    private final QuestionnaireRespondentIntroduceService questRespIntroduceServ;
    private final ExecuteQuestionnaireService execQuestionnaireServ;
    private final QuestionnaireResultService questionResultServ;
    private final MessageService messageService;

    public QuestionnaireServiceImpl(QuestionnaireDao dao, IOService ioService, QuestionnaireConfig config,
                                    QuestionnaireRespondentIntroduceService questRespIntroduceServ,
                                    ExecuteQuestionnaireService execQuestionnaireServ,
                                    QuestionnaireResultService questionResultServ, MessageService messageService) {
        this.dao = dao;
        this.ioService = ioService;
        this.config =  config;
        this.questRespIntroduceServ = questRespIntroduceServ;
        this.execQuestionnaireServ = execQuestionnaireServ;
        this.questionResultServ = questionResultServ;
        this.messageService = messageService;
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
                ioService.out(messageService.getMessage("strings.introduceError",""));
                e.printStackTrace();
            }
        } catch (QuestionnaireLoadingException e) {
            ioService.out(messageService.getMessage("string.questionnairesourceError",""));
            e.printStackTrace();
        }
    }


}
