package com.elec5619.hdhelper.service.impl;

import com.elec5619.hdhelper.entity.Student;
import com.elec5619.hdhelper.exception.ResourceNotFoundException;
import com.elec5619.hdhelper.repository.StudentRepository;
import com.elec5619.hdhelper.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Student", "Id", id));
    }

    @Override
    public Student updateStudent(Student student, Long id) throws NoSuchAlgorithmException {
        // Check whether ID exists
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setGender(student.getGender());
//        existingStudent.setEmail(student.getEmail());
        if (student.getPassword() != null && !student.getPassword().equals("") ) {
            existingStudent.setPassword(hashPassword(student.getPassword()));
        }
        studentRepository.save(existingStudent);

        return existingStudent;
    }

    @Override
    public void deleteStudent(Long id) {
        // Check whether ID exists
        studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
        studentRepository.deleteById(id);
    }

    @Override
    public int enableUser(String email) {
        return studentRepository.enableUser(email);
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }
}
