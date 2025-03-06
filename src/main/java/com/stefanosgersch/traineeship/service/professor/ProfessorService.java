package com.stefanosgersch.traineeship.service.professor;

import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfessorService {

    List<TraineeshipPosition> getSupervisedPositions(Long professorId);
}
