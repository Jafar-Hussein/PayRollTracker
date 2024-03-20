package com.example.PayRollTracker.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "check_in")
@Data
public class CheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date checkInTime;
    @OneToOne
    private UserEntity user;
}
