package com.example.PayRollTracker.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "check_out")
@Data
public class CheckOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Time checkOutTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
