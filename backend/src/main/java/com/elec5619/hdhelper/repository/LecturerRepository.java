package com.elec5619.hdhelper.repository;

import com.elec5619.hdhelper.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    @Query(value = "SELECT * FROM lecturers l WHERE l.first_name = ?1 AND l.last_name = ?2", nativeQuery = true)
    List<Lecturer> findByFirstNameAndLastName(String firstName, String lastName);
}
