package com.example.demo.model;

public class StudentMark {
    private int s_id;
    private String surname;
    private String name;
    private int m_id;
    private String mark;

    public StudentMark(int s_id, String surname, String name, int m_id, String mark)
    {
        this.s_id = s_id;
        this.surname = surname;
        this.name = name;
        this.m_id = m_id;
        this.mark = mark;
    }

    public StudentMark()
    {
    }

    public int getSId() {
        return s_id;
    }

    public void setSId(int s_id) {
        this.s_id = s_id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMId() {
        return m_id;
    }

    public void setMId(int m_id) {
        this.m_id = m_id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}