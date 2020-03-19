package com.example.demo.controller;

import com.example.demo.dao.StudyPlanJdbc;
import com.example.demo.model.StudyPlan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudyPlanController
{
    private final StudyPlanJdbc StudyPlanJdbc;

    public StudyPlanController(StudyPlanJdbc studyPlanJdbc)
    {
        this.StudyPlanJdbc = studyPlanJdbc;
    }

    @GetMapping("/studyPlans/all")
    public List<StudyPlan> getStudyPlans()
    {
        return StudyPlanJdbc.get();
    }
}