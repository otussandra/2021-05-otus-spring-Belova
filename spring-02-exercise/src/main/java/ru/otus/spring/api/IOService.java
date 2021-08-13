package ru.otus.spring.api;

public interface IOService {
    void out(String message);

    String readString();

    int readInteger();
}
