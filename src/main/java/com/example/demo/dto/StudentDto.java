package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class StudentDto implements Serializable {

    private static final long serialVersionUID = -1186143345779037858L;

    private Long id;
    private String firstName;
    private String lastName;
    //    @JsonBackReference
    @JsonIgnoreProperties("students")
    private GroupDto group;
}
