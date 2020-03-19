package com.example.demo.model;

public class Retake {
    private String subject_name;
    private int count;

    public Retake(String subject_name, int count)
    {
        this.subject_name = subject_name;
        this.count = count;
    }

    public Retake()
    {
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubjectName(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}