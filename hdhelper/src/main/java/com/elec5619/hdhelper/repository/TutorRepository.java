package com.elec5619.hdhelper.repository;

import com.elec5619.hdhelper.entity.Tutor;
import com.sun.jndi.ldap.sasl.LdapSasl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    @Query(value = "SELECT * FROM tutors t WHERE t.first_name = ?1 AND t.last_name = ?2", nativeQuery = true)
    List<Tutor> findByFirstNameAndLastName(String firstName, String lastName);
}
