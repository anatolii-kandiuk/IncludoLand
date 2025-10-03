package com.includoland.includolandapp.domain;

import jakarta.persistence.*;

@Entity
@Table(name="student")
public class StudentDetails {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name="grade", length = 50)
    private String grade;
    @Column(name = "parent_contact", length = 150)
    private String parentContact;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
