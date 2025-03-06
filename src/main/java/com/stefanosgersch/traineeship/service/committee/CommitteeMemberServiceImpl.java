package com.stefanosgersch.traineeship.service.committee;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.repository.StudentRepository;
import com.stefanosgersch.traineeship.repository.TraineeshipPositionRepository;


import java.util.List;

public class CommitteeMemberServiceImpl implements CommitteeMemberService {

    private final StudentRepository studentRepository;
    private final TraineeshipPositionRepository traineeshipPositionRepository;

    public CommitteeMemberServiceImpl(StudentRepository studentRepository,
                                      TraineeshipPositionRepository traineeshipPositionRepository) {
        this.studentRepository = studentRepository;
        this.traineeshipPositionRepository = traineeshipPositionRepository;
    }

    // US16
    @Override
    public List<Student> getStudentsThatAppliedForTraineeship() {
        return studentRepository.findAll()
                .stream()
                .filter(Student::isLookingForTraineeship)
                .toList();
    }

    // US18
    /**
     * 1. find student and set lookingForTraineeship to false
     * 2. assign the traineeship to the student
     * 3. update traineeship student
     * 3. save the student*
     * might face a problem, must throw something back
     */
    @Override
    public void assignPosition(Long positionId, String studentUsername) {
        studentRepository.findByUsername(studentUsername).ifPresent(student -> {
            traineeshipPositionRepository.findById(positionId).ifPresent(traineeshipPosition -> {
                if (!traineeshipPosition.isAssigned()) {
                    student.setLookingForTraineeship(false);
                    student.setAssignedTraineeshipPosition(traineeshipPosition);
                    traineeshipPosition.setStudent(student);
                    traineeshipPosition.setAssigned(true);

                    traineeshipPositionRepository.save(traineeshipPosition);
                    studentRepository.save(student);
                }
            });
        });
    }

    public void assignSupervisor(Long positionId, String strategy) {
        return;
    }

    // US20
    // if isAssigned == true then it is in progress
    @Override
    public List<TraineeshipPosition> getTraineeshipPositionsInProgress() {
        return traineeshipPositionRepository.findAll()
                .stream()
                .filter(TraineeshipPosition::isAssigned)
                .toList();
    }

}
