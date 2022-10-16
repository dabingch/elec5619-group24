package com.elec5619.hdhelper.repository;

import com.elec5619.hdhelper.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByCourseId(Long courseId);
    List<Comment> findByStudentId(Long studentId);
    @Transactional
    void deleteByCourseId(Long courseId);
    @Transactional
    void deleteByStudentId(Long studentId);
}
