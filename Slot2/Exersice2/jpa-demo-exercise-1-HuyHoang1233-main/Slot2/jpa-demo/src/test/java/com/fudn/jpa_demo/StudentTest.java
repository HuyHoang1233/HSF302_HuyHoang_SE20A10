package com.fudn.jpa_demo;
 
import com.fudn.jpa_demo.entity.Student;
import com.fudn.jpa_demo.service.StudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
 
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
 
@SpringBootTest
@Transactional
public class StudentTest {
 
    @Autowired
    private StudentService studentService;
 
    @PersistenceContext
    private EntityManager entityManager;
 
    @Test
    public void testCreateStudent() {

        Student student = studentService.createStudent("John Doe", "john@fpt.edu.vn", 20);
        assertNotNull(student.getId(), "Student ID should not be null after creation");
 
        entityManager.flush();
        entityManager.clear();
 

        Student retrieved = entityManager.find(Student.class, student.getId());
        assertNotNull(retrieved);
        assertEquals("John Doe", retrieved.getFullName());
        assertEquals("john@fpt.edu.vn", retrieved.getEmail());
    }
 
    @Test
    public void testUpdateStudent() {

        Student student = studentService.createStudent("Original Name", "original@fpt.edu.vn", 22);
        Long id = student.getId();
 
        entityManager.flush();
        entityManager.clear();
 

        studentService.updateStudent(id, "Updated Name", "updated@fpt.edu.vn", 23);
 
        entityManager.flush();
        entityManager.clear();
 

        Student updated = entityManager.find(Student.class, id);
        assertEquals("Updated Name", updated.getFullName());
        assertEquals("updated@fpt.edu.vn", updated.getEmail());
        assertEquals(23, updated.getAge());
    }
 
    @Test
    public void testDeleteStudent() {

        Student student = studentService.createStudent("Delete Me", "delete@fpt.edu.vn", 18);
        Long id = student.getId();
 
        entityManager.flush();
        entityManager.clear();
 

        studentService.deleteStudent(id);
 
        entityManager.flush();
        entityManager.clear();
 

        Student deleted = entityManager.find(Student.class, id);
        assertNull(deleted, "Student should be null after deletion");
    }
 
    @Test
    public void testGetAllStudents() {

        studentService.createStudent("Alice", "alice@fpt.edu.vn", 20);
        studentService.createStudent("Bob", "bob@fpt.edu.vn", 21);
 
        List<Student> students = studentService.getAllStudents();
        assertTrue(students.size() >= 2, "Should have at least 2 students");
    }
 
    @Test
    void testDeleteStudent_NotFound() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            studentService.deleteStudent(9999L);
        });
 
        assertTrue(exception.getMessage().contains("Student not found"));
    }
}
