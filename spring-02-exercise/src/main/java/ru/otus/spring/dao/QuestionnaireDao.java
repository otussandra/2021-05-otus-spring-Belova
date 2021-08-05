package ru.otus.spring.dao;

import ru.otus.spring.domain.Questionnaire;

import java.io.IOException;

public interface QuestionnaireDao {
    Questionnaire LoadQuestionnaire() throws IOException;
}
