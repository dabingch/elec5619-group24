package com.elec5619.hdhelper.service;

import com.elec5619.hdhelper.entity.Course;
import com.elec5619.hdhelper.entity.Post;
import com.elec5619.hdhelper.entity.Student;

import java.util.List;

public interface PostService {
//    Post savePost(Post post);
//    Post updatePost(Post post, Long id);
//    List<Post> findPostsByCourse(Course course);
//    List<Post> findPostsByStudent(Student student);
    List<Post> getAllPosts();
    void deletePost(Long id);
    void disabledPost(Long id);
    void enabledPost(Long id);
    void apply(Student student, Post post);
}
