package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.api.IOService;
import ru.otus.spring.domain.Respondent;

import java.util.InputMismatchException;
@Service
public class QuestionnarierRerespondentIntroduceService {
    private final IOService ioService;

    public QuestionnarierRerespondentIntroduceService(IOService ioService) {
        this.ioService = ioService;
    }

    public Respondent questionnarierRerespondentIntroduce() {
        try {
            ioService.out("Please enter your Last name" + "\n");
            String lastName = ioService.readString();
            ioService.out("Please enter your First name" + "\n");
            String firstName = ioService.readString();
            if (lastName.length() > 0 && firstName.length() > 0) {
                return new Respondent(lastName, firstName);
            } else {
                ioService.out("You didn't introduce yourself" + "\n");
                return null;
            }
        } catch (InputMismatchException inputMismatchException) {
            ioService.out("You didn't introduce yourself" + "\n");
        }
        return null;
    }
}
