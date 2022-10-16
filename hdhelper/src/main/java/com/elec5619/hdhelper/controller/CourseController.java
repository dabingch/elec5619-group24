package com.elec5619.hdhelper.controller;

import com.elec5619.hdhelper.entity.Course;
import com.elec5619.hdhelper.entity.Lecturer;
import com.elec5619.hdhelper.entity.Tutor;
import com.elec5619.hdhelper.exception.ResourceNotFoundException;
import com.elec5619.hdhelper.repository.CourseRepository;
import com.elec5619.hdhelper.repository.LecturerRepository;
import com.elec5619.hdhelper.repository.TutorRepository;
import com.elec5619.hdhelper.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    public CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    LecturerRepository lecturerRepository;

    @Autowired
    TutorRepository tutorRepository;

    // Create a course default by disabled
    @PostMapping("/lecturer/{lecturerId}/tutor/{tutorId}")
    public ResponseEntity<Course> saveCourse(@PathVariable(value = "lecturerId") Long lecturerId,
                                            @PathVariable(value = "tutorId") Long tutorId,
                                            @RequestBody Course course) {

        Lecturer lecturer = lecturerRepository.findById(lecturerId)
                .orElseThrow(() -> new ResourceNotFoundException("Lecturer", "Id", lecturerId));

        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor", "Id", tutorId));

        course.setLecturer(lecturer);
        course.setTutor(tutor);

        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }

    // Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Get a course by id
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") Long courseId) {
        return new ResponseEntity<>(courseService.getCourseById(courseId), HttpStatus.OK);
    }

    // Update a course by id
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") Long courseId,
                                               @RequestBody Course course) {
        return new ResponseEntity<>(courseService.updateCourse(course, courseId), HttpStatus.OK);
    }

    // Delete a course by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("Course delete successfully!", HttpStatus.OK);
    }

    // Enable a course by the course name
    @PutMapping
    public ResponseEntity<String> enableCourse(@RequestParam("courseName") String courseName) {
        courseService.enableCourse(courseName);
        return new ResponseEntity<>("Course enable successfully", HttpStatus.OK);
    }

    // Disable a course by the course name
    @PutMapping("/disable")
    public ResponseEntity<String> disableCourse(@RequestParam("courseName") String courseName) {
        courseRepository.disableCourse(courseName);
        return new ResponseEntity<>("Course disable successfully", HttpStatus.OK);
    }

    // Find all enabled courses
    @GetMapping("/enabled")
    public ResponseEntity<List<Course>> findByEnabled() {
        List<Course> courses = courseRepository.findAllEnabledCourses();

        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    // Enable a course by the courseId
    @PutMapping("/enabled/{courseId}")
    public ResponseEntity<Course> enableCourseById(@PathVariable(value = "courseId") Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        course.setEnabled(true);
        courseRepository.save(course);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Disable a course by the courseId
    @PutMapping("/disabled/{courseId}")
    public ResponseEntity<Course> disableCourseById(@PathVariable(value = "courseId") Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        course.setEnabled(false);
        courseRepository.save(course);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Find courses by a keyword
    @GetMapping("/search")
    public ResponseEntity<List<Course>> findCourseByKeyword(@RequestParam("keyword") String keyword) {
        return new ResponseEntity<>(courseRepository.findCoursesByKeyword(keyword), HttpStatus.OK);
    }
}
