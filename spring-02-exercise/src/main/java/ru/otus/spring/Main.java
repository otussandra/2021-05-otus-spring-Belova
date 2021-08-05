package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.service.QuestionnaireService;

import java.io.*;
@Configuration
@ComponentScan
@PropertySource("application.properties")
public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(Main.class);

        QuestionnaireService service = ctx.getBean(QuestionnaireService.class);

        service.startQuestionnaire();
    }
}
