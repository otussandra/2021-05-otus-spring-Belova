package ru.otus.spring.service;


import org.springframework.stereotype.Service;

import ru.otus.spring.api.LocalizedIOService;

import ru.otus.spring.api.RespondentIntroduceException;
import ru.otus.spring.domain.Respondent;

import java.util.InputMismatchException;

@Service
public class QuestionnaireRespondentIntroduceServiceImpl implements QuestionnaireRespondentIntroduceService {
    private final LocalizedIOService localizedIOService;

    public QuestionnaireRespondentIntroduceServiceImpl(LocalizedIOService localizedIOService) {
        this.localizedIOService = localizedIOService;
    }

    public Respondent questionnaireRespondentIntroduce() throws RespondentIntroduceException {
        try {
            localizedIOService.printMessage("strings.Lastname");
            String lastName = localizedIOService.readString();
            localizedIOService.printMessage("strings.Firstname");
            String firstName = localizedIOService.readString();
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
