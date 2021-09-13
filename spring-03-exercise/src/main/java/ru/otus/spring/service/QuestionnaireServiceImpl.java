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
    private final QuestionnaireRespondentIntroduceService questrespintroduceserv;
    private final ExecuteQuestionnaireService execquestionnaireserv;
    private final QuestionnaireResultService questionresultserv;
    private final MessageService messageService;

    public QuestionnaireServiceImpl(QuestionnaireDao dao, IOService ioService, QuestionnaireConfig config,
                                    QuestionnaireRespondentIntroduceService questrespintroduceserv,
                                    ExecuteQuestionnaireService execquestionnaireserv,
                                    QuestionnaireResultService questionresultserv, MessageService messageService) {
        this.dao = dao;
        this.ioService = ioService;
        this.config =  config;
        this.questrespintroduceserv = questrespintroduceserv;
        this.execquestionnaireserv = execquestionnaireserv;
        this.questionresultserv = questionresultserv;
        this.messageService = messageService;
    }



    @Override
    public void startQuestionnaire() {
        try {
            Questionnaire questionnaire = dao.loadQuestionnaire();
            int rightAnswerCounter;
            try {
                Respondent respondent = questrespintroduceserv.questionnaireRespondentIntroduce();
               rightAnswerCounter = execquestionnaireserv.executeQuestionnaire(questionnaire);
                questionresultserv.showQuestionnaireResult(config.getPassingScore(), new QuestionnaireResult(respondent, rightAnswerCounter));

            } catch (RespondentIntroduceException e) {
                ioService.out(messageService.getMessage("strings.introduceError",new String[] {""}));
                e.printStackTrace();
            }
        } catch (QuestionnaireLoadingException e) {
            ioService.out(messageService.getMessage("string.questionnairesourceError",new String[] {""}));
            e.printStackTrace();
        }
    }


}
