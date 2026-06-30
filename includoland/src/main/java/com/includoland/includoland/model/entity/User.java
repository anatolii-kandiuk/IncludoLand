package com.includoland.includoland.model.entity;

import com.includoland.includoland.model.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private boolean isPremium;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "specialist")
    private List<Notice> noticesAsSpecialist;

    @OneToMany(mappedBy = "child")
    private List<Notice> noticesAsChild;

    @OneToMany(mappedBy = "author")
    private List<Content> authoredContent;

    @OneToMany(mappedBy = "user")
    private List<Result> results;

    @OneToMany(mappedBy = "specialist")
    private List<SpecialistStudentMapping> specialistMappings;

    @OneToMany(mappedBy = "child")
    private List<SpecialistStudentMapping> childMappings;
}
