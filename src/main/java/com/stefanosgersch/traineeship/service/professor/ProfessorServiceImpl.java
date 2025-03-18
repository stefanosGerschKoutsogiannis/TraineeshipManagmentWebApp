package com.stefanosgersch.traineeship.service.professor;

import com.stefanosgersch.traineeship.domain.Evaluation;
import com.stefanosgersch.traineeship.domain.Professor;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public Professor retrieveProfessorProfile(String username) {
        return professorRepository.findByUsername(username).get();
    }

    public void saveProfessorProfile(Professor professor) {
        professorRepository.save(professor);
    }

    @Override
    public List<TraineeshipPosition> retrieveAssignedPositions(String username) {
        return professorRepository.findByUsername(username)
                .map(Professor::getSupervisedPositions)
                .orElse(Collections.emptyList());
    }

    @Override
    public void evaluateAssignedPosition(Long positionId) {
        return;
    }

    @Override
    public void saveEvaluation(Long positionId, Evaluation evaluation) {

    }


}
