package com.example.order_jpa.controller;

import com.example.order_jpa.entity.User;
import com.example.order_jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/add")
    public String addUser() {
        return "user/userForm";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/product/list";  // 상품조회 화면으로 redirect
    }
}
