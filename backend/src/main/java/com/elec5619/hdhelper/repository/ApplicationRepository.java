package com.elec5619.hdhelper.repository;

import com.elec5619.hdhelper.entity.Application;
import com.elec5619.hdhelper.entity.Post;
import com.elec5619.hdhelper.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
    List<Application> findByStudent(Student student);
    List<Application> findByPost(Post post);
    List<Application> findApplicationByPostAndStudent(Post post, Student student);
}
