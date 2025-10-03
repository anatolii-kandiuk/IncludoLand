package com.includoland.includolandapp.domain;

import jakarta.persistence.*;

@Entity
@Table(name="teacher")
public class TeacherDetails {
    @Id
    @Column(name="user_id")
    private Long id;
    @Column(name="subject")
    private String subject;
    @Column(name="years_of_experience")
    private Integer yearsOfExperience;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

}
