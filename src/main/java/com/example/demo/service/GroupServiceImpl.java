package com.example.demo.service;

import com.example.demo.converter.GroupConverter;
import com.example.demo.dto.GroupDto;
import com.example.demo.exception.NoSuchEntityException;
import com.example.demo.model.Group;
import com.example.demo.model.Lesson;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupConverter converter;
    private final GroupRepository groupRepository;
    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;
    private final GroupConverter groupConverter;


    @Override
    public GroupDto getGroupById(Long id) {
        log.info("calling getGroupById");
        Group byId = groupRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("no group found"));
        return converter.toDto(byId);
    }

    @Override
    public List<GroupDto> getAll() {
        log.info("calling getAllGroups");
        List<Group> groups = groupRepository.findAll();
        return groups
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GroupDto saveGroup(GroupDto dto) {
        log.info("calling save group");
        Group group = converter.toEntity(dto);
        return fillAndSaveEntity(dto, group);
    }

    @Override
    public GroupDto update(GroupDto dto, Long id) {
        log.info("calling update group");
        Group group1 = groupRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("no group found to update"));
        log.info("group found - {}", group1.toString());
        Group group = groupConverter.toEntity(dto);
        group.setId(id);
        return fillAndSaveEntity(dto, group);
    }

    private GroupDto fillAndSaveEntity(GroupDto dto, Group entity) {
        List<Long> lessonsIds = dto.getLessonsIds();
        List<Long> studentsIds = dto.getStudentsIds();
        if (dto.getStudentsIds() != null) {
            entity.setStudents(studentRepository.findAllById(studentsIds));
        }
        if (dto.getLessonsIds() != null) {
            entity.setLessons(lessonRepository.findAllById(lessonsIds));
        }
        return groupConverter.toDto(groupRepository.save(entity));
    }

    @Override
    public void deleteGroup(Long id) {
        log.info("call delete group with id {}", id);
        groupRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addLessonToGroup(Long id, Long lessonId) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("No group found"));
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new NoSuchEntityException("No lesson found"));
        group.getLessons().add(lesson);
        lesson.getGroups().add(group);
        groupRepository.save(group);
    }
}
