package com.hagayproject.demo12.security;


import com.hagayproject.demo12.login.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String email;
    private String password;
    private ClientType clientType;
}