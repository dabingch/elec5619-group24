package com.elec5619.hdhelper.service;

import com.elec5619.hdhelper.entity.Application;
import com.elec5619.hdhelper.entity.Student;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student updateStudent(Student student, Long id) throws NoSuchAlgorithmException;
    void deleteStudent(Long id);
    int enableUser(String email);
}
