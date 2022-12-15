package com.luv2code.springmvc;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@SpringBootTest(classes = MvcTestingExampleApplication.class)
class StudentAndGradeServiceTest {

    @Autowired
    private StudentAndGradeService studentService;
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setupDB(){
        jdbcTemplate.execute("insert into student(id, firstname, lastname, email_address) " +
                "values (1, 'Eric', 'Roby', 'eric.roby@gmail.com')");
    }

    @Test
    void createStudentService(){

        studentService.createStudent("Chad", "Darby", "chad.darby@gmail.com");
        CollegeStudent student = studentDao.findByEmailAddress("chad.darby@gmail.com");
        assertEquals("chad.darby@gmail.com", student.getEmailAddress(), "find by email");
    }

    @Test
    void isStudentNullCheck(){
        assertTrue(studentService.checkIfStudentIsNull(1));
        assertFalse(studentService.checkIfStudentIsNull(0));
    }

    @Test
    void deleteStudentService(){
        Optional<CollegeStudent> deletedCollegeStudent = studentDao.findById(1);

        assertTrue(deletedCollegeStudent.isPresent(), "Return true");
        studentService.deleteStudent(1);

        deletedCollegeStudent = studentDao.findById(1);
        assertFalse(deletedCollegeStudent.isPresent(), "Return false");
    }

    @Test
    void getGradebookService(){
        Iterable<CollegeStudent> iterableCollegeStudents = studentService.getGradebook();

        List<CollegeStudent> collegeStudents = new ArrayList<>();

        for(CollegeStudent collegeStudent: iterableCollegeStudents) {
            collegeStudents.add(collegeStudent);
        }

        assertEquals(1, collegeStudents.size());
    }

    @Sql("/insertData.sql")
    @Test
    void getGradebookServiceWithSql(){
        Iterable<CollegeStudent> iterableCollegeStudents = studentService.getGradebook();

        List<CollegeStudent> collegeStudents = new ArrayList<>();

        for(CollegeStudent collegeStudent: iterableCollegeStudents) {
            collegeStudents.add(collegeStudent);
        }

        assertEquals(5, collegeStudents.size());
    }

    @AfterEach
    void setupAfterTransaction(){
        jdbcTemplate.execute("delete from student");
    }
}
