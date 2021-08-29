package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.api.IOService;
import ru.otus.spring.api.QuestionnaireLoadingException;
import ru.otus.spring.api.RespondentIntroduceException;
import ru.otus.spring.domain.Respondent;

import java.util.InputMismatchException;
import java.util.Objects;

@Service
public class QuestionnaireRespondentIntroduceService {
    private final IOService ioService;

    public QuestionnaireRespondentIntroduceService(IOService ioService) {
        this.ioService = ioService;
    }

    public Respondent questionnaireRespondentIntroduce() throws RespondentIntroduceException {
        try {
            ioService.out("Please enter your Last name" + "\n");
            String lastName = ioService.readString();
            ioService.out("Please enter your First name" + "\n");
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
