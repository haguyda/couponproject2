package com.hagayproject.demo12.services;


import com.hagayproject.demo12.exceptions.CouponSystemException;
import com.hagayproject.demo12.exceptions.ErrMsg;
import com.hagayproject.demo12.login.ClientType;
import com.hagayproject.demo12.security.TokenService;
import com.hagayproject.demo12.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TokenService tokenService;

    @Override
    public UUID login(User user) throws CouponSystemException {
        switch (user.getClientType()) {
            case ClientType.ADMINSTRATOR:
                if (!((ClientService) adminService).login(user.getEmail(), user.getPassword())) {
                    throw new CouponSystemException(ErrMsg.LOGIN_FAILED);
                }
                break;
            case ClientType.COMPANY:
                if (!((ClientService) companyService).login(user.getEmail(), user.getPassword())) {
                    throw new CouponSystemException(ErrMsg.LOGIN_FAILED);
                }
                break;
            case ClientType.CUSTOMER:
                if (!((ClientService) customerService).login(user.getEmail(), user.getPassword())) {
                    throw new CouponSystemException(ErrMsg.LOGIN_FAILED);
                }
                break;
        }
        return tokenService.addToken(user);
    }
}