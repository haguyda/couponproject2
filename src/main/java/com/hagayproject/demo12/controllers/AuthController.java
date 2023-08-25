package com.hagayproject.demo12.controllers;


import com.hagayproject.demo12.exceptions.CouponSystemException;
import com.hagayproject.demo12.security.User;
import com.hagayproject.demo12.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID login(@RequestBody User user) throws CouponSystemException {
        return authService.login(user);
    }
}