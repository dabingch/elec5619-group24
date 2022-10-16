package com.elec5619.hdhelper.controller;

import com.elec5619.hdhelper.entity.*;
import com.elec5619.hdhelper.exception.ResourceNotFoundException;
import com.elec5619.hdhelper.repository.CourseRepository;
import com.elec5619.hdhelper.repository.PostRepository;
import com.elec5619.hdhelper.repository.StudentRepository;
import com.elec5619.hdhelper.service.EmailService;
import com.elec5619.hdhelper.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PostController {
    public PostService postService;

    private EmailService emailService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PostRepository postRepository;

    // Get all posts
    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // Delete a post by its id
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>("Post delete successfully!", HttpStatus.OK);
    }

    // Disable a post by id
    @PutMapping("/posts/disable/{id}")
    public ResponseEntity<String> disablePost(@PathVariable("id") Long postId) {
        postService.disabledPost(postId);
        return new ResponseEntity<>("Post disable successfully", HttpStatus.OK);
    }

    // Enable a post by id
    @PutMapping("/posts/enable/{id}")
    public ResponseEntity<String> enablePost(@PathVariable("id") Long postId) {
        postService.enabledPost(postId);
        return new ResponseEntity<>("Post enable successfully", HttpStatus.OK);
    }

    // Get all posts of a course
    @GetMapping("/courses/{courseId}/posts")
    public ResponseEntity<List<Post>> getAllPostsByCourseId(@PathVariable(value = "courseId") Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course", "Id", courseId);
        }

        List<Post> posts = postRepository.findPostsByCourseId(courseId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Get all posts of a student
    @GetMapping("/students/{studentId}/posts")
    public ResponseEntity<List<Post>> getAllPostsByStudentId(@PathVariable(value = "studentId") Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student", "Id", studentId);
        }

        List<Post> posts = postRepository.findPostsByStudentId(studentId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Get all posts of a student and a course
    @GetMapping("/courses/{courseId}/students/{studentId}/posts")
    public ResponseEntity<List<Post>> getAllPostsByCourseIdAndStudentId(@PathVariable(value = "courseId") Long courseId,
                                                                        @PathVariable(value = "studentId") Long studentId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course", "Id", courseId);
        }

        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student", "Id", studentId);
        }

        List<Post> posts = postRepository.findPostsByCourseAndStudent(courseId, studentId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Get a post by id
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "id") Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    // Create a new post
    @PostMapping("/students/{studentId}/courses/{courseId}/posts")
    public ResponseEntity<Post> createPost(@PathVariable(value = "studentId") Long studentId,
                                           @PathVariable(value = "courseId") Long courseId,
                                           @RequestBody Post postRequest) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));

        postRequest.setStudent(student);
        postRequest.setCourse(course);

        Post post = postRepository.save(postRequest);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    /**
     * Update a post by a student,
     * a student only needs to edit title, tutorial and comment
     */
    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long postId,
                                           @RequestBody Post postRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        post.setTitle(postRequest.getTitle());
        post.setTutorial(postRequest.getTutorial());
        post.setComment(postRequest.getComment());

        return new ResponseEntity<>(postRepository.save(post), HttpStatus.OK);
    }

    // Send an application email of a post
    @GetMapping("students/{studentId}/posts/{postId}/apply")
    public ResponseEntity<String> applyPost(@PathVariable(value = "studentId") Long studentId,
                                            @PathVariable(value = "postId") Long postId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        emailService.apply(student, post);
        return new ResponseEntity<>("Successfully send application email!", HttpStatus.OK);
    }

}
