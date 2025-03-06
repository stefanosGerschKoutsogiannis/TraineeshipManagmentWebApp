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
        name = "company",
        uniqueConstraints = {
                @UniqueConstraint(name = "email_unique", columnNames = "email")
        }
)
public class Company {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyId;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name="company_name")
    private String companyName;

    @Column(name="company_location")
    private String companyLocation;

    @OneToMany
    private List<TraineeshipPosition> positions;
}
