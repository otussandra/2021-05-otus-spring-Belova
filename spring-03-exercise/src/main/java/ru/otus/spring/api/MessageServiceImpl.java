package ru.otus.spring.api;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.QuestionnaireLocaleConfig;


@Service
public class MessageServiceImpl implements MessageService{
    private final QuestionnaireLocaleConfig config;
    private final MessageSource msg;
    public MessageServiceImpl(MessageSource msg,  QuestionnaireLocaleConfig config){
        this.config = config;
        this.msg    =   msg;

    }
    public String getMessage(String msgCode, String... args){

        return msg.getMessage(msgCode, args,  config.getConfigLocale());
    }
}
