package com.stefanosgersch.traineeship.service.committee;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommitteeMemberService {

    List<Student> getStudentsThatAppliedForTraineeship();
    void assignStudentToTraineeship(Long studentId, Long traineeshipId);
    public List<TraineeshipPosition> getTraineeshipPositionsInProgress();
}
