package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "@id")
@Data
public class LessonDto implements Serializable {

    private static final long serialVersionUID = 3445783310084389795L;

    private Long id;
    private String title;
    private Integer day;
    private String time;
    private Long professorId;
    @EqualsAndHashCode.Exclude
    private List<Long> groupsIds;

    public List<Long> getGroupsIds() {
        if (groupsIds == null) {
            return new ArrayList<>();
        }
        return groupsIds;
    }
}