package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.api.IOService;
import ru.otus.spring.api.RespondentIntroduceException;
import ru.otus.spring.domain.Respondent;

import java.util.InputMismatchException;

@Service
public class QuestionnaireRespondentIntroduceService {
    private final IOService ioService;
    private final MessageSource messagesource;

    public QuestionnaireRespondentIntroduceService(IOService ioService, MessageSource messagesource) {
        this.ioService = ioService;
        this.messagesource = messagesource;
    }

    public Respondent questionnaireRespondentIntroduce() throws RespondentIntroduceException {
        try {
            ioService.out("strings.Lastname",messagesource,new String[] {""});
            String lastName = ioService.readString();
            ioService.out("strings.Firstname",messagesource,new String[] {""});
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
