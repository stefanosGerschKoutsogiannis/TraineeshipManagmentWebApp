package com.stefanosgersch.traineeship.DTO;

import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentDTO {

    private String studentName;
    private String email;
    private String academicId;
    private double averageGrade;
    private String preferredLocation;
    private String interests;
    private String skills;
    private boolean lookingForTraineeship = false;
    private TraineeshipPosition assignedTraineeshipPosition;
}
