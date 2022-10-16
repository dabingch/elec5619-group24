package com.elec5619.hdhelper.service;

import com.elec5619.hdhelper.entity.Post;

import com.elec5619.hdhelper.entity.Application;
import com.elec5619.hdhelper.entity.Student;

import java.util.List;

public interface ApplicationService {
    Application saveApplication(Post post, Student student);
    List<Application> findByPost(Post post);
    List<Application> findByStudent(Student student);
    List<Application> getAllApplications();
}

