package com.example.demo;

import com.example.demo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private StudentService studentService;

	@Test
	void contextLoads() {
	}

	@Test
	void testStudentCreation() {
		studentService.createStudent("Test User", "test@example.com", 25);
		System.out.println("Student creation test passed.");
	}

	@Test
	void testDeleteStudent() {

		studentService.createStudent("Delete Me", "delete@example.com", 30);


		studentService.deleteStudent(4L); 
		System.out.println("Student deletion test passed.");
	}

}
