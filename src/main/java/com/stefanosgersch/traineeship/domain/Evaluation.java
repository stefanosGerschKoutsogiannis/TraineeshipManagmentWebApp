package com.stefanosgersch.traineeship.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long evaluationId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EvaluationType evaluationType;  // change this

    @Column(name = "motivation", nullable = false)
    private int motivation;

    @Column(name = "efficiency", nullable = false)
    private int efficiency;

    @Column(name = "effectiveness", nullable = false)
    private int effectiveness;
}
