package com.example.demo.controller;


import com.example.demo.dao.StudentJdbc;
import com.example.demo.model.Student;
import com.example.demo.model.Study_Group;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    private final StudentJdbc studentJdbc;

    public StudentController(StudentJdbc studentJdbc){
        this.studentJdbc = studentJdbc;
    }
    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable int id){
        Student student = studentJdbc.get(id);
        return student;
    }
    @PostMapping(value = "/student/add_student")
    public String addStudent(@RequestParam String surname, @RequestParam String name, @RequestParam String second_name, @RequestParam int study_group_id){
        studentJdbc.add(surname, name, second_name, study_group_id);
        return "ADDED student";
    }
    @PostMapping(value = "/student/update_student")
    public String updateStudent(@RequestParam int id, @RequestParam String surname, @RequestParam String name, @RequestParam String second_name, @RequestParam int study_group_id){
        studentJdbc.update(id, surname, name, second_name, study_group_id);
        return "UPDATED student";
    }
    @DeleteMapping("/delStudent/{id}")
    public void delStudent(@PathVariable int id){
        studentJdbc.del(id);
    }

}
