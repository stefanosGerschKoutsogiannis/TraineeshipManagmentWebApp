package com.stefanosgersch.traineeship.service.committee;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.domain.Professor;
import com.stefanosgersch.traineeship.domain.position_search.PositionSearchFactory;
import com.stefanosgersch.traineeship.domain.position_search.PositionSearchStrategy;
import com.stefanosgersch.traineeship.domain.supervisor_assignment.SupervisorAssignmentFactory;
import com.stefanosgersch.traineeship.domain.supervisor_assignment.SupervisorAssignmentStrategy;
import com.stefanosgersch.traineeship.repository.StudentRepository;
import com.stefanosgersch.traineeship.repository.TraineeshipPositionRepository;
import com.stefanosgersch.traineeship.repository.ProfessorRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CommitteeMemberServiceImpl implements CommitteeMemberService {

    private final StudentRepository studentRepository;
    private final TraineeshipPositionRepository traineeshipPositionRepository;
    private final SupervisorAssignmentFactory supervisorAssignmentFactory;
    private final PositionSearchFactory positionSearchFactory;
    private final ProfessorRepository professorRepository;

    public CommitteeMemberServiceImpl(StudentRepository studentRepository,
                                      TraineeshipPositionRepository traineeshipPositionRepository,
                                      SupervisorAssignmentFactory supervisorAssignmentFactory,
                                      PositionSearchFactory positionSearchFactory,
                                      ProfessorRepository professorRepository) {
        this.studentRepository = studentRepository;
        this.traineeshipPositionRepository = traineeshipPositionRepository;
        this.supervisorAssignmentFactory = supervisorAssignmentFactory;
        this.positionSearchFactory = positionSearchFactory;
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Student> retrieveTraineeshipApplicants() {
        return studentRepository.findAll()
                .stream()
                .filter(Student::isLookingForTraineeship)
                .toList();
    }
    // store also traineeship position student, or will it cascade?
    @Override
    public void assignPosition(Long positionId, String studentUsername) {
        studentRepository.findByUsername(studentUsername).ifPresent(student -> {
            TraineeshipPosition position = traineeshipPositionRepository.findById(positionId)
                .orElseThrow(() -> new RuntimeException("Position not found"));
            
            student.setLookingForTraineeship(false);
            student.setAssignedTraineeshipPosition(position);
            position.setAssigned(true);
            position.setStudent(student);
            
            studentRepository.save(student);
            traineeshipPositionRepository.save(position);
        });
    }

    @Override
    public void assignSupervisor(Long positionId, String strategy) {
        SupervisorAssignmentStrategy supervisorStrategy = supervisorAssignmentFactory.create(strategy);
        Professor assignedProfessor = supervisorStrategy.assignProfessor(positionId);
        
        TraineeshipPosition position = traineeshipPositionRepository.findById(positionId)
            .orElseThrow(() -> new RuntimeException("Position not found"));
        
        position.setSupervisor(assignedProfessor);
        assignedProfessor.getSupervisedPositions().add(position);
        
        traineeshipPositionRepository.save(position);
        professorRepository.save(assignedProfessor);
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
}
