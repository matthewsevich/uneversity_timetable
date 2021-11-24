package com.example.demo.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "professor")
@Getter
@Setter
@RequiredArgsConstructor
public class Professor {

    @Id
    @GeneratedValue
    @Column(name = "professor_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "professor", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Lesson> lessons;
}
