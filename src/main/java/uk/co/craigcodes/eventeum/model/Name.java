package uk.co.craigcodes.eventeum.model;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;

public class Name {

    private BigInteger id;

    private String firstName;

    private String surname;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
