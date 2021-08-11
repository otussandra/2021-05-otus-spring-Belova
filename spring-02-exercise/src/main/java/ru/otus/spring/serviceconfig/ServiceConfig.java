package ru.otus.spring.serviceconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.api.IOService;
import ru.otus.spring.service.ExecuteQuestionnaireService;
import ru.otus.spring.service.QuestionnaireResultService;
import ru.otus.spring.service.QuestionnarierRerespondentIntroduceService;

@Configuration
public class ServiceConfig {
    @Bean
    public QuestionnarierRerespondentIntroduceService questionnarierRerespondentIntroduceService(IOService ioService) {
        return new  QuestionnarierRerespondentIntroduceService( ioService);
    }
    @Bean
    public ExecuteQuestionnaireService executeQuestionnaireService(IOService ioService) {
        return new  ExecuteQuestionnaireService( ioService);
    }
    @Bean
    public QuestionnaireResultService questionnaireResultService(IOService ioService) {
        return new  QuestionnaireResultService( ioService);
    }
}
