package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import ru.otus.spring.api.IOService;
import ru.otus.spring.service.QuestionnaireService;

import java.util.Locale;

@SpringBootApplication
public class Spring03ExerciseApplication {

	public static void main(String[] args) {
		ApplicationContext ctx =SpringApplication.run(Spring03ExerciseApplication.class, args);
		MessageSource msg = ctx.getBean(MessageSource.class);
		QuestionnaireService service = ctx.getBean(QuestionnaireService.class);
		IOService ioService = ctx.getBean(IOService.class);
		service.startQuestionnaire();
	}

}
