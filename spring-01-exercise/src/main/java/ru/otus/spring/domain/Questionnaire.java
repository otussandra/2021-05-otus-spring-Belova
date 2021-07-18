package ru.otus.spring.domain;

import java.util.List;

public class Questionnaire {
    private final List<QuestionnairePart>  questions;

    public Questionnaire(List<QuestionnairePart> questions){
        this.questions = questions;
    }
    public List<QuestionnairePart> getQuestions() {
        return questions;
    }

}
