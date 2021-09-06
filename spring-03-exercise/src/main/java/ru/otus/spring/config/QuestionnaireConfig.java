package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "properties")
@Component
public class QuestionnaireConfig {
    private  String questionnaireSource;
    private  char delimiter;
    private  int passingScore;
    private   String locale;

    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }

    public void setPassingScore(int passingScore) {
        this.passingScore = passingScore;
    }

    public void setQuestionnaireSource(String questionnaireSource) {
        this.questionnaireSource = questionnaireSource;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        String country = System.getProperty("user.country");
        String language =System.getProperty("user.language");
        locale = country+"_"+language;
        return locale;
    }

    public String getQuestionnaireSource() {
        String loc = this.getLocale();
        String ss = questionnaireSource.replace("{locale}",loc);
        return questionnaireSource.replace("{locale}",loc);
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
