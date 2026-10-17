package org.example.DTO;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


public class ExceptionDTO {
    private LocalDateTime time;
    private String messgae;
    private String path;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ExceptionDTO(LocalDateTime time, String messgae, String path) {
        this.time = time;
        this.messgae = messgae;
        this.path = path;
    }
}
