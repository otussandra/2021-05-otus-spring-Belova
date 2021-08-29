package ru.otus.spring.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.spring.api.IOService;
import ru.otus.spring.api.RespondentIntroduceException;
import ru.otus.spring.service.QuestionnaireRespondentIntroduceService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@DisplayName("Класс QuestionnaireRespondentIntroduceService")
public class QuestionnaireRespondentIntroduceServiceTest {
    private QuestionnaireRespondentIntroduceService service;
    private IOService ioService;

    @BeforeEach
    void setUp() {

        ioService = Mockito.mock(IOService.class);
        service = new QuestionnaireRespondentIntroduceService(ioService);
    }

    @Test
    @DisplayName("Respondent корректно создается")
    void questionnaireRespondentIntroduce() throws RespondentIntroduceException {
        Respondent expectedRespondent = new Respondent("TestLastName", "TestFirstName");
        when(ioService.readString())
                .thenReturn(expectedRespondent.getLastName())
                .thenReturn(expectedRespondent.getFirstName());

        Respondent actualRespondent = service.questionnaireRespondentIntroduce();
        assertEquals(expectedRespondent, actualRespondent);
    }
}
