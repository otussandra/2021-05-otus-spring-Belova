package ru.otus.spring.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;


@Service
public class IOServiceImpl implements IOService {
    private final PrintStream out;
    private final Scanner sc;
    private final String locale;

    public IOServiceImpl(@Value("#{T(java.lang.System).out}") PrintStream out,
                         @Value("#{T(java.lang.System).in}") InputStream in) {
        this.out = out;
        this.sc = new Scanner(in);
        String country = System.getProperty("user.country");
        String language =System.getProperty("user.language");
        this.locale = language + "-" +country ;

    }

    @Override
    public void out(String messagecode,MessageSource messagesource, String[] message) {
        out.println(messagesource.getMessage(messagecode, message, Locale.forLanguageTag(locale)));
    }
    public void outString(String message) {
        out.println(message);
    }
    @Override
    public String readString() {
        return sc.nextLine();
    }

    @Override
    public int readInteger() {
        return sc.nextInt();
    }
}
