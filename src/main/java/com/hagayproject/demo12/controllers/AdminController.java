package com.hagayproject.demo12.controllers;


import com.hagayproject.demo12.exceptions.CouponSystemException;
import com.hagayproject.demo12.exceptions.ErrMsg;
import com.hagayproject.demo12.login.ClientType;
import com.hagayproject.demo12.security.TokenService;
import com.hagayproject.demo12.beans.Company;
import com.hagayproject.demo12.beans.Customer;
import com.hagayproject.demo12.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("company/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestHeader(value = "token") UUID token, @RequestBody Company company) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINSTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        adminService.addCompany(company);
    }

    @PutMapping("company/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@RequestHeader(value = "token") UUID token, @PathVariable int id, @RequestBody Company company) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINSTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        adminService.updateCompany(id, company);
    }

    @DeleteMapping("company/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@RequestHeader(value = "token") UUID token, @PathVariable int id) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINSTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        adminService.deleteCompany(id);
    }

    @GetMapping("companies")
    public List<Company> getAllCompanies(@RequestHeader(value = "token") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINSTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        return adminService.getAllCompanies();
    }

    @GetMapping("company/{id}")
    public Company getOneCompany(@RequestHeader(value = "token") UUID token, @PathVariable int id) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINSTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        return adminService.getOneCompany(id);

    }

    @PostMapping("customer/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestHeader(value = "token") UUID token, @RequestBody Customer customer) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINSTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        adminService.addCustomer(customer);
    }

    @PutMapping("customer/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestHeader(value = "token") UUID token, @PathVariable int id, @RequestBody Customer customer) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINSTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        adminService.updateCustomer(id, customer);
    }


    @DeleteMapping("customer/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@RequestHeader(value = "token") UUID token, @PathVariable int id) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINSTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        adminService.deleteCustomer(id);
    }

    @GetMapping("customers")
    public List<Customer> getAllCustomers(@RequestHeader(value = "token") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINSTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        return adminService.getAllCustomers();
    }

    @GetMapping("customer/{id}")
    public Customer getOneCustomer(@RequestHeader(value = "token") UUID token, @PathVariable int id) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINSTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        return adminService.getOneCustomer(id);
    }
}
