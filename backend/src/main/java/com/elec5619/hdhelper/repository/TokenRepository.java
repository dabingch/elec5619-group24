package com.elec5619.hdhelper.repository;

import com.elec5619.hdhelper.entity.AuthenticationToken;
import com.elec5619.hdhelper.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Long> {
    AuthenticationToken findByStudent(Student student);
    Optional<AuthenticationToken> findByToken(String token);
}
