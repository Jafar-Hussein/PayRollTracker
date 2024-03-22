package com.example.PayRollTracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CheckIn> checkIns;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CheckOut> checkOuts;
    @OneToOne(mappedBy = "user")
    private PayRoll payRoll;

    public UserEntity(Long id, String username, String password, Roles role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
