package com.hagayproject.demo12.services;


import com.hagayproject.demo12.beans.Company;
import com.hagayproject.demo12.beans.Customer;
import com.hagayproject.demo12.exceptions.CouponSystemException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Lazy
public interface AdminService {
    void addCompany(Company company) throws CouponSystemException;

    void updateCompany(int id, Company company) throws CouponSystemException;

    void deleteCompany(int id) throws CouponSystemException;

    List<Company> getAllCompanies();

    Company getOneCompany(int id) throws CouponSystemException;

    void addCustomer(Customer customer) throws CouponSystemException;

    void updateCustomer(int id, Customer customer) throws CouponSystemException;

    void deleteCustomer(int id) throws CouponSystemException;

    List<Customer> getAllCustomers();

    Customer getOneCustomer(int id) throws CouponSystemException;
}