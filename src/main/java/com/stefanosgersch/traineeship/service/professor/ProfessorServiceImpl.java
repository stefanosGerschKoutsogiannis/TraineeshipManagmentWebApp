package com.stefanosgersch.traineeship.service.professor;

import com.stefanosgersch.traineeship.domain.Professor;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.repository.ProfessorRepository;

import java.util.Collections;
import java.util.List;

public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public List<TraineeshipPosition> getSupervisedPositions(Long professorId) {
        return professorRepository.findById(professorId)
                .map(Professor::getSupervisedPositions)
                .orElse(Collections.emptyList());
    }


}
