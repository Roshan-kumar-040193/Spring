package org.example.Service;

import jakarta.transaction.Transactional;
import org.example.DTO.StudentRequest;
import org.example.DTO.StudentResponse;
import org.example.DTO.StudentScoreUpdateRequest;
import org.example.Entity.Student;
import org.example.Exception.CustomException;
import org.example.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;

    StudentService(StudentRepository repository){
        this.repository=repository;
    }

    public Optional<StudentResponse> getStudentById(int id){



        Optional<Student> student1 = repository.findByAdmnNoAndIsDeletedFalse(id);
        if(student1.isEmpty()){
            throw new CustomException("couldn't find a student with id : "+id);
        }
        else{
            StudentResponse response =  new StudentResponse();
            Student s= student1.get();
            response.setAdmnNo(s.getAdmnNo());
            response.setName(s.getName());
            if (s.getResultDate() != null && LocalDateTime.now().isAfter(s.getResultDate())) {
                response.setScore(s.getScore());
            } else {
                response.setScore(null);
            }
            return Optional.of(response);
        }
    }

    public Optional<List<StudentResponse>> getAllStudents() {
        List<Student> student1 = repository.findByIsDeletedFalse();
        if(student1.isEmpty()){
            return Optional.empty();
        }
        else{
            List<StudentResponse> responses = new ArrayList<>();
            student1.forEach(s->{
                StudentResponse response =  new StudentResponse();
                response.setAdmnNo(s.getAdmnNo());
                response.setName(s.getName());
                if (s.getResultDate()!=null && LocalDateTime.now().isAfter(s.getResultDate()))
                    response.setScore(s.getScore());
                else {
                    response.setScore(null);
                }
                responses.add(response);
            });

            return Optional.of(responses);
        }
    }

    public Optional<StudentResponse> addStdent(StudentRequest request) {
        Optional<Student> student1 = repository.findByAdmnNoAndIsDeletedFalse(request.getAdmnNo());
        if(student1.isEmpty()){

            Student student = new Student();
            student.setAdmnNo(request.getAdmnNo());
            student.setName(request.getName());
            student.isDeleted();
            Student s = repository.save(student);
            StudentResponse response =  new StudentResponse();
            response.setAdmnNo(s.getAdmnNo());
            response.setName(s.getName());
            if(s.getResultDate()!=null && LocalDateTime.now().isAfter(s.getResultDate())) response.setScore(s.getScore());
            return Optional.of(response);

        }
        else{
            return Optional.empty();
        }
    }

    @Transactional
    public int updateExamDates() {
        List<Student> students = repository.findByIsDeletedFalse();
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < students.size(); i++) {
            LocalDateTime examDate;

            if (i % 2 == 0) {
                // Before today
                examDate = now.minusDays(i + 1);
            } else {
                // After today
                examDate = now.plusDays(i + 1);
            }

            students.get(i).setResultDate(examDate);
        }

        repository.saveAll(students);
        return students.size();
    }

    @Transactional
    public List<StudentResponse> addStudents(List<StudentRequest> requests) {
        List<Student> students = new ArrayList<>();

        for (StudentRequest request : requests) {
            if (repository
                    .findByAdmnNoAndIsDeletedFalse(request.getAdmnNo())
                    .isPresent()) {
                throw new IllegalArgumentException(
                        "Student already exists: " + request.getAdmnNo());
            }

            Student student = new Student();
            student.setAdmnNo(request.getAdmnNo());
            student.setName(request.getName());
            student.setDeleted(false);

            students.add(student);
        }

        List<Student> savedStudents = repository.saveAll(students);

        List<StudentResponse> responses = new ArrayList<>();
        for (Student student : savedStudents) {
            StudentResponse response = new StudentResponse();
            response.setAdmnNo(student.getAdmnNo());
            response.setName(student.getName());
            responses.add(response);
        }

        return responses;
    }

    @Transactional
    public int updateScores(List<StudentScoreUpdateRequest> requests) {
        List<Student> students = new ArrayList<>();

        for (StudentScoreUpdateRequest request : requests) {
            Student student = repository
                    .findByAdmnNoAndIsDeletedFalse(request.getAdmnNo())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Student not found: " + request.getAdmnNo()));

            student.setScore(request.getScore());
            students.add(student);
        }

        repository.saveAll(students);
        return students.size();
    }

    @Transactional
    public void deleteStudent(int id) {
        Optional<Student> student = repository.findByAdmnNoAndIsDeletedFalse(id);

        if(student.isPresent()) {
            Student student1 = student.get();
            student1.setDeleted(true);
            repository.save(student1);
            System.out.println("Student data is temporarily deleted");
        }
        else{
            Optional<Student> student1 = repository.findByAdmnNo(id);
            if(student1.isPresent()){
                repository.delete(student1.get());
                System.out.println("Student data is permanently deleted");
            }
            else{
                System.out.println("No Student found");
            }
        }
    }
}
