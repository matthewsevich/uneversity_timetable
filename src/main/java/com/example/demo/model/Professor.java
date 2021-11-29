package com.example.demo.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "professor")
@Data
public class Professor implements Serializable {

    @Id
    @GeneratedValue(generator = "professor_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "professor_id", sequenceName = "prof_seq", allocationSize = 1)
    @Column(name = "professor_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "professor", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<Lesson> lessons;

    public List<Lesson> getLessons() {
        if (lessons == null) {
            return new ArrayList<>();
        }
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
        if (lessons != null) {
            for (Lesson lesson : lessons) {
                if (lesson.getProfessor() != this) {
                    lesson.setProfessor(this);
                }
            }
        }
    }
}
