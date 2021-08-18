package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.api.IOService;
import ru.otus.spring.service.QuestionnaireService;

import java.io.*;

@Configuration
@ComponentScan
@PropertySource("application.properties")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(Main.class);
        QuestionnaireService service = ctx.getBean(QuestionnaireService.class);
        IOService ioService = ctx.getBean(IOService.class);
        service.startQuestionnaire();
    }

}
