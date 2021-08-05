package ru.otus.spring.domain;

import java.util.List;

public class QuestionnairePart {
    private final String questionText;
    private final List<String> questionAnswers;
    private final String rightAnswer;

    public QuestionnairePart(String questionText, List<String> questionAnswers, String rightAnswer){
        this.questionText = questionText;
        this.rightAnswer = rightAnswer;
        this.questionAnswers = questionAnswers;
    }

    public String getQuestionText(){
        return questionText;
    }
    public String getRightAnswer(){
        return rightAnswer;
    }
    public List<String> getQuestionAnswers(){
        return questionAnswers;
    }

}
