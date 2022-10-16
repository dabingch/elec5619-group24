package com.elec5619.hdhelper.controller;

import com.elec5619.hdhelper.entity.Student;
import com.elec5619.hdhelper.repository.StudentRepository;
import com.elec5619.hdhelper.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private StudentService studentService;

    @Test
    void getAllStudents() {
        studentService.getAllStudents();
    }

    @Test
    void getStudentById() {
        Long studentId = Long.valueOf(1);
        studentService.getStudentById(studentId);
    }

    @Test
    void updateStudent() throws NoSuchAlgorithmException {
        Student student = new Student();
        student.setGender("M");
        student.setFirstName("Hao");
        student.setLastName("Chen");
        student.setPassword("123");
        studentService.updateStudent(student, student.getId());
    }

    @Test
    void disableStudent() {
        Long studentId = Long.valueOf(1);
        studentRepository.disableUser(studentId);
    }

    @Test
    void enableStudent() {
        Long studentId = Long.valueOf(1);
        studentRepository.enableUserById(studentId);
    }
}