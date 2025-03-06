package com.stefanosgersch.traineeship.domain.supervisor_assignment;

import com.stefanosgersch.traineeship.domain.Professor;
import com.stefanosgersch.traineeship.repository.ProfessorRepository;
import com.stefanosgersch.traineeship.repository.TraineeshipPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssignmentBasedOnInterests implements SupervisorAssignmentStrategy {


    private final ProfessorRepository professorRepository;
    private final TraineeshipPositionRepository traineeshipPositionRepository;

    @Autowired
    public AssignmentBasedOnInterests(ProfessorRepository professorRepository,
                                 TraineeshipPositionRepository traineeshipPositionRepository) {
        this.professorRepository = professorRepository;
        this.traineeshipPositionRepository = traineeshipPositionRepository;
    }

    @Override
    public Professor assignProfessor(Long positionId) {
        return null;
    }
}
