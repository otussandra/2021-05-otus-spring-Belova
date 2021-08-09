package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
    public void startQuestionnaire() throws IOException {
        Questionnaire questionnaire = dao.loadQuestionnaire();
        int rightAnswerCounter;
        QuestionnarierRerespondentIntroduceService qris = new QuestionnarierRerespondentIntroduceService( this.ioService);
        Respondent respondent = qris.questionnarierRerespondentIntroduce();
        ExecuteQuestionnaireService eqs = new ExecuteQuestionnaireService(this.ioService);
        QuestionnaireResultService qrs =  new QuestionnaireResultService(this.ioService);
        rightAnswerCounter = eqs.executeQuestionnaire(questionnaire);
        if (respondent != null) {
            respondent.seyNumberOfRightAnswer(rightAnswerCounter);
            qrs.showQuestionnaireResult(respondent,questionnaire,this.getPassingScore());
        }
    }
    public int getPassingScore() {
        return passingScore;
    }




}
