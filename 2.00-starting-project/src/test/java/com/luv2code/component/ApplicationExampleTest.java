package com.luv2code.component;

import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
class ApplicationExampleTest {

    private static int count = 0;

    @Value("${info.app.name}")
    private String appInfo;
    @Value("${info.app.description}")
    private String appDescription;
    @Value("${info.app.version}")
    private String appVersion;
    @Value("${info.school.name}")
    private String schoolName;

    @Autowired
    CollegeStudent student;
    @Autowired
    StudentGrades studentGrades;
    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void beforeEach(){
        count++;
        System.out.println("Testing: " + appInfo + " which is " + appDescription + " Version: " + appVersion + ". Execution of test method " + count);
        student.setFirstname("Eric");
        student.setLastname("Roby");
        student.setEmailAddress("eric.roby@gmail.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 85.0, 76.60, 53.51)));
        student.setStudentGrades(studentGrades);
    }

    @DisplayName("Add grade results for student grades")
    @Test
    void addGradeResultForStudentGrades(){
        assertEquals(315.11, studentGrades.addGradeResultsForSingleClass(
                student.getStudentGrades().getMathGradeResults()
        ));
    }

    @DisplayName("Add grade results for student grades not equal")
    @Test
    void addGradeResultForStudentGradesAssertNotEquals(){
        assertNotEquals(0, studentGrades.addGradeResultsForSingleClass(
                student.getStudentGrades().getMathGradeResults()
        ));
    }

    @DisplayName("Is grade greater")
    @Test
    void isGradeGreaterStudentGrades(){
        assertTrue(studentGrades.isGradeGreater(90, 75), "failure - should be true");
    }

    @DisplayName("Is grade grater")
    @Test
    void isGradeGraterStudentGradesAssertFalse(){
        assertFalse(studentGrades.isGradeGreater(89, 92), "Failure - should be false");
    }

    @DisplayName("Check Null for student grades")
    @Test
    void checkNullForStudentGrades(){
        assertNotNull(studentGrades.checkNull(student.getStudentGrades().getMathGradeResults()), "object should not be null");
    }

    @DisplayName("Create student without grade init")
    @Test
    void createStudentWithoutGradesInit(){
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);

        studentTwo.setFirstname("Chad");
        studentTwo.setLastname("Darby");
        studentTwo.setEmailAddress("chad.darby@gmail.com");
        assertNotNull(studentTwo.getFirstname());
        assertNotNull(studentTwo.getLastname());
        assertNotNull(studentTwo.getEmailAddress());
        assertNull(studentTwo.getStudentGrades());
    }

    @DisplayName("Verify students arte prototypes")
    @Test
    void verifyStudentsArePrototypes(){
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);

        assertNotSame(student, studentTwo);
    }

    @DisplayName("FInd Grade Point Average")
    @Test
    void findGradePointAverage(){
        assertAll("Testing all assertEquals",
                () -> assertEquals(315.11, studentGrades.addGradeResultsForSingleClass(
                    student.getStudentGrades().getMathGradeResults())),
                () -> assertEquals(78.78, studentGrades.findGradePointAverage(
                        student.getStudentGrades().getMathGradeResults()))
        );
    }
}
