package com.hagayproject.demo12.tests;

import com.hagayproject.demo12.Utils.Art;
import com.hagayproject.demo12.beans.Category;
import com.hagayproject.demo12.beans.Coupon;
import com.hagayproject.demo12.exceptions.CouponSystemException;
import com.hagayproject.demo12.exceptions.ErrMsg;
import com.hagayproject.demo12.login.ClientType;
import com.hagayproject.demo12.login.LoginManager;
import com.hagayproject.demo12.repos.CouponRepository;
import com.hagayproject.demo12.repos.CustomerRepository;
import com.hagayproject.demo12.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceTest {
    @Autowired
    private LoginManager loginManager;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CustomerRepository customerRepository;
    private CustomerService customerService;


    public void testAsCustomer() throws CouponSystemException {
        System.out.println(Art.CUSTOMER_SERVICE);

        Test.test("Customer Service - bad login");
        try {
            customerService = (CustomerService) loginManager.login("stam@email.com", "1234", ClientType.CUSTOMER);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Customer Service - good login");
        try {
            customerService = (CustomerService) loginManager.login("johndoe@email.com", "1234", ClientType.CUSTOMER);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        int customerId = customerRepository.getIdByEmail("johndoe@email.com");

        Test.test("Customer Service - good purchase coupon id=1");
        Coupon toPurchase = couponRepository.findById(1).orElseThrow(() -> new CouponSystemException(ErrMsg.COUPON_NOT_EXISTS));
        try {
            customerService.purchaseCoupon(customerId, toPurchase);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Customer Service - bad purchase - already purchased");
        try {
            customerService.purchaseCoupon(customerId, toPurchase);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Customer Service - get customer coupons");
        customerService.getCustomerCoupons(customerId).forEach(System.out::println);

        Test.test("Customer Service - get customer coupons category=Electricity");
        customerService.getCustomerCoupons(customerId, Category.ELECTRICITY).forEach(System.out::println);

        Test.test("Customer Service - get customer coupons max_price=3000");
        customerService.getCustomerCoupons(customerId, 3000).forEach(System.out::println);

        Test.test("Customer Service - get customer details");
        System.out.println(customerService.getCustomerDetails(customerId));
    }
}