package com.stefanosgersch.traineeship.domain.supervisor_assignment;

import com.stefanosgersch.traineeship.domain.Professor;
import com.stefanosgersch.traineeship.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssignmentBasedOnLoad implements SupervisorAssignmentStrategy {

    private final ProfessorRepository professorRepository;

    @Autowired
    public AssignmentBasedOnLoad(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public Professor assignProfessor(Professor professor) {
        return null;
    }
}
