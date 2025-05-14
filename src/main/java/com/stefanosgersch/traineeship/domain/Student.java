package com.stefanosgersch.traineeship.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "student_id")
public class Student extends User {


    private String studentName;
    private String academicId;
    private double averageGrade;
    private String preferredLocation;
    private String interests;
    private String skills;
    private boolean lookingForTraineeship;

    @OneToOne
    @JoinColumn(name = "traineeship_position_id")
    private TraineeshipPosition assignedTraineeshipPosition;
}
