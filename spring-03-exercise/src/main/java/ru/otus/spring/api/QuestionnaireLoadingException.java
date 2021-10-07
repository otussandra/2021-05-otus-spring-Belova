package ru.otus.spring.api;

public class QuestionnaireLoadingException extends Throwable {
    public QuestionnaireLoadingException(String message, Exception e) {
        super(message);
    }
}
