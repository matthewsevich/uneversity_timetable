package com.example.demo.service;

import com.example.demo.converter.ProfessorConverter;
import com.example.demo.dto.LessonDto;
import com.example.demo.dto.ProfessorDto;
import com.example.demo.exception.ContainEqualObject;
import com.example.demo.exception.NoSuchEntityException;
import com.example.demo.model.Lesson;
import com.example.demo.model.Professor;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository repository;
    private final LessonRepository lessonRepository;
    private final ProfessorConverter professorConverter;

    @Override
    public ProfessorDto create(ProfessorDto professor) {
        log.info("save professor");
        Professor entity = professorConverter.toEntity(professor);
        return fillAndSaveEntity(professor, entity);
    }


    private ProfessorDto fillAndSaveEntity(ProfessorDto dto, Professor entity) {
        List<Long> ids = dto.getLessonIds();
        List<Lesson> allById = lessonRepository.findAllById(ids);
        entity.setLessons(allById);
        return professorConverter.toDto(repository.save(entity));
    }

    @Override
    public List<ProfessorDto> findAll() {
        log.info("findAll professor");
        return repository.findAll()
                .stream()
                .map(professorConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProfessorDto update(ProfessorDto dto, Long id) {
        Professor found = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("No professor found"));
        log.info("professor found {}", found.toString());
        Professor entity = professorConverter.toEntity(dto);
        entity.setId(id);
        return fillAndSaveEntity(dto, entity);
    }

    @Override
    public ProfessorDto findById(Long id) {
        log.info("find by id {} professor", id);

        Professor found = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("No professor found"));
        return professorConverter.toDto(found);
    }

    @Override
    public void delete(Long id) {
        log.info("delete with id {} professor", id);
        repository.deleteById(id);
    }

    @Override
    public ProfessorDto addLesson(Long id, LessonDto dto) {

        Professor professor = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("No professor found"));
        Lesson lesson = lessonRepository.findById(dto.getId()).orElseThrow(() -> new NoSuchEntityException("No lesson found"));
        List<Lesson> professorLessons = professor.getLessons();
        if (!checkLessonPossibility(professorLessons, lesson)) {
            throw new ContainEqualObject("Professor already have this lesson");
        }
        professorLessons.add(lesson);
        lesson.setProfessor(professor);
        professor.setLessons(professorLessons);
        return professorConverter.toDto(repository.save(professor));
    }

    private Boolean checkLessonPossibility(List<Lesson> professorsLessons, Lesson lesson) {
        if (professorsLessons.contains(lesson)) {
            return false;
        }
        for (Lesson profLesson : professorsLessons) {
            if (profLesson.getDay().equals(lesson.getDay()) && profLesson.getTime().equals(lesson.getTime()))
                return false;
        }
        return true;
    }
}
