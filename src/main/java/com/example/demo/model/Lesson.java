package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Data
@Entity
@Table(name = "lesson")
public class Lesson implements Serializable {

    @Id
    @GeneratedValue(generator = "lesson_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "lesson_id", sequenceName = "less_seq", allocationSize = 1)
    @Column(name = "lesson_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "day")
    private Integer day;

    @Column(name = "time")
    private String time;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "lesson_students_group",
            joinColumns = {@JoinColumn(name = "lesson_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "students_group_id", nullable = false)})
    @EqualsAndHashCode.Exclude
    private List<Group> groups;

    @ManyToOne(cascade = {PERSIST, MERGE})
    @JoinColumn(name = "professor_id", referencedColumnName = "professor_id")
    private Professor professor;

    public List<Group> getGroups() {
        if (groups == null) {
            return new ArrayList<>();
        }
        return groups;
    }
}

