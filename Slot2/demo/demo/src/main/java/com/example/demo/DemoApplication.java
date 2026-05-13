package com.example.demo;

import com.example.demo.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(StudentService studentService) {
        return args -> {

            studentService.createStudent("Hoàng", "hoang@example.com", 20);
            studentService.createStudent("Nam", "nam@example.com", 21);
            studentService.createStudent("Hương", "huong@example.com", 19);

            System.out.println("\n=== DANH SÁCH SINH VIÊN ===");
            studentService.printAll();
        };
    }
}
