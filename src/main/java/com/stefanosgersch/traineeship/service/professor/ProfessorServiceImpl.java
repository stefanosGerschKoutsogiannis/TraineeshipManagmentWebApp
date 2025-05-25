package com.stefanosgersch.traineeship.service.professor;

import com.stefanosgersch.traineeship.domain.Evaluation;
import com.stefanosgersch.traineeship.domain.EvaluationType;
import com.stefanosgersch.traineeship.domain.Professor;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.repository.ProfessorRepository;
import com.stefanosgersch.traineeship.service.auth.AuthService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;
    private final AuthService authService;

    public ProfessorServiceImpl(ProfessorRepository professorRepository, AuthService authService) {
        this.professorRepository = professorRepository;
        this.authService = authService;
    }

    @Override
    public Professor retrieveProfessorProfile(String username) {
        return professorRepository.findByUsername(username).get();
    }

    public void saveProfessorProfile(Professor professor) {
        Professor savedProfessor = retrieveProfessorProfile(authService.authenticateUser());
        savedProfessor.setProfessorName(professor.getProfessorName());
        savedProfessor.setInterests(professor.getInterests());
        professorRepository.save(savedProfessor);
    }

    @Override
    public List<TraineeshipPosition> retrieveAssignedPositions(String username) {
        return professorRepository.findByUsername(username)
                .map(Professor::getSupervisedPositions)
                .orElse(Collections.emptyList());
    }

    @Override
    public TraineeshipPosition evaluateAssignedPosition(Long positionId) {
        TraineeshipPosition positionToEval =  retrieveAssignedPositions(authService.authenticateUser())
                .stream()
                .filter(position -> position.getTraineeshipId().equals(positionId))
                .findFirst().get();
        if (isEvaluated(positionToEval)) {
            return null;
        }
        return positionToEval;
    }

    private boolean isEvaluated(TraineeshipPosition position) {
        Optional<Evaluation> professorEvaluation = position.getEvaluations()
                .stream()
                .filter(evaluation -> evaluation.getEvaluationType().equals(EvaluationType.PROFESSOR))
                .findFirst();
        if (professorEvaluation.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public void saveEvaluation(Long positionId, Evaluation evaluation) {
        TraineeshipPosition position = professorRepository.findAll().stream()
                .flatMap(professor -> professor.getSupervisedPositions().stream())
                .filter(p -> p.getTraineeshipId().equals(positionId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Position not found"));
        
        evaluation.setPosition(position);
        position.getEvaluations().add(evaluation);
        professorRepository.save(position.getSupervisor());
    }
}
