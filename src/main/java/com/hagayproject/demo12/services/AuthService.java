package com.hagayproject.demo12.services;


import com.hagayproject.demo12.exceptions.CouponSystemException;
import com.hagayproject.demo12.security.User;

import java.util.UUID;

public interface AuthService {
    UUID login(User user) throws CouponSystemException;
}
