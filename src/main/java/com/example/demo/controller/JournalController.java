package com.example.demo.controller;

import com.example.demo.dao.JournalJdbc;
import com.example.demo.model.Journal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JournalController
{
    private final JournalJdbc journalJdbc;

    public JournalController(JournalJdbc journalRecordJdbc)
    {
        this.journalJdbc = journalRecordJdbc;
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/journal/{id}")
    public Journal getJournalRecord(@PathVariable int id)
    {
        return journalJdbc.get(id);
    }

    @GetMapping("/journal/all")
    public List<Journal> getAllJournalRecords()
    {
        return journalJdbc.getAll();
    }

    @GetMapping("/journal/student/{id}")
    public List<Journal> getJournalRecordsByStudent(@PathVariable int id)
    {
        return journalJdbc.getAllByStudent(id);
    }

    @GetMapping("/journal/study_plan/{id}")
    public List<Journal> getJournalRecordsByStudyPlan(@PathVariable int id)
    {
        return journalJdbc.getAllByStudyPlan(id);
    }

    @PostMapping("/journal/add")
    public int addNewRecord(@RequestParam int studentId, @RequestParam int studyPlanId, @RequestParam boolean inTime, @RequestParam int count, @RequestParam int markId)
    {
        return journalJdbc.addJournal(studentId, studyPlanId, inTime, count, markId);
    }

    @PostMapping("/journal/update")
    public int updateRecord(@RequestBody Journal jR)
    {
        return journalJdbc.updateJournal(jR);
    }

    @DeleteMapping("/journal/delete/{id}")
    public int deleteRecordById(@PathVariable int id)
    {
        return journalJdbc.deleteJournal(id);
    }

}