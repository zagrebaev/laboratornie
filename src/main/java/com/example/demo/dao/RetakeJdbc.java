package com.example.demo.dao;

import com.example.demo.model.Retake;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RetakeJdbc {
    private final JdbcTemplate jdbcTemplate;

    public RetakeJdbc(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Retake> get(){
        return jdbcTemplate.queryForObject("SELECT s.name, SUM(j.count) AS count FROM journal AS j\n" +
                "INNER JOIN study_plan AS sp ON j.study_plan_id = sp.id\n" +
                "INNER JOIN subject AS s ON sp.subject_id = s.id\n" +
                "GROUP BY s.name\n", this::mapAllRetake);
    }

    private List<Retake> mapAllRetake(ResultSet rs, int i) throws SQLException
    {
        List<Retake> retakeList = new ArrayList<>();

        do
        {
            retakeList.add(new Retake(
                    rs.getString("name"),
                    rs.getInt("count")));
        }
        while (rs.next());

        return retakeList;
    }
}