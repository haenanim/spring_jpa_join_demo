package com.example.order_jpa.service;


import com.example.order_jpa.entity.User;
import com.example.order_jpa.entity.UserType;
import com.example.order_jpa.repository.JPAUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final JPAUserRepository userRepository;

    public void addUser(User user) {//일반 사용자 - 고객
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if(byEmail.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        user.setUserType(UserType.BASIC);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
