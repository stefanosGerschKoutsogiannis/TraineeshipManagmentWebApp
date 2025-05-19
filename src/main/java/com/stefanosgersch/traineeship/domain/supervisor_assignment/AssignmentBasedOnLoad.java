package com.stefanosgersch.traineeship.domain.supervisor_assignment;

import com.stefanosgersch.traineeship.domain.Professor;
import com.stefanosgersch.traineeship.repository.ProfessorRepository;
import com.stefanosgersch.traineeship.repository.TraineeshipPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class AssignmentBasedOnLoad implements SupervisorAssignmentStrategy {

    private final ProfessorRepository professorRepository;
    private final TraineeshipPositionRepository traineeshipPositionRepository;

    @Autowired
    public AssignmentBasedOnLoad(ProfessorRepository professorRepository,
                                 TraineeshipPositionRepository traineeshipPositionRepository) {
        this.professorRepository = professorRepository;
        this.traineeshipPositionRepository = traineeshipPositionRepository;
    }


    @Override
    public Professor assignProfessor(Long positionId) {
        TreeSet<Professor> professors = new TreeSet<>((o1, o2) -> {
            return o1.getSupervisedPositions().size() >= o2.getSupervisedPositions().size() ? 1 : -1;   // should return int
        });
        professorRepository.findAll().forEach(professor -> {
            professors.add(professor);
        });
        return professors.last();
    }

}
