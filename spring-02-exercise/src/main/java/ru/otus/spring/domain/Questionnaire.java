package ru.otus.spring.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.style.ToStringCreator;

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
