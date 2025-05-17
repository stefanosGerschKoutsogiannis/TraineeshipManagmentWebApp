package com.stefanosgersch.traineeship.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    private List<Evaluation> evaluations;
}
