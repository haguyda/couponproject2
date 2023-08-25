package com.hagayproject.demo12.services;


import com.hagayproject.demo12.repos.CompanyRepository;
import com.hagayproject.demo12.repos.CouponRepository;
import com.hagayproject.demo12.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {
    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;

    public abstract boolean login(String email, String password);
}