package com.stefanosgersch.traineeship.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "traineeship_position")
public class TraineeshipPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long traineeshipId;

    private String title;
    private String description;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String topics;
    private String skills;
    private boolean isAssigned;
    private String studentLogbook;
    private boolean passFailGrade;

    @OneToOne
    private Student student;

    @ManyToOne
    private Professor supervisor;

    @ManyToOne
    private Company company;

    @OneToMany()
    private List<Evaluation> evaluations;
}
