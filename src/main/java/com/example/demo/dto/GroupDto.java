package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "@id")

public class GroupDto implements Serializable {

    private static final long serialVersionUID = -3203462416500415574L;

    private Long id;

    private Set<StudentDto> students;

    private List<LessonDto> lessons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentDto> students) {
        this.students = students;
    }

    public List<LessonDto> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonDto> lessons) {
        this.lessons = lessons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDto groupDto = (GroupDto) o;
        return id.equals(groupDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
