package com.example.order_jpa.filter;

import com.example.order_jpa.session.UserSession;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.example.order_jpa.session.SessionConst.SESSION_NAME;

@Slf4j
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        UserSession userSession = (UserSession) session.getAttribute(SESSION_NAME);
        log.info("userSession: {}",userSession);
        if(userSession!= null) {
            String redirectURI   = req.getRequestURI();
            resp.sendRedirect(req.getContextPath() + "/login?redirectURI" + redirectURI);
            return; // 로그인 되지 않은 사용자는 login 하러 감(지금 요청 들어온 uri 정보를 가지고)

        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

//    Cookie[] cookies = request.getCookies();
//        log.info("cookie.name ==> "+cookie.getName());
//    Long userId = Long.parseLong(cookie.getValue());
//    User user = userService.getUserById(userId);

}
