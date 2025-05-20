package com.stefanosgersch.traineeship.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "student_username")
public class Student extends User {

    private String studentName;
    private String academicId;
    private double averageGrade;
    private String preferredLocation;
    private String interests;
    private String skills;
    private boolean lookingForTraineeship;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private TraineeshipPosition assignedTraineeshipPosition;
}
