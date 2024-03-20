package com.example.PayRollTracker.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Getter
    @Setter
    private Roles role;
    // relation with checkin
    @OneToOne(mappedBy = "user")
    private CheckIn checkIn;
    // relation with checkout
    @OneToOne(mappedBy = "user")
    private CheckOut checkOut;

    public UserEntity(Long id, String username, String password, Roles role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
