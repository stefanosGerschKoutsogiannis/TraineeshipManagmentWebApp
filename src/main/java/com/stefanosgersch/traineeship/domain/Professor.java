package com.stefanosgersch.traineeship.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name = "professor",
        uniqueConstraints = {@UniqueConstraint(name = "email_unique", columnNames = "email")}
)
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long professorId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "professor_name")
    private String professorName;

    @Column(name = "interests")
    private String interests;

    @OneToMany
    private List<TraineeshipPosition> supervisedPositions;
}
