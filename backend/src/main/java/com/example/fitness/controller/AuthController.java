package com.example.fitness.controller;

import com.example.fitness.common.Result;
import com.example.fitness.entity.User;
import com.example.fitness.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public Result<Map<String, Object>> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return Result.error(400, "用户名和密码不能为空");
        }
        
        Map<String, Object> result = authService.register(username, password);
        
        if ((Boolean) result.get("success")) {
            return Result.success(result);
        } else {
            return Result.error(400, (String) result.get("message"));
        }
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return Result.error(400, "用户名和密码不能为空");
        }
        
        Map<String, Object> result = authService.login(username, password);
        
        if ((Boolean) result.get("success")) {
            return Result.success(result);
        } else {
            return Result.error(401, (String) result.get("message"));
        }
    }

    @GetMapping("/info")
    public Result<Map<String, Object>> getInfo(@RequestAttribute("userId") Long userId) {
        User user = authService.getUserInfo(userId);
        
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("username", user.getUsername());
        userInfo.put("nickname", user.getNickname());
        userInfo.put("avatar", user.getAvatar());
        
        return Result.success(userInfo);
    }
}
