package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class GroupDto implements Serializable {

    private Long id;
    private Set<StudentDto> students;
    private List<LessonDto> lessons;
}
