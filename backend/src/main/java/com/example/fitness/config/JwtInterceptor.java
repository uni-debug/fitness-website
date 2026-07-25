package com.example.fitness.config;

import com.example.fitness.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        
        if (token == null || !token.startsWith("Bearer ")) {
            sendErrorResponse(response, 401, "未登录或token无效");
            return false;
        }

        token = token.substring(7);

        try {
            if (!jwtUtil.validateToken(token)) {
                sendErrorResponse(response, 401, "token已过期");
                return false;
            }
            request.setAttribute("userId", jwtUtil.getUserIdFromToken(token));
            request.setAttribute("username", jwtUtil.getUsernameFromToken(token));
            return true;
        } catch (Exception e) {
            sendErrorResponse(response, 401, "token解析失败");
            return false;
        }
    }

    private void sendErrorResponse(HttpServletResponse response, int code, String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(Result.error(code, message)));
    }
}
