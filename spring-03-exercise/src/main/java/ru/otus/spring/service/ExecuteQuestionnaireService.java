package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.api.IOService;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.QuestionnairePart;

import java.util.InputMismatchException;
import java.util.List;

@Service
public class ExecuteQuestionnaireService {
    private final IOService ioService;
    private final MessageSource messagesource;
    public ExecuteQuestionnaireService(IOService ioService, MessageSource messagesource) {
        this.ioService = ioService;
        this.messagesource = messagesource;
    }

    public int executeQuestionnaire(Questionnaire qest) {
        int rightAnswerCounter;
        if (qest != null) {
            ioService.out("string.startquestionnaire",messagesource,new String[] {""});
            rightAnswerCounter = 0;
            for (int i = 0; i < qest.getQuestions().size(); i++) {
                QuestionnairePart element = qest.getQuestions().get(i);
                ioService.outString(element.getQuestionText() + "\n");
                showAnswerList(element);
                if (element.getRightAnswer().equals(element.getQuestionAnswers().get((respondentAnswerEvaluation(element) - 1)))) {
                    rightAnswerCounter++;
                }
            }
            return rightAnswerCounter;
        } else {
            return 0;
        }
    }

    private void showAnswerList(QuestionnairePart element) {
        List<String> answers = element.getQuestionAnswers();
        StringBuilder answerString = new StringBuilder();
        for (int j = 0; j < answers.size(); j++) {
            answerString.append(j + 1).append(")").append(" ").append(answers.get(j)).append(" ");
        }
        ioService.outString(answerString + "\n");
    }

    private int respondentAnswerEvaluation(QuestionnairePart element) {
        int youAnswerNumber = 0;

        ioService.out("string.asktopress",messagesource,new String[] {""});
        try {
            youAnswerNumber = ioService.readInteger();
            while (youAnswerNumber != 1 && youAnswerNumber != 2) {
                ioService.out("string.asktopress",messagesource,new String[] {""});
                youAnswerNumber = ioService.readInteger();
            }
        } catch (InputMismatchException inputMismatchException) {
            ioService.out("string.asktopressError",messagesource,new String[] {""});
            return 0;
        }
        ioService.out("string.youranswer" ,messagesource, new String[] {youAnswerNumber + "\n"});
        ioService.out("string.rightanswer",messagesource,new String[] {element.getRightAnswer() + "\n"});
        return youAnswerNumber;
    }
}
