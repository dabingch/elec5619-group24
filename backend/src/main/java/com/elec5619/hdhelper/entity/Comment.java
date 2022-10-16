package com.elec5619.hdhelper.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key
    private Long id;

    @ManyToOne(targetEntity = Course.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(nullable = false, name = "course_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Course course;

    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(nullable = false, name = "student_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;

    @Column(name = "course_comment")
    private String courseComment;

    @Column(name = "lecturer_comment")
    private String lecturerComment;

    @Column(name = "tutor_comment")
    private String tutorComment;

    @Column(name = "rating")
    private Integer rating;

}
