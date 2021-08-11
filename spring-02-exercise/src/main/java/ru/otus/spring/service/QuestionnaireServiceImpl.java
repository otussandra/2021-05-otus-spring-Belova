package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.otus.spring.Main;
import ru.otus.spring.dao.QuestionnaireDao;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.QuestionnairePart;
import ru.otus.spring.domain.Respondent;
import ru.otus.spring.api.IOService;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final QuestionnaireDao dao;
    private final IOService ioService;
    private final int passingScore;

    public QuestionnaireServiceImpl(QuestionnaireDao dao, IOService ioService, @Value("${passingScore}") int passingScore) {
        this.dao = dao;
        this.ioService = ioService;
        this.passingScore = passingScore;
    }

    @Override
    public void startQuestionnaire() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(Main.class);
        QuestionnarierRerespondentIntroduceService qris = ctx.getBean(QuestionnarierRerespondentIntroduceService.class);
        ExecuteQuestionnaireService eqs = ctx.getBean(ExecuteQuestionnaireService.class);
        QuestionnaireResultService qrs = ctx.getBean(QuestionnaireResultService.class);
        Questionnaire questionnaire = dao.loadQuestionnaire();
        int rightAnswerCounter;
        Respondent respondent = qris.questionnarierRerespondentIntroduce();
        rightAnswerCounter = eqs.executeQuestionnaire(questionnaire);
        if (respondent != null) {
            respondent.seyNumberOfRightAnswer(rightAnswerCounter);
            qrs.showQuestionnaireResult(respondent, questionnaire, passingScore);
        }
    }


}
