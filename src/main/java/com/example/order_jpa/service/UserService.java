package com.example.order_jpa.service;

import com.example.order_jpa.entity.User;
import com.example.order_jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  public User save(User user) {
    return userRepository.save(user);
  }
  public List<User> findAll() {
    return userRepository.findAll();
  }
  public User findById(String id) {
    return userRepository.findById(id).get();
  }
  public void deleteById(String id) {
    userRepository.deleteById(id);
  }

}
