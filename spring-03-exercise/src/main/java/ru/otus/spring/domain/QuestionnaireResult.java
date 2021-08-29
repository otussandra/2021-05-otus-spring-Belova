package ru.otus.spring.domain;

public class QuestionnaireResult {
    private final Respondent respondent;
    private final int numberOfRightAnswer;

    public QuestionnaireResult(Respondent respondent, int numberOfRightAnswer) {
        this.respondent = respondent;
        this.numberOfRightAnswer = numberOfRightAnswer;
    }

    public int getNumberOfRightAnswer() {

        return numberOfRightAnswer;
    }

    public String getRespondentToString() {

        return respondent.getFirstName() + " " + respondent.getLastName();
    }


}
