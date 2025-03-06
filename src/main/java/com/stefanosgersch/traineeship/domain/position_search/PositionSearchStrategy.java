package com.stefanosgersch.traineeship.domain.position_search;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;

import java.util.List;

public interface PositionSearchStrategy {

    List<TraineeshipPosition> searchPositions(Student student);
}
