package com.elec5619.hdhelper.controller;

import com.elec5619.hdhelper.entity.Course;
import com.elec5619.hdhelper.exception.ResourceNotFoundException;
import com.elec5619.hdhelper.repository.CourseRepository;
import com.elec5619.hdhelper.repository.LecturerRepository;
import com.elec5619.hdhelper.repository.TutorRepository;
import com.elec5619.hdhelper.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private LecturerRepository lecturerRepository;

    @MockBean
    private TutorRepository tutorRepository;

    @Test
    void saveCourse() {
        Course course = new Course();
        course.setCourseName("ELEC5619");
        courseService.saveCourse(course);
    }

    @Test
    void getAllCourses() {
        courseService.getAllCourses();
    }

    @Test
    void getCourseById() {
        Long courseId = Long.valueOf(1);
        courseService.getCourseById(courseId);
    }

    @Test
    void updateCourse() {
        Course course = new Course();
        course.setCourseName("INFO5990");
        courseService.updateCourse(course, course.getId());
    }

    @Test
    void deleteCourse() {
        Long courseId = Long.valueOf(1);
        courseService.deleteCourse(courseId);
    }

    @Test
    void enableCourse() {
        String courseName = "INFO5991";
        courseService.enableCourse(courseName);
    }

    @Test
    void findByEnabled() {
        courseRepository.findAllEnabledCourses();
    }

    @Test
    void enableCourseById() {
        Course course = new Course();
        course.setCourseName("INFO5991");
        courseRepository.save(course);
        course.setEnabled(true);
        courseRepository.save(course);
    }

    @Test
    void findCourseByKeyword() {
        String keyword = "INFO";
        courseRepository.findCoursesByKeyword(keyword);
    }
}