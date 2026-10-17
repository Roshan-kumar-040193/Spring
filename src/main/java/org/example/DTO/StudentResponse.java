package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Lazy;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse {
    private String name;
    private int admnNo;
    @Lazy
    private Integer score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdmnNo() {
        return admnNo;
    }

    public void setAdmnNo(int admnNo) {
        this.admnNo = admnNo;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
