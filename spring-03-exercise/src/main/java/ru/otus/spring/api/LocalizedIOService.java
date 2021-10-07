package ru.otus.spring.api;

public interface LocalizedIOService {
     void printMessage(String msgCode, String... args);
     void out(String msgCode);
     String readString();
     int readInteger();
}
