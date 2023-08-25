package com.hagayproject.demo12.security;


import com.hagayproject.demo12.login.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Information {
    private int id;
    private LocalDateTime time;
    private ClientType clientType;
}