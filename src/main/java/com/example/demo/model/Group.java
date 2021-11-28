package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students_group")
@Data
public class Group implements Serializable {

    @Id
    @GeneratedValue(generator = "students_group_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "students_group_id", sequenceName = "group_seq", allocationSize = 1)
    @Column(name = "students_group_id")
    private Long id;

    @Column(name = "group_number")
    private String groupNumber;

    @OneToMany(mappedBy = "group", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<Student> students;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "lesson_students_group",
            joinColumns = {@JoinColumn(name = "students_group_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "lesson_id", nullable = false)})
    @EqualsAndHashCode.Exclude
    private List<Lesson> lessons;

    public List<Student> getStudents() {
        if (students == null) {
            return new ArrayList<>();
        }
        return students;
    }

    public List<Lesson> getLessons() {
        if (lessons == null) {
            return new ArrayList<>();
        }
        return lessons;
    }
}
