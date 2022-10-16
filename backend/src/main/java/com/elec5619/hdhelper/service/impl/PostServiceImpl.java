package com.elec5619.hdhelper.service.impl;

import com.elec5619.hdhelper.entity.Course;
import com.elec5619.hdhelper.entity.Post;
import com.elec5619.hdhelper.entity.Student;
import com.elec5619.hdhelper.exception.CustomException;
import com.elec5619.hdhelper.exception.ResourceNotFoundException;
import com.elec5619.hdhelper.repository.PostRepository;
import com.elec5619.hdhelper.service.EmailService;
import com.elec5619.hdhelper.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private EmailService emailService;

//    @Override
//    public Post savePost(Post post) {
//        if(Objects.nonNull(post)){
//            return postRepository.save(post);
//        } else {
//            throw new CustomException("no group info");
//        }
//    }
//
//    @Override
//    public Post updatePost(Post post, Long id) {
//        Post existingPost = postRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("Post", "Id", id));
//        existingPost.setTitle(post.getTitle());
//        existingPost.setTutorial(post.getTutorial());
//        existingPost.setTutor(post.getTutor());
//        return postRepository.save(existingPost);
//    }

//    @Override
//    public List<Post> findPostsByCourse(Course course) {
//        List<Post> posts = postRepository.findPostsByCourse(course);
//        List<Post> result = new ArrayList<>();
//        for (Post post : posts
//             ) {
//            if(Objects.nonNull(post)) {
//                if(!post.getDisabled()) {
//                    result.add(post);
//                }
//            }
//        }
//        return result;
//    }

//    @Override
//    public List<Post> findPostsByStudent(Student student) {
//        return postRepository.findPostsByStudent(student);
//    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void disabledPost(Long id) {
        Post existingPost = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post", "Id", id));
        existingPost.setDisabled(true);
        postRepository.save(existingPost);

    }

    @Override
    public void enabledPost(Long id) {
        Post existingPost = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post", "Id", id));
        existingPost.setDisabled(false);
        postRepository.save(existingPost);
    }

    @Override
    public void apply(Student student, Post post) {
        emailService.apply(student, post);
    }
}
