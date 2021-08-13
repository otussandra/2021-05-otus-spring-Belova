package ru.otus.spring.service;

import ru.otus.spring.api.QuestionnaireLoadingException;

import java.io.IOException;

public interface QuestionnaireService {

    void startQuestionnaire() throws QuestionnaireLoadingException;
}
