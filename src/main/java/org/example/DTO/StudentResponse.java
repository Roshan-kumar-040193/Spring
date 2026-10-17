package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse {
    @NotBlank
    private String name;
    @NotBlank
    private int adminNo;

    private Integer score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdmnNo() {
        return adminNo;
    }

    public void setAdmnNo(int admnNo) {
        this.adminNo = admnNo;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "StudentResponse{" +
                "name='" + name + '\'' +
                ", adminNo=" + adminNo +
                ", score=" + score +
                '}';
    }
}
