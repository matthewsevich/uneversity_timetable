package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "@id")

public class LessonDto implements Serializable {

    private static final long serialVersionUID = 3445783310084389795L;

    private Long id;
    private String title;
    private Set<GroupDto> groups;
    private ProfessorDto professor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<GroupDto> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupDto> groups) {
        this.groups = groups;
    }

    public ProfessorDto getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorDto professor) {
        this.professor = professor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonDto lessonDto = (LessonDto) o;
        return Objects.equals(id, lessonDto.id) && Objects.equals(title, lessonDto.title) && Objects.equals(professor, lessonDto.professor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, professor);
    }
}
