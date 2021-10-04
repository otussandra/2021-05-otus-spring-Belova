package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.api.IOService;
import ru.otus.spring.api.LocalizedIOService;
import ru.otus.spring.api.MessageService;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.QuestionnairePart;

import java.util.InputMismatchException;
import java.util.List;

@Service
public class ExecuteQuestionnaireService {
    private final IOService ioService;
    private final LocalizedIOService localizedIOService;
    public ExecuteQuestionnaireService(IOService ioService, LocalizedIOService localizedIOService) {
        this.ioService = ioService;
        this.localizedIOService = localizedIOService;
    }

    public int executeQuestionnaire(Questionnaire qest) {
        int rightAnswerCounter;
        if (qest != null) {
            localizedIOService.printMessage("string.startquestionnaire","");
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
        StringBuilder answerString = new StringBuilder();
        for (int j = 0; j < answers.size(); j++) {
            answerString.append(j + 1).append(")").append(" ").append(answers.get(j)).append(" ");
        }
        ioService.out(answerString + "\n");
    }

    private int respondentAnswerEvaluation(QuestionnairePart element) {
        int youAnswerNumber = 0;

        localizedIOService.printMessage("string.asktopress","");
        try {
            youAnswerNumber = ioService.readInteger();
            while (youAnswerNumber != 1 && youAnswerNumber != 2) {
                localizedIOService.printMessage("string.asktopress","");
                youAnswerNumber = ioService.readInteger();
            }
        } catch (InputMismatchException inputMismatchException) {
            localizedIOService.printMessage("string.asktopressError","");
            return 0;
        }
        localizedIOService.printMessage("string.youranswer" , youAnswerNumber+ "\n");
        localizedIOService.printMessage("string.rightanswer",element.getRightAnswer() + "\n");
        return youAnswerNumber;
    }
}
