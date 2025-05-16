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
@PrimaryKeyJoinColumn(name = "professor_username")
public class Professor  extends User {

    private String professorName;

    private String interests;

    @OneToMany(mappedBy = "supervisor")
    private List<TraineeshipPosition> supervisedPositions;

}
