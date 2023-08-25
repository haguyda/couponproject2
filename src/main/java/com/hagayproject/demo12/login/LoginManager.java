package com.hagayproject.demo12.login;


import com.hagayproject.demo12.exceptions.CouponSystemException;
import com.hagayproject.demo12.exceptions.ErrMsg;
import com.hagayproject.demo12.services.AdminService;
import com.hagayproject.demo12.services.ClientService;
import com.hagayproject.demo12.services.CompanyService;
import com.hagayproject.demo12.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginManager {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;

    public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {
        switch (clientType) {
            case ADMINSTRATOR:
                if (((ClientService) adminService).login(email, password)) {
                    return (ClientService) adminService;
                }
                break;
            case COMPANY:
                if (((ClientService) companyService).login(email, password)) {
                    return (ClientService) companyService;
                }
                break;
            case CUSTOMER:
                if (((ClientService) customerService).login(email, password)) {
                    return (ClientService) customerService;
                }
                break;
        }
        throw new CouponSystemException(ErrMsg.LOGIN_FAILED);
    }


}