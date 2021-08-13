package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.api.QuestionnaireLoadingException;
import ru.otus.spring.api.RespondentIntroduceException;
import ru.otus.spring.dao.QuestionnaireDao;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.Respondent;
import ru.otus.spring.api.IOService;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final QuestionnaireDao dao;
    private final IOService ioService;
    private final int passingScore;
    private final QuestionnaireRespondentIntroduceService qris;
    private final ExecuteQuestionnaireService eqs;
    private final QuestionnaireResultService qrs;

    public QuestionnaireServiceImpl(QuestionnaireDao dao, IOService ioService, @Value("${passingScore}") int passingScore,
                                    QuestionnaireRespondentIntroduceService qris, ExecuteQuestionnaireService eqs, QuestionnaireResultService qrs) {
        this.dao = dao;
        this.ioService = ioService;
        this.passingScore = passingScore;
        this.qris = qris;
        this.eqs = eqs;
        this.qrs = qrs;
    }

    @Override
    public void startQuestionnaire() {
        try {
            Questionnaire questionnaire = dao.loadQuestionnaire();
            int rightAnswerCounter;
            try {
                Respondent respondent = qris.questionnaireRespondentIntroduce();
                rightAnswerCounter = eqs.executeQuestionnaire(questionnaire);
                qrs.prepareQuestionnaireResult(respondent, rightAnswerCounter);
                qrs.showQuestionnaireResult(passingScore);

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
