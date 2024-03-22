package com.example.PayRollTracker.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Entity
@Table(name = "check_in")
@Data
public class CheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Time checkInTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
