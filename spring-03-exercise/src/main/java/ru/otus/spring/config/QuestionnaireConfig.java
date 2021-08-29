package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "properties")
@Component
public class QuestionnaireConfig {
    private  String questionnaireSource;
    private  char delimiter;
    private  int passingScore;

    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }

    public void setPassingScore(int passingScore) {
        this.passingScore = passingScore;
    }

    public void setQuestionnaireSource(String questionnaireSource) {
        this.questionnaireSource = questionnaireSource;
    }

    public String getQuestionnaireSource() {
        return questionnaireSource;
    }

    public char getDelimiter() {
        return delimiter;
    }

    public int getPassingScore() {
        return passingScore;
    }

    @Override
    public String toString() {
        return "QuestionnaireConfig{" +
                "questionnaireSource='" + questionnaireSource + '\'' +
                ", delimiter=" + delimiter +
                ", passingScore=" + passingScore +
                '}';
    }
}
