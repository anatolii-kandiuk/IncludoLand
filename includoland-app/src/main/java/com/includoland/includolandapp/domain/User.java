package com.includoland.includolandapp.domain;

import com.includoland.includolandapp.constant.Role;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="first_name", length=100, nullable=false)
    private String firstName;
    @Column(name="last_name", length=100, nullable=false)
    private String lastName;
    @Column(name="username", length=100, nullable=false, unique=true)
    private String username;
    @Column(length=20)
    private String gender;
    @Column(name="email",  length=150, nullable=false, unique=true)
    private String email;
    @Column(nullable=false, length=255)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=30)
    private Role role;
    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name="created_at", updatable=false, insertable=false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    private TeacherDetails teacherDetails;
    @OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    private StudentDetails studentDetails;
}
