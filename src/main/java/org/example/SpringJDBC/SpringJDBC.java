package org.example.SpringJDBC;

import org.example.DTO.StudentRequest;
import org.example.Entity.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SpringJDBC {

    JdbcTemplate template;
    RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);

    public SpringJDBC(JdbcTemplate template){
        this.template=template;
    }

    public int createStudent(StudentRequest student){
        String sql="Insert into Student(admn_no,is_deleted,name,result_date,score) values(?,?,?,?,?)";
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        return template.update(sql,student.getAdmnNo(),false,student.getName(),timestamp,80);
    }

    public Student getStudent(int admnNo){
        String sql="select admn_no,is_deleted,name,result_date,score from Student where admn_no= ?";
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        return template.queryForObject(sql,rowMapper,admnNo);
    }

    public List<Student> getStudents(int admnNo){
        String sql="select admn_no,is_deleted,name,result_date,score from Student where admn_no= ?";
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        return template.query(sql,rowMapper,admnNo);
    }
}
