package com.elec5619.hdhelper.repository;

import com.elec5619.hdhelper.entity.Course;
import com.elec5619.hdhelper.entity.Post;
import com.elec5619.hdhelper.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findPostsByCourseId(Long courseId);
    List<Post> findPostsByStudentId(Long studentId);
    @Query(value = "SELECT * FROM posts p WHERE p.course_id = ?1 AND p.student_id = ?2", nativeQuery = true)
    List<Post> findPostsByCourseAndStudent(Long courseId, Long studentId);
}
