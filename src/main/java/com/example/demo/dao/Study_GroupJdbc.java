package com.example.demo.dao;

import com.example.demo.model.Student;
import com.example.demo.model.Study_Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import java.util.List;

import java.util.Map;

@Repository
public class Study_GroupJdbc {
    private final JdbcTemplate jdbcTemplate;

    public Study_GroupJdbc(JdbcTemplate jdbcTemplate) {    this.jdbcTemplate = jdbcTemplate;   }

    public Study_Group get(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM study_group WHERE id = ?", this::mapStudy_Group, id);
    }

    public List<Study_Group>getAll(){
        return jdbcTemplate.query("SELECT * FROM study_group", this::mapStudy_Group);
    }

    public Study_Group add(int id, String name){
        Study_Group study_group = new Study_Group(id,name);

        jdbcTemplate.update("INSERT INTO study_group(name) VALUES (?)", name);
        return study_group;

    }

    public String update(int id, String name){
        jdbcTemplate.update("UPDATE study_group SET name = ? WHERE id = ?", name, id);
        return "ADDED study group";
    }
    public void del(int id){
        jdbcTemplate.update("DELETE FROM study_group  WHERE id = ?",id);
    }

    private Study_Group mapStudy_Group(ResultSet rs, int i) throws SQLException{
        Study_Group study_group = new Study_Group(
                rs.getInt("id"),
                rs.getString("name")
        );
        return study_group;
    }

    public Study_Group search(String study_group){
        return jdbcTemplate.queryForObject("SELECT * FROM study_group WHERE name = ?", Study_Group.class, study_group);
    }
}
