package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "@id")
public class ProfessorDto {

    private Long id;
    private String firstName;
    private String lastName;
    private List<LessonDto> lessons;

}
