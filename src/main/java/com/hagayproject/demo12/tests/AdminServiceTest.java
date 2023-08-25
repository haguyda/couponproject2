package com.hagayproject.demo12.tests;


import com.hagayproject.demo12.Utils.Art;
import com.hagayproject.demo12.beans.Company;
import com.hagayproject.demo12.beans.Customer;
import com.hagayproject.demo12.exceptions.CouponSystemException;
import com.hagayproject.demo12.login.ClientType;
import com.hagayproject.demo12.login.LoginManager;
import com.hagayproject.demo12.services.AdminService;
import com.hagayproject.demo12.services.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceTest {
    @Autowired
    private LoginManager loginManager;

    private AdminService adminService;

    public void testAsAdmin() throws CouponSystemException {
        System.out.println(Art.ADMIN_SERVICE);

        Test.test("Admin Service - bad login - wrong email");
        try {
            adminService = (AdminServiceImpl) loginManager.login("stam@email.com", "admin", ClientType.ADMINSTRATOR);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Service - bad login - wrong password");
        try {
            adminService = (AdminServiceImpl) loginManager.login("admin@admin.com", "stam", ClientType.ADMINSTRATOR);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Service - bad login - wrong email and password");
        try {
            adminService = (AdminServiceImpl) loginManager.login("stam@email.com", "stam", ClientType.ADMINSTRATOR);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - good login");
        try {
            adminService = (AdminServiceImpl) loginManager.login("admin@admin.com", "admin", ClientType.ADMINSTRATOR);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - bad add company - same name");
        Company c1 = Company.builder()
                .name("KSP")
                .email("stam@stam.com")
                .password("1234")
                .build();
        try {
            adminService.addCompany(c1);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - bad add company - same id");
        c1.setName("KFC");
        c1.setId(1);
        try {
            adminService.addCompany(c1);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - bad add company - same email");
        c1.setName("stam");
        c1.setId(0);
        c1.setEmail("KSP@KSP.com");
        try {
            adminService.addCompany(c1);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - good add company");
        Company c2 = Company.builder()
                .name("EFC")
                .email("efc@efc.com")
                .password("1234")
                .build();
        try {
            adminService.addCompany(c2);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - bad update company - changed id");
        Company toUpdate = adminService.getOneCompany(11);
        toUpdate.setId(2);
        try {
            adminService.updateCompany(11, toUpdate);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - bad update company - changed name");
        toUpdate = adminService.getOneCompany(11);
        toUpdate.setId(11);
        try {
            adminService.updateCompany(11, toUpdate);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - good update company - changed email");
        toUpdate.setName("EFC");
        toUpdate.setEmail("info@efc.com");
        try {
            adminService.updateCompany(11, toUpdate);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - get one company id=11");
        try {
            System.out.println(adminService.getOneCompany(11));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - get one company id=15 - doesn't exist");
        try {
            System.out.println(adminService.getOneCompany(15));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - delete company id=11");
        try {
            adminService.deleteCompany(11);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - get all companies");
        adminService.getAllCompanies().forEach(System.out::println);

        Test.test("Admin Service - bad add customer - same email");
        Customer customer1 = Customer.builder()
                .firstName("stam")
                .lastName("stam")
                .email("johndoe@email.com")
                .password("1234")
                .build();
        try {
            adminService.addCustomer(customer1);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - bad add customer - same id");
        customer1.setEmail("stam@stam.com");
        customer1.setId(1);
        try {
            adminService.addCustomer(customer1);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - good add customer");
        Customer customer2 = Customer.builder()
                .firstName("Ahmad")
                .lastName("Jbara")
                .email("ahmadjbara@email.com")
                .password("1234")
                .build();
        try {
            adminService.addCustomer(customer2);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - bad update customer - changed id");
        customer2.setId(2);
        try {
            adminService.updateCustomer(1, customer2);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - good update customer");
        customer2.setId(11);
        customer2.setFirstName("Jaber");
        try {
            adminService.updateCustomer(11, customer2);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - get one customer - id=11");
        try {
            System.out.println(adminService.getOneCustomer(11));
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - delete customer - id=11");
        try {
            adminService.deleteCustomer(11);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - get all customers");
        adminService.getAllCustomers().forEach(System.out::println);

    }

}