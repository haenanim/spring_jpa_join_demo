package com.example.order_jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(length = 45)
    private String name;
    @Column(length = 45)
    private String email;
    @Column(length = 10)
    private String password;
    @Column(length = 15)
    private String phone;
    @Column(length = 45)
    private String address;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_type", length = 10)
    private UserType userType;
}
