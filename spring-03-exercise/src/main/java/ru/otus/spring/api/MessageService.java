package ru.otus.spring.api;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.QuestionnaireConfig;


@Service
public class MessageService {
    private final QuestionnaireConfig config;
    private final MessageSource msg;
    public MessageService(MessageSource msg,  QuestionnaireConfig config){
        this.config = config;
        this.msg    =   msg;

    }
    public String getMessage(String msgCode, String... args){

        return msg.getMessage(msgCode, args,  config.getConfigLocale());
    }
}
