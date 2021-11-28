package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentDto implements Serializable {

    private static final long serialVersionUID = -1186143345779037858L;

    private Long id;
    private String firstName;
    private String lastName;
    private Long groupId;
}
