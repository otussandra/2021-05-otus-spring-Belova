package ru.otus.spring.api;

public interface MessageService {
    String getMessage(String msgCode, String... args);
}
