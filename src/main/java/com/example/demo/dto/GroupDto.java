package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

//@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "@id")
@Data
public class GroupDto implements Serializable {

    private static final long serialVersionUID = -3203462416500415574L;

    private Long id;
    private String groupNumber;
    @EqualsAndHashCode.Exclude
    private List<Long> studentsIds;
    @EqualsAndHashCode.Exclude
    private List<Long> lessonsIds;

}
