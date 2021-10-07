package ru.otus.spring.dao;

import ru.otus.spring.api.QuestionnaireLoadingException;
import ru.otus.spring.domain.Questionnaire;

public interface QuestionnaireDao {
    Questionnaire loadQuestionnaire() throws QuestionnaireLoadingException;
}
