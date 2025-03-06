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
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "evaluation_id")
    private Long evaluationId;

    //private String evaluationType;  // change this

    @Column(name = "motivation")
    private int motivation;

    @Column(name = "efficiency")
    private int efficiency;

    @Column(name = "effectiveness")
    private int effectiveness;
}
