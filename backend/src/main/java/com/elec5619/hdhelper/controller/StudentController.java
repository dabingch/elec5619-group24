package com.elec5619.hdhelper.controller;

import com.elec5619.hdhelper.entity.Student;
import com.elec5619.hdhelper.exception.ResourceNotFoundException;
import com.elec5619.hdhelper.repository.StudentRepository;
import com.elec5619.hdhelper.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    public StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get a student by id
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long studentId) {
        return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);
    }

    // Update a student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long studentId,
                                                   @RequestBody Student student) throws NoSuchAlgorithmException {
        return new ResponseEntity<>(studentService.updateStudent(student, studentId), HttpStatus.OK);
    }

    // Delete a student
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long studentId) {
//        studentService.deleteStudent(studentId);
//        return new ResponseEntity<>("Student delete successfully!", HttpStatus.OK);
//    }

    // Disable a student by id
    @PutMapping("/disable/{id}")
    public ResponseEntity<String> disableStudent(@PathVariable(value = "id") Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student", "Id", studentId);
        }

        studentRepository.disableUser(studentId);
        return new ResponseEntity<>("Student disable successfully", HttpStatus.OK);
    }

    // Enable a user by id
    @PutMapping("/enable/{id}")
    public ResponseEntity<String> enableStudent(@PathVariable(value = "id") Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student", "Id", studentId);
        }

        studentRepository.enableUserById(studentId);
        return new ResponseEntity<>("Student enable successfully", HttpStatus.OK);
    }

}
