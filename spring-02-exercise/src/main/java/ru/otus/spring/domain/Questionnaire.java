package ru.otus.spring.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.style.ToStringCreator;

import java.util.List;

public class Questionnaire {
    private final List<QuestionnairePart>  questions;
    private final int passingScore;

    public Questionnaire(List<QuestionnairePart> questions, int passingScore){
        this.questions = questions;
        this.passingScore = passingScore;
    }
    public List<QuestionnairePart> getQuestions() {
        return questions;
    }

    public int getPassingScore() {
        return passingScore;
    }
}
