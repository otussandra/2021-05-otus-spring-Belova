package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.api.IOService;
import ru.otus.spring.api.MessageService;
import ru.otus.spring.api.RespondentIntroduceException;
import ru.otus.spring.domain.Respondent;

import java.util.InputMismatchException;

@Service
public class QuestionnaireRespondentIntroduceService {
    private final IOService ioService;
    private final MessageService messageService;

    public QuestionnaireRespondentIntroduceService(IOService ioService, MessageService messageService) {
        this.ioService = ioService;
        this.messageService = messageService;
    }

    public Respondent questionnaireRespondentIntroduce() throws RespondentIntroduceException {
        try {
            ioService.out(messageService.getMessage("strings.Lastname",""));
            String lastName = ioService.readString();
            ioService.out(messageService.getMessage("strings.Firstname",""));
            String firstName = ioService.readString();
            if (lastName.length() > 0 && firstName.length() > 0) {
                return new Respondent(lastName, firstName);
            } else {
                throw new RespondentIntroduceException("You didn't introduce yourself." + "\n", null);
            }
        } catch (InputMismatchException e) {
            throw new RespondentIntroduceException("You didn't introduce yourself." + "\n", e);
        }

    }


}
