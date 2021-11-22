package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentDto implements Serializable {

    private Long id;
    private String firstName;
    private String secondName;
    private GroupDto group;
}
