package com.includoland.includoland.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "specialist_student_mapping")
@Data
public class SpecialistStudentMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "specialist_uuid", nullable = false)
    private User specialist;

    @ManyToOne
    @JoinColumn(name = "child_uuid", nullable = false)
    private User child;

    private LocalDateTime createdAt;
}
