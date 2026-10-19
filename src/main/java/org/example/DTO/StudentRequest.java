package org.example.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

@Component
public class StudentRequest {
    @NotBlank
    @Size(min = 3,max = 50,message = "Name should atleast be of 3 letters")
    private String name;

    private int admnNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Setting name");this.name = name;
    }

    public int getAdmnNo() {
        return admnNo;
    }

    public void setAdmnNo(int admnNo) {
        this.admnNo = admnNo;
    }

    @Override
    public String toString() {
        return "StudentRequest{" +
                "name='" + name + '\'' +
                ", admnNo=" + admnNo +
                '}';
    }
}
