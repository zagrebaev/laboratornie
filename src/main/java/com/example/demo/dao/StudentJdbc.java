package com.example.demo.dao;


import com.example.demo.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentJdbc {
    private final JdbcTemplate jdbcTemplate;

    public StudentJdbc(JdbcTemplate jdbcTemplate) {    this.jdbcTemplate = jdbcTemplate;   }

    public Student get(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM student WHERE id = ?", this::mapStudent, id);
    }

    public List<Student> getAll(){
        return jdbcTemplate.query("SELECT * FROM student", this::mapStudent);
    }

    public List<Student> getAllByStudyGroup(int studyGroupId) {
        return jdbcTemplate.query(
                "SELECT student.id, surname, student.name, second_name, study_group_id, study_group.name " +
                        "AS study_group " +
                        "FROM student INNER JOIN study_group ON student.study_group_id = study_group.id " +
                        "WHERE study_group_id = ?",
                this::mapStudent,
                studyGroupId
        );
    }

    public String add(String surname, String name, String second_name, int study_group_id){
        jdbcTemplate.update("INSERT INTO student(SURNAME, NAME, SECOND_NAME, STUDY_GROUP_ID) VALUES(?,?,?,?)", surname, name, second_name, study_group_id);
        return("STUDENT ADDED");
    }

    public void del(int id){
        jdbcTemplate.update("DELETE FROM student WHERE id = ?", id);
    }
    public String update(int id, String surname, String name, String second_name, int study_group_id){
        jdbcTemplate.update("UPDATE student SET SURNAME = ?, NAME = ?, SECOND_NAME = ?, STUDY_GROUP_ID = ? WHERE ID = ?",surname, name, second_name, study_group_id, id);
        return("STUDENT UPDATED");
    }

    private Student mapStudent(ResultSet rs, int i) throws SQLException{
        Student student = new Student(
                rs.getInt("id"),
                rs.getString("surname"),
                rs.getString("name"),
                rs.getString("second_name"),
                rs.getInt("study_group_id")
        );
        return student;
    }

    public Student search(String student){
        return jdbcTemplate.queryForObject("SELECT * FROM student WHERE name = ?", Student.class, student);
    }


}
