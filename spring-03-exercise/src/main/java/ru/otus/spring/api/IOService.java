package ru.otus.spring.api;

import org.springframework.context.MessageSource;

public interface IOService {
    void out(String message);
    String readString();
    int readInteger();
}
