package org.example.Service;

import org.example.DTO.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface School {

    public Optional<StudentResponse> getStudentById(int id);

}
