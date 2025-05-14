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
@Table(name = "professor")
@PrimaryKeyJoinColumn(name = "professor_id")
public class Professor extends User {

    @Column(name = "professor_name")
    private String professorName;

    @Column(name = "interests")
    private String interests;

    @OneToMany()
    private List<TraineeshipPosition> supervisedPositions;

}
