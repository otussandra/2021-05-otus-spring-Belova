package ru.otus.spring.domain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@DisplayName("Класс uestionnairePart")
public class QuestionnairePartTest {
    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        List answerList = new ArrayList();
        String element1 = "first answer";
        String element2 = "second answer";
        answerList.add(element1);
        answerList.add(element2);
        QuestionnairePart questionnairePart = new QuestionnairePart("Qestion Text",answerList ,"Right");
        assertEquals("Qestion Text", questionnairePart.getQuestionText());
        assertEquals("Right", questionnairePart.getRightAnswer());
    }
}
