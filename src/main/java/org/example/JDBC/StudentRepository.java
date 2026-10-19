package org.example.JDBC;


import org.example.Entity.Student;

import javax.sql.DataSource;
import javax.swing.tree.RowMapper;
import java.beans.JavaBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentRepository {

    DataSource dataSource;

    RowMapper rowMapper;

    String url = "jdbc:postgresql://localhost:5432/mydb/testing";
    String username = "app_user";
    String password = "DBPass";
    String schema = "testing";

    void createStudent(Student student){
        String sql = "insert into Student(admn_no,is_deleted,name,result_date,score) " +
                "values(?,?,?,?,?)";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1,student.getAdmnNo());
            statement.setBoolean(2,false);
            statement.setString(3,student.getName());
            statement.setDate(4,Date.valueOf(student.getResultDate().toLocalDate()));

            statement.setInt(5,student.getScore());

            if(statement.executeUpdate()>0) System.out.println("Student Successfully inserted");

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void updateStudent(Map<String, Object> updateMap, int admnNo) {

        if (updateMap == null || updateMap.isEmpty()) {
            throw new IllegalArgumentException("No fields to update");
        }

        StringBuilder addKey = new StringBuilder();

        updateMap.keySet().forEach(key ->
                addKey.append(key).append(" = ?,")
        );

        addKey.setLength(addKey.length() - 1);

        String sql = "UPDATE Student SET " + addKey
                + " WHERE admnNo = ?";

        try (Connection connection =
                     DriverManager.getConnection(url, username, password);
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            int index = 1;

            for (Object value : updateMap.values()) {
                statement.setObject(index++, value);
            }

            statement.setInt(index, admnNo);

            int rows = statement.executeUpdate();

            if (rows == 1) {
                System.out.println("Student successfully updated");
            } else if (rows == 0) {
                System.out.println("Student not found");
            }
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    int deleteStudent(){
        return 0;
    }

    Student getStudent(int x){
        String sql = "select admn_no,is_deleted,name,result_date,score from Student" +
                " where admn_no = ?";

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,x);

            ResultSet result = statement.executeQuery();
            result.next();
            connection.close();
            return (Student)result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    Object getAllStudents(String sql){
        //String sql = "select admn_no,is_deleted,name,result_date,score from Student";
        List<Student> result = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();

            Boolean b =statement.execute(sql);
            //returns boolean, if boolean is true that means it was a read query
            // and we can fetch the result from resultSet
            // and if it's false we can get the int

            if(b){
                ResultSet resultSet = statement.getResultSet();
                resultSet.next();
                while(resultSet.next()){
                    result.add((Student) resultSet);
                }
            }
            else {
                return statement.getUpdateCount();
            }

            connection.close();

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
