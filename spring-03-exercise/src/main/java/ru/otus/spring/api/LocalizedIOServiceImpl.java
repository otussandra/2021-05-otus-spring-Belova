package ru.otus.spring.api;

import org.springframework.stereotype.Service;

@Service
public class LocalizedIOServiceImpl implements LocalizedIOService{
    private final IOService ioService;
    private final MessageService messageService;

    public LocalizedIOServiceImpl(IOService ioService, MessageService messageService) {
        this.ioService = ioService;
        this.messageService = messageService;
    }

    public void printMessage(String msgCode, String... args) {
        ioService.out(messageService.getMessage(msgCode, args));
    }

    public void out(String msgCode) {
        ioService.out(msgCode);
    }

    public String readString() {
        return ioService.readString();
    }

    public int readInteger() {
        return ioService.readInteger();
    }
}
