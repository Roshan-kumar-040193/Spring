package org.example.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.Entity.Student;
import org.hibernate.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    EntityManager manager;
    Session session;

    public StudentRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    public Optional<Student> findByAdmnNoAndIsDeletedFalse(int id){
        Thread t1 = new Thread(()->System.out.println(manager.find(Student.class, id)));
        Thread t2 = new Thread(()->System.out.println(manager.find(Student.class, id+1)));
        Thread t3 = new Thread(()->System.out.println(manager.find(Student.class, id+2)));
        Thread t4 = new Thread(()->System.out.println(manager.find(Student.class, id+3)));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        return Optional.of(manager.find(Student.class, id));
    };

    @Transactional
    public Student findByIsDeletedFalse(int id) {
        Thread t1 = new Thread(()->System.out.println(manager.find(Student.class, id)));
        Thread t2 = new Thread(()->System.out.println(manager.find(Student.class, id+1)));
        Thread t3 = new Thread(()->System.out.println(manager.find(Student.class, id+2)));
        Thread t4 = new Thread(()->System.out.println(manager.find(Student.class, id+3)));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        return manager.find(Student.class,id);
    }


    public Student updateStudent(int id) {
            Student student = manager.find(Student.class, id);

            student.setName("Roshan");

            manager.refresh(student);

            System.out.println(student.getName());
            return student;
    }

    @Transactional
    public Student updateStudent2(int id) {
        Student student = manager.find(Student.class, id);
        System.out.println(student.getName()+" Setting name to Richa");
        student.setName("Richa");

        return student;
    }

//    public Student save(Student student1) {
//        return student1;
//    }
//
//    public Optional<Student> findByAdmnNo(int id) {
//    }
//
//    public void delete(Student student) {
//    }
}
