package com.elec5619.hdhelper.repository;

import com.elec5619.hdhelper.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Student s " +
            "SET s.enabled = TRUE WHERE s.email = ?1")
    int enableUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Student s " +
            "SET s.enabled = FALSE WHERE s.id = ?1")
    void disableUser(Long studentId);

    @Transactional
    @Modifying
    @Query("UPDATE Student s " +
            "SET s.enabled = TRUE WHERE s.id = ?1")
    void enableUserById(Long studentId);
}
