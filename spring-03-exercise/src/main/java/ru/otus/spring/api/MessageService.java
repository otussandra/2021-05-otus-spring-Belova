package ru.otus.spring.api;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.QuestionnaireConfig;

import java.util.Locale;

@Service
public class MessageService {
    private final Locale locale;
    private final MessageSource msg;
    public MessageService(MessageSource msg,  QuestionnaireConfig config){
        this.locale = config.getLocale();
        this.msg    =   msg;

    }
    public String getMessage(String msgCode, String... args){

        return msg.getMessage(msgCode, args, locale);
    }
}
