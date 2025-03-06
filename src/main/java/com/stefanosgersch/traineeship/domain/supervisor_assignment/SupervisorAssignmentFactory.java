package com.stefanosgersch.traineeship.domain.supervisor_assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupervisorAssignmentFactory {

    private final AssignmentBasedOnInterests assignmentBasedOnInterests;
    private final AssignmentBasedOnLoad assignmentBasedOnLoad;

    @Autowired
    public SupervisorAssignmentFactory(AssignmentBasedOnInterests assignmentBasedOnInterests,
                                       AssignmentBasedOnLoad assignmentBasedOnLoad) {
        this.assignmentBasedOnInterests = assignmentBasedOnInterests;
        this.assignmentBasedOnLoad = assignmentBasedOnLoad;
    }

    public SupervisorAssignmentStrategy create(String strategy) {
        switch (strategy) {
            case "interests":
                return assignmentBasedOnInterests;
            case "load":
                return assignmentBasedOnLoad;
            default:
                throw new IllegalArgumentException("Unknown strategy: " + strategy);
        }
    }
}
