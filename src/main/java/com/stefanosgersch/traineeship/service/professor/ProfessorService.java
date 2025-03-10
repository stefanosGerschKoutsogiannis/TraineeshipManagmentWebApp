package com.stefanosgersch.traineeship.service.professor;

import com.stefanosgersch.traineeship.domain.Evaluation;
import com.stefanosgersch.traineeship.domain.Professor;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfessorService {

    Professor retrieveProfessorProfile(String username);
    void saveProfessorProfile(Professor professor);
    List<TraineeshipPosition> getSupervisedPositions(Long professorId);
    void evaluateAssignedPosition(Long positionId);
    void saveEvaluation(Long positionId, Evaluation evaluation);

}
