package com.elec5619.hdhelper.repository;

import com.elec5619.hdhelper.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE courses c " +
            "SET c.enabled = TRUE WHERE c.course_name = ?1", nativeQuery = true)
    void enableCourse(String courseName);

    @Transactional
    @Modifying
    @Query(value = "UPDATE courses c " +
            "SET c.enabled = FALSE WHERE c.course_name = ?1", nativeQuery = true)
    void disableCourse(String courseName);

    @Query(value = "SELECT * FROM courses c WHERE c.course_name like %?1% and c.enabled = true", nativeQuery = true)
    List<Course> findCoursesByKeyword(String keyword);

    @Query(value = "SELECT * FROM courses c WHERE c.enabled = true", nativeQuery = true)
    List<Course> findAllEnabledCourses();

}
