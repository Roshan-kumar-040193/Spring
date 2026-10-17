package org.example.Controller;

import org.example.DTO.StudentRequest;
import org.example.DTO.StudentResponse;
import org.example.DTO.StudentScoreUpdateRequest;
import org.example.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Student")
public class StudentController {

    StudentService studentService;

    StudentController(StudentService service){
        studentService=service;
    }

    @GetMapping("/getStudent")
    private ResponseEntity<StudentResponse> getStudentById(@RequestParam int id){
        Optional<StudentResponse> response = studentService.getStudentById(id);
        return response.map(studentResponse -> ResponseEntity.status(HttpStatus.OK).body(studentResponse)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/getStudents")
    private ResponseEntity<List<StudentResponse>> getStudentById(){
        Optional<List<StudentResponse>> response = studentService.getAllStudents();
        return response.map(studentResponse -> ResponseEntity.status(HttpStatus.OK).body(studentResponse)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/addStudent")
    private ResponseEntity<StudentResponse> addStudent(@RequestBody StudentRequest request){
        Optional<StudentResponse> response = studentService.addStdent(request);
        return response.map(studentResponse -> ResponseEntity.status(HttpStatus.OK).body(studentResponse)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<StudentResponse>> addStudents(
            @RequestBody List<StudentRequest> requests) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.addStudents(requests));
    }

    @PatchMapping("/exam-dates")
    public ResponseEntity<Integer> updateExamDates() {
        return ResponseEntity.ok(studentService.updateExamDates());
    }

    @PatchMapping("/scores")
    public ResponseEntity<Integer> updateScores(
            @RequestBody List<StudentScoreUpdateRequest> requests) {

        return ResponseEntity.ok(studentService.updateScores(requests));
    }
}
