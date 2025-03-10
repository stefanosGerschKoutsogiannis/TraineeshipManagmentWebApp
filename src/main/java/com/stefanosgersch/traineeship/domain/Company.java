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
@PrimaryKeyJoinColumn(name = "user_id")
public class Company extends User {

    @Column(name="company_name")
    private String companyName;

    @Column(name="company_location")
    private String companyLocation;

    @OneToMany
    private List<TraineeshipPosition> positions;
}
