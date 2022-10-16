package com.elec5619.hdhelper.service;

import com.elec5619.hdhelper.entity.Course;
import com.elec5619.hdhelper.entity.Post;

import java.util.List;

public interface CourseService {

    Course saveCourse(Course course);
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    Course updateCourse(Course course, Long id);
    void deleteCourse(Long id);
    void enableCourse(String courseName);
    List<Course> findCoursesByKeyword(String keyword);
    List<Course> findAllEnabledCourses();

}
