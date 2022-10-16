package com.elec5619.hdhelper.service.impl;

import com.elec5619.hdhelper.entity.Course;
import com.elec5619.hdhelper.exception.ResourceNotFoundException;
import com.elec5619.hdhelper.repository.CourseRepository;
import com.elec5619.hdhelper.service.CourseService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Course", "Id", id));
    }

    @Override
    public Course updateCourse(Course course, Long id) {
        Course existingCourse = courseRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Course", "Id", id));
        existingCourse.setCourseName(course.getCourseName());
        if (course.getNumberOfEachGroup() != null) {
            existingCourse.setNumberOfEachGroup(course.getNumberOfEachGroup());
        }
        courseRepository.save(existingCourse);
        return existingCourse;
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Course", "Id", id));
        courseRepository.deleteById(id);
    }

    @Override
    public void enableCourse(String courseName) {
        courseRepository.enableCourse(courseName);
    }

    @Override
    public List<Course> findCoursesByKeyword(String keyword) {
        if(Strings.isBlank(keyword)) {
            return courseRepository.findAllEnabledCourses();
        } else {
            return courseRepository.findCoursesByKeyword(keyword);
        }
    }

    @Override
    public List<Course> findAllEnabledCourses() {
        return courseRepository.findAllEnabledCourses();
    }

//    @Override
//    public void disableCourse(Long id) {
//        Course existingCourse = courseRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("Course", "Id", id));
//        existingCourse.setEnabled(false);
//        courseRepository.save(existingCourse);
//    }
}
