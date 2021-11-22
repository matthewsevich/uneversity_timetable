package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "students_group")
@Data
@EqualsAndHashCode(exclude = {"students", "lessons"})
public class Group implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "students_group_id")
    private Long id;

    @Column(name = "group_number")
    private String groupNumber;

    @OneToMany(mappedBy = "group", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("group")
    private List<Student> students;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "lesson_students_group",
            joinColumns = {@JoinColumn(name = "students_group_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "lesson_id", nullable = false)})
    private List<Lesson> lessons;
}
