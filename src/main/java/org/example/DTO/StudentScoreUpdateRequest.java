package org.example.DTO;

import jakarta.validation.constraints.NotBlank;

public class StudentScoreUpdateRequest {

    @NotBlank
    private int admnNo;
    @NotBlank
    private int score;

    public int getAdmnNo() {
        return admnNo;
    }

    public void setAdmnNo(int admnNo) {
        this.admnNo = admnNo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}