package ru.otus.spring.service;

import ru.otus.spring.api.IOService;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.QuestionnairePart;

import java.util.InputMismatchException;
import java.util.List;

public class ExecuteQuestionnaireService {
    private final IOService ioService;

    public ExecuteQuestionnaireService(IOService ioService) {
        this.ioService = ioService;
    }

    public int executeQuestionnaire(Questionnaire qest) {
        int rightAnswerCounter;
        if (qest != null) {
            ioService.out("Start Questionnaire" + "\n");
            rightAnswerCounter = 0;
            for (int i = 0; i < qest.getQuestions().size(); i++) {
                QuestionnairePart element = qest.getQuestions().get(i);
                ioService.out(element.getQuestionText() + "\n");
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
        String answerString = "";
        for (int j = 0; j < answers.size(); j++) {
            answerString = answerString + Integer.toString(j + 1) + ")" + " " + answers.get(j) + " ";
        }
        ioService.out(answerString + "\n");
    }

    private int respondentAnswerEvaluation(QuestionnairePart element) {
        int youAnswerNumber = 0;
        ioService.out("press 1 or 2" + "\n");
        try {
            youAnswerNumber = ioService.readInteger();
            if (youAnswerNumber != 1 || youAnswerNumber != 2) {
                while (youAnswerNumber != 1 && youAnswerNumber != 2) {
                    ioService.out("press 1 or 2" + "\n");
                    youAnswerNumber = ioService.readInteger();
                }
            }
        } catch (InputMismatchException inputMismatchException) {
            ioService.out("You need to press 1 or 2" + "\n" + "Start the questionnaire from the beginning");
            return 0;
        }
        ioService.out("your answer is: " + youAnswerNumber + "\n");
        ioService.out("The right answer is: " + element.getRightAnswer() + "\n");
        return youAnswerNumber;
    }
}
