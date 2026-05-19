package traltb.fudn.chapter1_exercise1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import traltb.fudn.chapter1_exercise1.pojo.Student;
import traltb.fudn.chapter1_exercise1.service.StudentService;
import traltb.fudn.chapter1_exercise1.service.StudentServiceImpl;

import java.util.List;
import java.util.Optional;

/**
 * ============================================================================
 *  TODO #6: MAIN APP - CRUD DEMO  (15 điểm)
 * ============================================================================
 *
 *  YÊU CẦU:
 *  Trong main(), viết code demo đủ 5 thao tác CRUD bằng StudentService.
 *
 *  ⚠️ ĐIỀU KIỆN:
 *    - SQL Server đã chạy ở localhost:1433
 *    - Đã tạo database "HSF301_Chapter1_Ex1"
 *    - Đã cập nhật user/password trong META-INF/persistence.xml
 *
 *  CHECKLIST:
 *    [x] (6.1) CREATE: tạo 1 Student mới (đủ email/password/firstName/lastName/marks),
 *              gọi service.create(), in ra "CREATE OK -> id: <id>"
 *    [x] (6.2) READ ALL: gọi service.getAll(), in "TOTAL STUDENTS: <count>"
 *    [x] (6.3) READ BY ID: gọi service.getById(<id>), in "FIND BY ID: <student>"
 *    [x] (6.4) UPDATE: đổi marks/firstName, gọi service.update(), in kết quả
 *    [x] (6.5) DELETE: gọi service.deleteById(<id>), in "DELETE OK -> id: <id>"
 *
 *  GHI CHÚ:
 *    - Annotation @SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
 *      ĐÃ ĐƯỢC GIỮ NGUYÊN vì chúng ta dùng JPA RESOURCE_LOCAL (qua persistence.xml)
 *      thay vì Spring Data JPA — không cần Spring tự config DataSource.
 *    - SpringApplication.run() khởi tạo Spring context (cho tương lai mở rộng).
 *      CRUD demo dùng StudentService trực tiếp (không qua Spring bean).
 * ============================================================================
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Chapter1Exercise1Application {

    public static void main(String[] args) {

        SpringApplication.run(Chapter1Exercise1Application.class, args);

        StudentService studentService = new StudentServiceImpl();

        // ================== 1) CREATE ==================
        Student student = new Student();
        student.setEmail("demo@example.com");
        student.setPassword("password123");
        student.setFirstName("Huy");
        student.setLastName("Hoang");
        student.setMarks(85);

        Student created = studentService.create(student);
        System.out.println("CREATE OK -> id: " + created.getId());

        Long createdId = created.getId();

        // ================== 2) READ ALL ==================
        List<Student> list = studentService.getAll();
        System.out.println("TOTAL STUDENTS: " + list.size());

        // ================== 3) READ BY ID ==================
        Optional<Student> optional = studentService.getById(createdId);
        System.out.println("FIND BY ID: " + optional.orElse(null));

        // ================== 4) UPDATE ==================
        created.setFirstName("UpdatedName");
        created.setMarks(99);
        Student updated = studentService.update(created);
        System.out.println("UPDATE OK -> firstName: " + updated.getFirstName() + ", marks: " + updated.getMarks());

        // ================== 5) DELETE ==================
        studentService.deleteById(createdId);
        System.out.println("DELETE OK -> id: " + createdId);

    }
}
