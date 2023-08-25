package com.hagayproject.demo12.security;


import com.hagayproject.demo12.login.ClientType;
import com.hagayproject.demo12.repos.CompanyRepository;
import com.hagayproject.demo12.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    private Map<UUID, Information> tokens = new HashMap<>();

    @Override
    public UUID addToken(User user) {
        UUID token = UUID.randomUUID();
        int id = 0;
        switch (user.getClientType()) {
            case ADMINSTRATOR:
                break;
            case COMPANY:
                id = companyRepository.getIdByEmail(user.getEmail());
                break;
            case CUSTOMER:
                id = customerRepository.getIdByEmail(user.getEmail());
                break;
        }
        Information info = Information.builder().id(id).time(LocalDateTime.now()).clientType(user.getClientType()).build();
        tokens.put(token, info);
        return token;
    }

    @Override
    public boolean isUserAllowed(UUID token, ClientType clientType) {
        Information info = tokens.get(token);
        if (info == null) {
            return false;
        }
        return info.getClientType().equals(clientType);
    }

    @Override
    public void clear() {
        tokens.entrySet().removeIf(item -> item.getValue().getTime().isBefore(LocalDateTime.now().minusMinutes(30)));
    }

    @Override
    public int getId(UUID token) {
        return tokens.get(token).getId();
    }
}