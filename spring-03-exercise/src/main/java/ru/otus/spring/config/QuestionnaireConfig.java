package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;

@ConfigurationProperties(prefix = "properties")
@Component
public class QuestionnaireConfig  implements QuestionnaireLocaleConfig,QuestionnaireParamConfig,QuestionnaireResultConfig{
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
        return locale;
    }
    public  Locale getConfigLocale(){
        return Locale.forLanguageTag(this.getLocale());
    }

    public String getQuestionnaireSource() {
        String loc = Locale.forLanguageTag(locale).getLanguage();
        return questionnaireSource.replace("{locale}",loc.equals("")?"en":loc);
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
