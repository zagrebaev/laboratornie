package com.example.demo.controller;



import com.example.demo.dao.Study_GroupJdbc;
import com.example.demo.model.Study_Group;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
public class Study_GroupController {
    private final Study_GroupJdbc study_groupJdbc;

    public Study_GroupController(Study_GroupJdbc study_groupJdbc){
        this.study_groupJdbc = study_groupJdbc;
    }

    @GetMapping("/study_group/{id}")
    public Study_Group getStudy_group(@PathVariable int id){
        Study_Group study_group = study_groupJdbc.get(id);
        return study_group;
    }


    @PostMapping(value = "/study_group/add_study_group")
    public String addStudy_group(@RequestParam int id,@RequestParam String name){

        Study_Group study_group = study_groupJdbc.add(id,name);
        return "ADDED study group";
    }

    @PostMapping(value = "/study_group/update_study_group")
    public String updateStudy_group(@RequestParam int id,@RequestParam String name){

        String study_group = study_groupJdbc.update(id,name);
        return "ADDED study group";
    }

    @DeleteMapping("/del_study_group/{id}")
    public void deleteStuddy_group(@PathVariable int id){
        study_groupJdbc.del(id);
    }
}
