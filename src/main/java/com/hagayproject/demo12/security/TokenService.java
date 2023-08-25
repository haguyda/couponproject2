package com.hagayproject.demo12.security;


import com.hagayproject.demo12.login.ClientType;

import java.util.UUID;

public interface TokenService {
    UUID addToken(User user);

    boolean isUserAllowed(UUID token, ClientType clientType);

    void clear();

    int getId(UUID token);
}