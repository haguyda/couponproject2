package com.hagayproject.demo12.clr;


import com.hagayproject.demo12.tests.AdminServiceTest;
import com.hagayproject.demo12.tests.CompanyServiceTest;
import com.hagayproject.demo12.tests.CustomerServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class Tests implements CommandLineRunner {
    @Autowired
    private AdminServiceTest adminServiceTest;
    @Autowired
    private CompanyServiceTest companyServiceTest;
    @Autowired
    private CustomerServiceTest customerServiceTest;


    @Override
    public void run(String... args) throws Exception {
        adminServiceTest.testAsAdmin();
        companyServiceTest.testAsCompany();
        customerServiceTest.testAsCustomer();
    }
}