package ru.otus.spring.service;

import ru.otus.spring.api.RespondentIntroduceException;
import ru.otus.spring.domain.Respondent;

public interface QuestionnaireRespondentIntroduceService {
    Respondent questionnaireRespondentIntroduce() throws RespondentIntroduceException;
}
