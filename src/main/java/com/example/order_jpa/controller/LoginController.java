package com.example.order_jpa.controller;

import com.example.order_jpa.dto.UserLoginDto;
import com.example.order_jpa.entity.User;
import com.example.order_jpa.session.UserSession;
import com.example.order_jpa.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private LoginService loginService;

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute UserLoginDto userLoginDto,
                        HttpServletResponse response,
                        HttpServletRequest request) {
        User login = loginService.login(userLoginDto);
        if(login == null) {
            return "redirect:/login";
        }
        UserSession userSession = new UserSession();
        userSession.setUserId(login.getUserId());
        userSession.setName(login.getName());
        userSession.setEmail(login.getEmail());

        HttpSession session = request.getSession();
        String uuid = UUID.randomUUID().toString();
        session.setAttribute(uuid, userSession);

        //로그인에 성공 시 쿠키 설정
        Cookie cookie = new Cookie("userId",uuid);
        cookie.setPath("/");
        cookie.setMaxAge(60);
        response.addCookie(cookie);

        return "redirect:/order/add";

    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("userId",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        HttpSession session = request.getSession(false);
        if(session!= null) {
            session.invalidate();
        }
        return "redirect:/login";
    }

}
