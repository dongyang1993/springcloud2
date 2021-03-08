package com.hd.auth.controller;

import com.cloud.common.api.Rs;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {


    @PostMapping("/login")
    public Rs<Object> login() {
        return Rs.ok(null);
    }

    @PostMapping("/verifyToken")
    public Rs<Object> verifyToken() {
        return Rs.ok(null);
    }

    @PostMapping("/logout")
    public Rs<Object> logout() {
        return Rs.ok(null);
    }

}
