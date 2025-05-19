package com.stefanosgersch.traineeship.domain.supervisor_assignment;

import com.stefanosgersch.traineeship.domain.Professor;
import com.stefanosgersch.traineeship.repository.ProfessorRepository;
import com.stefanosgersch.traineeship.repository.TraineeshipPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.stefanosgersch.traineeship.domain.algorithms.Utilities.calculateJaccardSimilarity;

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
        List<Professor> professors = professorRepository.findAll();
        Set<String> traineeshipTopics = Set.of(
                traineeshipPositionRepository.findById(positionId).get()
                        .getTopics()
                        .toLowerCase()
                        .split(",")
        );

        double threshold = 0.5;
        HashMap<Professor, Double> filteredProfessors = new HashMap<>();

        professors.forEach(professor -> {
            Set<String> professorInterests = Set.of(professor.getInterests().toLowerCase().split(","));
            double similarity = calculateJaccardSimilarity(professorInterests, traineeshipTopics);
            if (similarity >= threshold) {
                filteredProfessors.put(professor, similarity);
            }
        });

        return Collections.max(filteredProfessors.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
