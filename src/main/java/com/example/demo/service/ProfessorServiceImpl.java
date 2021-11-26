package com.example.demo.service;

import com.example.demo.converter.CycleAvoidingMappingContext;
import com.example.demo.converter.ProfessorMapper;
import com.example.demo.dto.ProfessorDto;
import com.example.demo.exception.NoSuchEntityException;
import com.example.demo.model.Professor;
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

    private final ProfessorMapper professorMapper;
    private final CycleAvoidingMappingContext context;
    private final ProfessorRepository repository;

    @Override
    public ProfessorDto create(ProfessorDto professor) {
        log.info("save professor");
        Professor entity = professorMapper.toEntity(professor, context);

        return professorMapper.toDto(repository.save(entity), context);
    }

    @Override
    public List<ProfessorDto> findAll() {
        log.info("findall professor");

        return repository.findAll()
                .stream()
                .map(p -> professorMapper.toDto(p, context))
                .collect(Collectors.toList());
    }

    @Override
    public ProfessorDto update(ProfessorDto professor, Long id) {
        Professor found = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("No professor found"));
        log.info("professor found {}", found.toString());
        Professor entity = professorMapper.toEntity(professor, context);
        entity.setId(id);
        return professorMapper.toDto(repository.save(entity), context);
    }

    @Override
    public ProfessorDto findById(Long id) {
        log.info("find by id {} professor", id);

        Professor found = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("No professor found"));
        return professorMapper.toDto(found, context);
    }

    @Override
    public void delete(Long id) {
        log.info("delete with id {} professor", id);

        repository.deleteById(id);
    }
}
