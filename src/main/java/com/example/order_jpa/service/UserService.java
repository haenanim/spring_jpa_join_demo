package com.example.order_jpa.service;


import com.example.order_jpa.entity.User;
import com.example.order_jpa.entity.UserType;
import com.example.order_jpa.repository.JPAUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final JPAUserRepository userRepository;

    public void addUser(User user) {
        user.setUserType(UserType.BASIC);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
