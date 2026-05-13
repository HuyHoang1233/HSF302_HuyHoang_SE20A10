package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public void createStudent(String fullName, String email, Integer age) {
        Student s = new Student(fullName, email, age);
        studentRepository.save(s);
        System.out.println("Saved with ID = " + s.getId());
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
        System.out.println("Deleted student with ID = " + id);
    }

    public void printAll() {
        studentRepository.findAll().forEach(System.out::println);
    }
}