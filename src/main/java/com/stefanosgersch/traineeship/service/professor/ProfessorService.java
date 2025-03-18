package com.stefanosgersch.traineeship.service.professor;

import com.stefanosgersch.traineeship.domain.Evaluation;
import com.stefanosgersch.traineeship.domain.Professor;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProfessorService {

    Professor retrieveProfessorProfile(String username);
    void saveProfessorProfile(Professor professor);
    List<TraineeshipPosition> retrieveAssignedPositions(String username);
    void evaluateAssignedPosition(Long positionId);
    void saveEvaluation(Long positionId, Evaluation evaluation);

}
