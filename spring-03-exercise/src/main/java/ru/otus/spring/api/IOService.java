package ru.otus.spring.api;

import org.springframework.context.MessageSource;

public interface IOService {
    void out(String messagecode, MessageSource messagesource,String[] message);
    void outString(String message);
    String readString();

    int readInteger();
}
