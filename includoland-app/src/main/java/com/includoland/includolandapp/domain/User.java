package com.includoland.includolandapp.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(schema-"includo_land", name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String gender;
    private String email;
    private String password;
    private LocalDateTime dateOfBirth;
    private LocalDateTime createdAt;

}
