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
@Table(name = "company")
@PrimaryKeyJoinColumn(name = "company_username")
public class Company extends User {


    private String companyName;
    private String companyLocation;

    @OneToMany(mappedBy = "company")
    private List<TraineeshipPosition> positions;
}
