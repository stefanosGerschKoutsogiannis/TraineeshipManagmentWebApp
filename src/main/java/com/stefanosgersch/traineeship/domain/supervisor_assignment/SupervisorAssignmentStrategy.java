package com.stefanosgersch.traineeship.domain.supervisor_assignment;

import com.stefanosgersch.traineeship.domain.Professor;

public interface SupervisorAssignmentStrategy {

    Professor assignProfessor(Long positionId);
}
