package com.luv2code.springmvc;

import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource("/application.properties")
@SpringBootTest(classes = MvcTestingExampleApplication.class)
class StudentAndGradeServiceTest {

    @Autowired
    private StudentAndGradeService studentService;

    @Test
    void createStudentService(){

        studentService.createStudent("Chad", "Darby", "chad.darby@gmail.com");
//        CollegeStudent student = StudentDao.findByEmailAddress("chad.darby@gmail.com");
//        assertEquals("chad.darby@gmail.com", student.getEmailAddress(), "find by email");
    }

}
