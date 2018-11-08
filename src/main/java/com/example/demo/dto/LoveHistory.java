package com.example.demo.dto;

public class LoveHistory {

    private String firstName;
    private String secondName;
    private int result;

    public LoveHistory(String firstName, String secondName, int result) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.result = result;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
