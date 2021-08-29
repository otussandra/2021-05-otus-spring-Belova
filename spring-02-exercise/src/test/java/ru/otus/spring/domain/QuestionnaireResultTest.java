package ru.otus.spring.domain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
@DisplayName("Класс QuestionnaireResult")
public class QuestionnaireResultTest {

    @Test
    @DisplayName("QuestionnaireResult корректно создается")
    void questionnaireResult() {
        Respondent expectedRespondent = new Respondent("TestLastName", "TestFirstName");
        int numberOfRightAnswer = 3;
        QuestionnaireResult questionnaireResult = new QuestionnaireResult(expectedRespondent, numberOfRightAnswer);
        assertEquals("TestFirstName TestLastName", questionnaireResult.getRespondentToString());
        assertEquals(3, questionnaireResult.getNumberOfRightAnswer());
    }
}
