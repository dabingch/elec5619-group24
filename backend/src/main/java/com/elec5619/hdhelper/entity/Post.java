package com.elec5619.hdhelper.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "posts")
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "tutorial")
    private String tutorial;

    @Column(nullable = false, name = "comment")
    private String comment;

    @Column(nullable = false, name = "disabled")
    private Boolean disabled = false;

    public Post(String title, String tutorial, String comment) {
        this.title = title;
        this.tutorial = tutorial;
        this.comment = comment;
    }
}
