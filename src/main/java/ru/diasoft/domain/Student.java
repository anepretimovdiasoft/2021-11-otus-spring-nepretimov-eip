package ru.diasoft.domain;

import java.util.List;

public class Student {

    private final String name;

    private String group;

    private List<Integer> markBook;

    public Student(String name, String group) {
        this.name = name;
        this.group = group;
    }

    public Student(String name, String group, List<Integer> markBook) {
        this.name = name;
        this.group = group;
        this.markBook = markBook;
    }

    public void setMarkBook(List<Integer> markBook) {
        this.markBook = markBook;
    }

    public List<Integer> getMarkBook() {
        return markBook;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }
}
