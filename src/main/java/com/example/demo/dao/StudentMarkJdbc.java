package com.example.demo.dao;

import com.example.demo.model.Mark;
import com.example.demo.model.StudentMark;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentMarkJdbc {
    private final JdbcTemplate jdbcTemplate;

    public StudentMarkJdbc(JdbcTemplate template)
    {
        this.jdbcTemplate = template;
    }

    public List<StudentMark> get(){
        return jdbcTemplate.queryForObject("SELECT s.id AS s_id, s.surname, s.name, s.second_name, m.id AS m_id, m.name AS mark_name " +
                "FROM journal AS jour\n" +
                "INNER JOIN student AS s ON j.student_id = s.id\n" +
                "INNER JOIN mark AS m ON jour.mark_id = m.id", this::mapAllStudentMark);
    }

    private List<StudentMark> mapAllStudentMark(ResultSet rs, int i) throws SQLException
    {
        List<StudentMark> StudentMarkList = new ArrayList<>();

        do
        {
            StudentMarkList.add(new StudentMark(
                    rs.getInt("s_id"),
                    rs.getString("surname"),
                    rs.getString("name"),
                    rs.getInt("m_id"),
                    rs.getString("mark_name")));
        }
        while (rs.next());

        return StudentMarkList;
    }
}