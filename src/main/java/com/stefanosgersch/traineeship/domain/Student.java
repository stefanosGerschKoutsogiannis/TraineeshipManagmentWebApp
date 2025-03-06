package com.stefanosgersch.traineeship.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        uniqueConstraints = {@UniqueConstraint(name = "email_unique", columnNames = "email")}
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_id_number")
    private String studentIdNumber;

    @Column(name = "average_grade")
    private double averageGrade;

    @Column(name = "preferred_location")
    private String preferredLocation;

    @Column(name = "interests")
    private String interests;

    @Column(name = "skills")
    private String skills;

    @Column(name = "looking_for_traineeship")
    private boolean lookingForTraineeship;

    @OneToOne
    private TraineeshipPosition assignedTraineeshipPosition;
}
