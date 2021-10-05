package ru.otus.spring.service;


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
    private final QuestionnaireRespondentIntroduceService questRespIntroduceServ;
    private final ExecuteQuestionnaireService execQuestionnaireServ;
    private final QuestionnaireResultService questionResultServ;
    private final LocalizedIOService localizedIOService;

    public QuestionnaireServiceImpl(QuestionnaireDao dao,
                                    QuestionnaireRespondentIntroduceService questRespIntroduceServ,
                                    ExecuteQuestionnaireService execQuestionnaireServ,
                                    QuestionnaireResultService questionResultServ, LocalizedIOService localizedIOService) {
        this.dao = dao;
        this.questRespIntroduceServ = questRespIntroduceServ;
        this.execQuestionnaireServ = execQuestionnaireServ;
        this.questionResultServ = questionResultServ;
        this.localizedIOService = localizedIOService;
    }



    @Override
    public void startQuestionnaire() {
        int rightAnswerCounter;
        try {
            Questionnaire questionnaire = dao.loadQuestionnaire();
            try {
                Respondent respondent = questRespIntroduceServ.questionnaireRespondentIntroduce();
                rightAnswerCounter = execQuestionnaireServ.executeQuestionnaire(questionnaire);
                questionResultServ.showQuestionnaireResult( new QuestionnaireResult(respondent, rightAnswerCounter));

            } catch (RespondentIntroduceException e) {
                localizedIOService.printMessage("strings.introduceError");
                e.printStackTrace();
            }
        } catch (QuestionnaireLoadingException e) {
            localizedIOService.printMessage("string.questionnairesourceError");
            e.printStackTrace();
        }
    }


}
