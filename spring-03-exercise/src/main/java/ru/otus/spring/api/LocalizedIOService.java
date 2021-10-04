package ru.otus.spring.api;

import org.springframework.stereotype.Service;

@Service
public class LocalizedIOService{
    private final IOService ioService;
    private final MessageService messageService;
    public LocalizedIOService(IOService ioService, MessageService messageService) {
        this.ioService = ioService;
        this.messageService = messageService;
    }
   public void printMessage(String msgCode, String... args){
        ioService.out(messageService.getMessage(msgCode, args));
    }
}
