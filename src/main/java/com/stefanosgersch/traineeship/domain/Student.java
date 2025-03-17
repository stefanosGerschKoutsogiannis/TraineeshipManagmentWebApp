package com.stefanosgersch.traineeship.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "academic_id")
    private String academicId;

    @Column(name = "average_grade")
    private double averageGrade;

    @Column(name = "preferred_location")
    private String preferredLocation;

    @Column(name = "interests")
    private String interests;

    @Column(name = "skills")
    private String skills;

    @Column(name = "looking_for_traineeship")
    private boolean lookingForTraineeship = false;

    @OneToOne
    @JoinColumn(name = "traineeship_position_id")
    private TraineeshipPosition assignedTraineeshipPosition;
}
