package com.stefanosgersch.traineeship.service.professor;

import com.stefanosgersch.traineeship.domain.Professor;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfessorService {

    Professor retrieveProfessorProfile(String username);
    List<TraineeshipPosition> getSupervisedPositions(Long professorId);
}
