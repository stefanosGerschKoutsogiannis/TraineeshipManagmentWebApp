package com.stefanosgersch.traineeship.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long traineeshipId;

    private String title;
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    private String topics;
    private String skills;
    private boolean isAssigned;
    private String studentLogbook;
    private boolean passFailGrade;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_username")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "professor_username")
    private Professor supervisor;

    @ManyToOne
    @JoinColumn(name = "company_username")
    private Company company;

    @OneToMany(mappedBy = "position")
    private List<Evaluation> evaluations;
}
