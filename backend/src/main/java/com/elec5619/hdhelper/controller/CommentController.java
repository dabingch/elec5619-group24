package com.elec5619.hdhelper.controller;

import com.elec5619.hdhelper.entity.Comment;
import com.elec5619.hdhelper.entity.Course;
import com.elec5619.hdhelper.exception.ResourceNotFoundException;
import com.elec5619.hdhelper.repository.CommentRepository;
import com.elec5619.hdhelper.repository.CourseRepository;
import com.elec5619.hdhelper.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CommentRepository commentRepository;

    // Get comments by a courseId
    @GetMapping("/courses/{courseId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByCourseId(@PathVariable(value = "courseId") Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course", "Id", courseId);
        }

        List<Comment> comments = commentRepository.findByCourseId(courseId);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Get comments by a studentId
    @GetMapping("/students/{studentId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByStudentId(@PathVariable(value = "studentId") Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student", "Id", studentId);
        }

        List<Comment> comments = commentRepository.findByStudentId(studentId);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Get a comment by commentId
    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentsById(@PathVariable(value = "id") Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    // Get all comments
    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComents() {
        List<Comment> comments = commentRepository.findAll();

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Create a comment based on a courseId and a studentId
    @PostMapping("/courses/{courseId}/students/{studentId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "courseId") Long courseId,
                                                 @PathVariable(value = "studentId") Long studentId,
                                                 @RequestBody Comment commentRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        commentRequest.setCourse(course);

        Comment comment = studentRepository.findById(studentId).map(student -> {
            commentRequest.setStudent(student);
            return commentRepository.save(commentRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    // Update a comment by a commentId
    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Long commentId,
                                                 @RequestBody Comment commentRequest) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));

        comment.setCourseComment(commentRequest.getCourseComment());
        comment.setLecturerComment(commentRequest.getLecturerComment());
        comment.setTutorComment(comment.getTutorComment());
        comment.setRating(commentRequest.getRating());

        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.OK);
    }

    // Delete a comment by a commentId
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") Long commentId) {
        commentRepository.deleteById(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete all comments by a courseId
    @DeleteMapping("/courses/{courseId}/comments")
    public ResponseEntity<List<Comment>> deleteAllCommentsOfCourse(@PathVariable(value = "courseId") Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course", "Id", courseId);
        }

        commentRepository.deleteByCourseId(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete all comments by a studentId
    @DeleteMapping("/students/{studentId}/comments")
    public ResponseEntity<List<Comment>> deleteAllCommentsOfStudent(@PathVariable(value = "studentId") Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student", "Id", studentId);
        }

        commentRepository.deleteByStudentId(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
