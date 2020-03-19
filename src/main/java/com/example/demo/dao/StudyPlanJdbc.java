package com.example.demo.dao;

import com.example.demo.model.Journal;
import com.example.demo.model.StudyPlan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudyPlanJdbc
{
    private final JdbcTemplate jdbcTemplate;

    public StudyPlanJdbc(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<StudyPlan> get()
    {
        return jdbcTemplate.queryForObject("SELECT sp.id, s.name, e.type FROM study_plan AS sp " +
                "INNER JOIN subject AS s ON s.id = sp.subject_id " +
                "INNER JOIN exam_type as e ON e.id = sp.exam_type_id", this::mapStudyPlanAll);
    }

    private List<StudyPlan> mapStudyPlanAll(ResultSet rs, int i) throws SQLException
    {
        List<StudyPlan> studyPlanList = new ArrayList<>();

        do
        {
            studyPlanList.add(new StudyPlan(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("type")));
        }
        while (rs.next());

        return studyPlanList;
    }
}