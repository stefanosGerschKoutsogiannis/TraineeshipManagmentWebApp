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
    @Column(name = "traineeship_id", nullable = false)
    private Long traineeshipId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "topics")
    private String topics;

    @Column(name = "slills")
    private String skills;

    @Column(name = "is_assigned")
    private boolean isAssigned;

    @Column(name = "student_logbook")
    private String studentLogbook;

    @Column(name = "pass_fail_grade")
    private boolean passFailGrade;

    @ManyToOne
    @JoinColumn(name = "student_student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "supervisor_professor_id")
    private Professor supervisor;

    @ManyToOne
    @JoinColumn(name = "company_company_id")
    private Company company;

    @OneToMany
    private List<Evaluation> evaluations;
}
