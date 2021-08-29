package ru.otus.spring.domain;

import java.util.Objects;

public class Respondent {
    private final String lastName;
    private final String firstName;


    public Respondent(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;

    }

    public String getLastName() {

        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "Respondent{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Respondent that = (Respondent) o;
        return lastName.equals(that.lastName) && firstName.equals(that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName);
    }
}
