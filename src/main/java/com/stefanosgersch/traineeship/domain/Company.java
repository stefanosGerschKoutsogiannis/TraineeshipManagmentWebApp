package com.stefanosgersch.traineeship.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "company")
@PrimaryKeyJoinColumn(name = "company_username")
public class Company extends User {


    private String companyName;
    private String companyLocation;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<TraineeshipPosition> positions;
}
