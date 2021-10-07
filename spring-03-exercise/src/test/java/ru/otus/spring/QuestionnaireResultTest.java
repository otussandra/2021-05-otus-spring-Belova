package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.domain.QuestionnaireResult;
import ru.otus.spring.domain.Respondent;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
