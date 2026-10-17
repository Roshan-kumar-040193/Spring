package org.example.DTO;

import org.springframework.stereotype.Component;

@Component
public class StudentRequest {
    private String name;
    private int admnNo;

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
}
