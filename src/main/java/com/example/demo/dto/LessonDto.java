package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class LessonDto implements Serializable {

    private Long id;
    private String title;
    private Set<GroupDto> groups;
}
