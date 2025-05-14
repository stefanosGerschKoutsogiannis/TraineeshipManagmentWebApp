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
    private EvaluationType evaluationType;
    private int motivation;
    private int efficiency;
    private int effectiveness;
}
