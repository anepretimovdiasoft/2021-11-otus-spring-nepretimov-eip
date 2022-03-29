package ru.diasoft.domain;

public class Graduate {

    private final String name;

    private int gpa;

    public Graduate(String name, int gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }


    public int getGpa() {
        return gpa;
    }
}
