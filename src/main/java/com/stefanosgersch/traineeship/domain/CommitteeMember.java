package com.stefanosgersch.traineeship.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        uniqueConstraints = {@UniqueConstraint(name = "email_unique", columnNames = "email")}
)
public class CommitteeMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "committee_member_id")
    private Long committeeMemberId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;


}
