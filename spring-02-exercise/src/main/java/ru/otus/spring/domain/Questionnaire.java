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
    public String getQuestionnaireResult(Respondent respondent){
        if (respondent.getNumberOfRightAnswer() < this.passingScore){
            return "The number of correct answers is " + Integer.toString(respondent.getNumberOfRightAnswer())
                    + ". The passing score is " + Integer.toString(this.passingScore) + ". You didn't pass the test.";
        }
        if (respondent.getNumberOfRightAnswer() >= this.passingScore){
            return "The number of correct answers is " + Integer.toString(respondent.getNumberOfRightAnswer())
                    + ". The passing score is " + Integer.toString(this.passingScore) + ". " +
                    respondent.getLastName() + respondent.getFirstName() + ", you have passed the test.";
        }
        return "";
    }
}
