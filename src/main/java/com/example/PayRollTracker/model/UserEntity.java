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
    @Column(unique = true)
    private String username;
    private String password;
    @OneToOne(mappedBy = "user")
    private UserInfo userInfo;


    @Getter
    @Setter
    private Roles role;
    // relation with checkin
    @OneToOne(mappedBy = "user")
    private CheckIn checkIn;
    // relation with checkout
    @OneToOne(mappedBy = "user")
    private CheckOut checkOut;
    @OneToOne(mappedBy = "user")
    private PayRoll payRoll;

    public UserEntity(Long id, String username, String password, Roles role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
