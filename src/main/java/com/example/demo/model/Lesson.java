package com.example.demo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "lesson")
@EqualsAndHashCode(exclude = "groups")
public class Lesson implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "lesson_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "lesson_students_group",
            joinColumns = {@JoinColumn(name = "lesson_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "students_group_id", nullable = false)})
//    @JsonIgnoreProperties("lessons")
    private List<Group> groups;

    @ManyToOne(cascade = {PERSIST, MERGE})
    @JoinColumn(name = "professor_id", referencedColumnName = "professor_id")
    private Professor professor;
}
