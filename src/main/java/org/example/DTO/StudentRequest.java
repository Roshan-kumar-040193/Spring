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

    @NotNull(message = "Admission number is required")
    @Min(value = 1, message = "Admission number must be positive")
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
