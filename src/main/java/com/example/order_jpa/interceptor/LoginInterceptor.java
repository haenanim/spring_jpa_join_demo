package com.example.order_jpa.interceptor;

import com.example.order_jpa.session.SessionConst;
import com.example.order_jpa.session.UserSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req,
                             HttpServletResponse res,
                             Object handler) throws Exception {

        String redirectURI = req.getRequestURI();
        HttpSession session = req.getSession(false);
        // null check
        if(session == null) {
            res.sendRedirect(req.getContextPath() + "/login?redirectURI="+redirectURI);
            return false;
        } else {
            UserSession userSession = (UserSession)session.getAttribute(SessionConst.SESSION_NAME);
            log.info("userSession {} ", userSession);
            if(userSession == null) {// 로그인 되지 않은 사용자는 login 하러 감(지금 요청 들어온 uri 정보를 가지고)
                res.sendRedirect(req.getContextPath() + "/login?redirectURI="+redirectURI);
                return false;
            }
        }
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request,
//                           HttpServletResponse response,
//                           Object handler,
//                           ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request,
//                                HttpServletResponse response,
//                                Object handler,
//                                Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
}