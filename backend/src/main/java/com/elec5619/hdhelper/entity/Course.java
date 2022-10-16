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
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key
    private Long id;

    @Column(name = "course_name", unique = true)
    private String courseName;
    
    @Column(name = "enabled")
    private Boolean enabled = false;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "lecturer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Lecturer lecturer = null;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tutor_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Tutor tutor = null;

    @Column
    private Integer numberOfEachGroup;

    public Course(String courseName, Integer numberOfEachGroup) {
        this.courseName = courseName;
        this.numberOfEachGroup = numberOfEachGroup;
    }
}
