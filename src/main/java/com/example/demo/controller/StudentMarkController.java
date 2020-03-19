package com.example.demo.controller;

import com.example.demo.dao.StudentMarkJdbc;
import com.example.demo.model.StudentMark;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentMarkController
{
    private final StudentMarkJdbc studentMarkJdbc;

    public StudentMarkController(StudentMarkJdbc studentMarkJdbc)
    {
        this.studentMarkJdbc = studentMarkJdbc;
    }

    @GetMapping("/student_mark")
    public List<StudentMark> getMark()
    {
        return studentMarkJdbc.get();
    }
}