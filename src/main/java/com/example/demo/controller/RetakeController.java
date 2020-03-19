package com.example.demo.controller;

import com.example.demo.dao.RetakeJdbc;
import com.example.demo.model.Retake;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RetakeController
{
    private final RetakeJdbc markJdbc;

    public RetakeController(RetakeJdbc markJdbc)
    {
        this.markJdbc = markJdbc;
    }

    @GetMapping("/retake")
    public List<Retake> getMark()
    {
        return markJdbc.get();
    }
}