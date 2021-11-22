package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "student")
@Data
public class Student implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(cascade = {PERSIST, MERGE})
    @JoinColumn(name = "students_group_id", referencedColumnName = "students_group_id")
    @JsonIgnoreProperties("students")
    private Group group;


}
