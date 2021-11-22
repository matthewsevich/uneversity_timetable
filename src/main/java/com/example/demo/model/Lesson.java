package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
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
    private List<Group> groups;

}
