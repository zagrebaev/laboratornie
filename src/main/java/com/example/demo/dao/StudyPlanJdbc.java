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
        return jdbcTemplate.queryForObject("SELECT studplan.id, sub.name, extyp.type FROM study_plan AS studplan " +
                "INNER JOIN subject AS sub ON sub.id = studplan.subject_id " +
                "INNER JOIN exam_type as extyp ON extyp.id = studplan.exam_type_id", this::mapStudyPlanAll);
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