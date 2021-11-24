package com.example.demo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "students_group")
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"students", "lessons"})
public class Group implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "students_group_id")
    private Long id;

    @Column(name = "group_number")
    private String groupNumber;

    @OneToMany(mappedBy = "group", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    @JsonIgnoreProperties("group")
    private List<Student> students;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "lesson_students_group",
            joinColumns = {@JoinColumn(name = "students_group_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "lesson_id", nullable = false)})
//    @JsonIgnoreProperties("groups")
    private List<Lesson> lessons;
}
