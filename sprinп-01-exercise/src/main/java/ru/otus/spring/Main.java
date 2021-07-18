package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.QuestionnaireService;

import java.io.*;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionnaireService service = context.getBean(QuestionnaireService.class);
        service.StartQuestionnaire();
    }
}
