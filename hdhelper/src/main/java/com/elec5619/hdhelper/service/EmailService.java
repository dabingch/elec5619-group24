package com.elec5619.hdhelper.service;

import com.elec5619.hdhelper.entity.Post;
import com.elec5619.hdhelper.entity.Student;

public interface EmailService {
    void send(String to, String email);
    void apply(Student student, Post post);
}
