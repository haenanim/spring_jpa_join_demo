package com.example.order_jpa.session;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserSession {
    @Id
    private Long userId;
    private String name;
    private String email;
}
