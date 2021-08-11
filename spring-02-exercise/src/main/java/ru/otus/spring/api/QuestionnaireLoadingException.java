package ru.otus.spring.api;

import java.io.FileNotFoundException;
import java.io.IOException;

public class QuestionnaireLoadingException extends FileNotFoundException {
    public QuestionnaireLoadingException(String message,Exception e) {
        super(message);
        e.printStackTrace();
    }
}
