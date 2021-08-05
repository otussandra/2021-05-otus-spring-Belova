package ru.otus.spring.domain;

import ru.otus.spring.api.IOService;

public class Respondent {
    private final String lastName;
    private final String firstName;
    private  int numberOfRightAnswer;
    public Respondent(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.numberOfRightAnswer = 0;
    }
    public String getLastName(){

        return lastName;
    }
    public String getFirstName(){
        return firstName;
    }
    public int getNumberOfRightAnswer(){

        return numberOfRightAnswer;
    }
    public void seyNumberOfRightAnswer(int numberOfRightAnswer){

        this.numberOfRightAnswer = numberOfRightAnswer ;
    }

}
