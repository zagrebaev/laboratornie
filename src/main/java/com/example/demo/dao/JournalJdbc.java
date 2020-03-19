package com.example.demo.dao;

import com.example.demo.model.Journal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JournalJdbc
{
    private final JdbcTemplate jdbcTemplate;

    public JournalJdbc(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Journal get(int id)
    {
        return jdbcTemplate.queryForObject("SELECT * FROM \"journal\" WHERE \"id\" = ?", this::mapJournalRecord, id);
    }

    public List<Journal> getAll()
    {
        return jdbcTemplate.queryForObject("SELECT * FROM journal", this::mapAllJournalRecords);
    }

    public List<Journal> getAllByStudent(int id)
    {
        return jdbcTemplate.queryForObject("SELECT * FROM journal WHERE student_id = ?", this::mapAllJournalRecords, id);
    }

    public List<Journal> getAllByStudyPlan(int id)
    {
        return jdbcTemplate.queryForObject("SELECT * FROM journal WHERE study_plan_id = ?", this::mapAllJournalRecords, id);
    }

    public int addJournal(int studentId, int studyPlanId, boolean inTime, int count, int markId)
    {
        return jdbcTemplate.update("INSERT INTO journal (student_id, study_plan_id, in_time, count, mark_id) VALUES (?, ?, ?, ?, ?)",
                studentId, studyPlanId, inTime, count, markId);
    }

    public int updateJournal(Journal jR)
    {
        StringBuilder sqlQuery = new StringBuilder("UPDATE journal SET ");

        if (jR.getStudentId() != null) sqlQuery.append("student_id = '").append(jR.getStudentId()).append("', ");
        if (jR.getStudyPlanId() != null) sqlQuery.append("study_plan_id = '").append(jR.getStudyPlanId()).append("', ");
        if (jR.isInTime() != null) sqlQuery.append("in_time = '").append(jR.isInTime()).append("', ");
        if (jR.getCount() != null) sqlQuery.append("count = '").append(jR.getCount()).append("', ");
        if (jR.getMarkId() != null) sqlQuery.append("mark_id = ").append(jR.getMarkId()).append("' ");
        else if (sqlQuery.charAt(sqlQuery.length() - 2) == ',') sqlQuery.deleteCharAt(sqlQuery.length() - 2);
        sqlQuery.append("WHERE id = ?");

        return jdbcTemplate.update(sqlQuery.toString(), jR.getId());
    }

    public int deleteJournal(int id)
    {
        return jdbcTemplate.update("DELETE FROM \"journal\" WHERE \"id\" = ?", id);
    }

    private Journal mapJournalRecord(ResultSet rs, int i) throws SQLException
    {
        return new Journal(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("study_plan_id"),
                rs.getBoolean("in_time"),
                rs.getInt("count"),
                rs.getInt("mark_id")
        );
    }

    private List<Journal> mapAllJournalRecords(ResultSet rs, int i) throws SQLException
    {
        List<Journal> journalList = new ArrayList<>();

        do
        {
            journalList.add(new Journal(
                    rs.getInt("id"),
                    rs.getInt("student_id"),
                    rs.getInt("study_plan_id"),
                    rs.getBoolean("in_time"),
                    rs.getInt("count"),
                    rs.getInt("mark_id")));
        }
        while (rs.next());

        return journalList;
    }
}