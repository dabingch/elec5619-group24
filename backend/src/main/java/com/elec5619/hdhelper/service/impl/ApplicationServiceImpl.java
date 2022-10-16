package com.elec5619.hdhelper.service.impl;

import com.elec5619.hdhelper.entity.Application;
import com.elec5619.hdhelper.entity.Post;
import com.elec5619.hdhelper.entity.Student;
import com.elec5619.hdhelper.exception.CustomException;
import com.elec5619.hdhelper.repository.ApplicationRepository;
import com.elec5619.hdhelper.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository applicationRepository;
    private EmailServiceImpl emailService;

    @Override
    public Application saveApplication(Post post, Student student) {
        List<Application> existingApplication = applicationRepository.findApplicationByPostAndStudent(post, student);
        if(existingApplication.size() == 0) {
            Application application = new Application();
            application.setPost(post);
            application.setStudent(student);
            emailService.apply(student, post);
            return applicationRepository.save(application);
        } else {
            throw new CustomException("Your already applied this group");
        }
    }

    @Override
    public List<Application> findByPost(Post post) {
        return applicationRepository.findByPost(post);
    }

    @Override
    public List<Application> findByStudent(Student student) {
        return applicationRepository.findByStudent(student);
    }

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }


}
