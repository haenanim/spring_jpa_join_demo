package com.example.order_jpa.controller;

import com.example.order_jpa.entity.User;
import com.example.order_jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
  private final UserService userService;
  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String index() {
    return "redirect:/user/list";
  }
  @GetMapping("/list")
  public String list(Model model) {
    model.addAttribute("users", userService.findAll());
    return "user/userList";
  }
  @PostMapping("/add")
  public String add(User user) {
    userService.save(user);
    return "redirect:/user/list";
  }
}
