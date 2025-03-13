package com.stefanosgersch.traineeship.service.committee;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.domain.position_search.PositionSearchFactory;
import com.stefanosgersch.traineeship.domain.position_search.PositionSearchStrategy;
import com.stefanosgersch.traineeship.domain.supervisor_assignment.SupervisorAssignmentFactory;
import com.stefanosgersch.traineeship.domain.supervisor_assignment.SupervisorAssignmentStrategy;
import com.stefanosgersch.traineeship.repository.StudentRepository;
import com.stefanosgersch.traineeship.repository.TraineeshipPositionRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CommitteeMemberServiceImpl implements CommitteeMemberService {

    private final StudentRepository studentRepository;
    private final TraineeshipPositionRepository traineeshipPositionRepository;
    private final SupervisorAssignmentFactory supervisorAssignmentFactory;
    private final PositionSearchFactory positionSearchFactory;

    public CommitteeMemberServiceImpl(StudentRepository studentRepository,
                                      TraineeshipPositionRepository traineeshipPositionRepository,
                                      SupervisorAssignmentFactory supervisorAssignmentFactory,
                                      PositionSearchFactory positionSearchFactory) {
        this.studentRepository = studentRepository;
        this.traineeshipPositionRepository = traineeshipPositionRepository;
        this.supervisorAssignmentFactory = supervisorAssignmentFactory;
        this.positionSearchFactory = positionSearchFactory;
    }

    @Override
    public List<Student> retrieveTraineeshipApplicants() {
        return studentRepository.findAll()
                .stream()
                .filter(Student::isLookingForTraineeship)
                .toList();
    }

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
                    assignPositionToStudent(student, traineeshipPosition);
                }
            });
        });
    }

    @Override
    public void assignSupervisor(Long positionId, String strategy) {
        SupervisorAssignmentStrategy supervisorStrategy = supervisorAssignmentFactory.create(strategy);
        supervisorStrategy.assignProfessor(positionId);
    }

    @Override
    public List<TraineeshipPosition> retrievePositionsForApplicant(String applicantUsername, String strategy) {
        PositionSearchStrategy positionSearchStrategy = positionSearchFactory.create(strategy);
        return positionSearchStrategy.searchPositions(applicantUsername);
    }

    // if isAssigned == true then it is in progress
    @Override
    public List<TraineeshipPosition> getTraineeshipPositionsInProgress() {
        return traineeshipPositionRepository.findAll()
                .stream()
                .filter(TraineeshipPosition::isAssigned)
                .toList();
    }

    private void assignPositionToStudent(Student student, TraineeshipPosition traineeshipPosition) {
        student.setLookingForTraineeship(false);
        student.setAssignedTraineeshipPosition(traineeshipPosition);
        traineeshipPosition.setStudent(student);
        traineeshipPosition.setAssigned(true);

        traineeshipPositionRepository.save(traineeshipPosition);
        studentRepository.save(student);
    }
}
