package com.elec5619.hdhelper.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "tokens")
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "student_id")
    private Student student;

    public AuthenticationToken(Student student) {
        this.student = student;
        this.createdDate = new Date();
        this.token = UUID.randomUUID().toString();
    }

    public AuthenticationToken() {
    }
}
