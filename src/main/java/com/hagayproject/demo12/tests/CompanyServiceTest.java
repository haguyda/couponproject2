package com.hagayproject.demo12.tests;


import com.hagayproject.demo12.Utils.Art;
import com.hagayproject.demo12.beans.Category;
import com.hagayproject.demo12.beans.Coupon;
import com.hagayproject.demo12.exceptions.CouponSystemException;
import com.hagayproject.demo12.exceptions.ErrMsg;
import com.hagayproject.demo12.login.ClientType;
import com.hagayproject.demo12.login.LoginManager;
import com.hagayproject.demo12.repos.CompanyRepository;
import com.hagayproject.demo12.repos.CouponRepository;
import com.hagayproject.demo12.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class CompanyServiceTest {
    @Autowired
    private LoginManager loginManager;
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CouponRepository couponRepository;

    private CompanyService companyService;

    public void testAsCompany() throws CouponSystemException {
        System.out.println(Art.COMPANY_SERVICE);

        Test.test("Company Service - bad login - wrong email");
        try {
            companyService = (CompanyService) loginManager.login("stam@stam.com", "1234", ClientType.COMPANY);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        Test.test("Company Service - bad login - wrong password");
        try {
            companyService = (CompanyService) loginManager.login("KSP@KSP.com", "stam", ClientType.COMPANY);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        Test.test("Company Service - bad login - wrong email and password");
        try {
            companyService = (CompanyService) loginManager.login("stam@stam.com", "stam", ClientType.COMPANY);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Service - good login");
        try {
            companyService = (CompanyService) loginManager.login("KSP@KSP.com", "1234", ClientType.COMPANY);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        int companyId = companyRepository.getIdByEmail("KSP@KSP.com");


        Test.test("Company Service - good add coupon");
        Coupon coupon = Coupon.builder()
                .title("20% off Logitech products")
                .image("https://shorturl.at/epxRT")
                .price(500)
                .category(Category.ELECTRICITY)
                .company(companyRepository.findById(1).orElseThrow(()->new CouponSystemException(ErrMsg.COMPANY_NOT_EXIST)))
                .description("get 20% off any Logitech product")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .amount(100)
                .build();
        try {
            companyService.addCoupon(companyId, coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Service - bad add coupon - same title");
        try {
            companyService.addCoupon(companyId, coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Service - bad update coupon - changed id");
        coupon.setId(2);
        try {
            companyService.updateCoupon(companyId,6, coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Service - bad update coupon - changed company id");
        coupon.setId(6);
        coupon.setCompany(companyRepository.findById(2).orElseThrow(()->new CouponSystemException(ErrMsg.COMPANY_NOT_EXIST)));
        try {
            companyService.updateCoupon(companyId,6, coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Service - good update coupon");
        coupon.setCompany(companyRepository.findById(1).orElseThrow(()->new CouponSystemException(ErrMsg.COMPANY_NOT_EXIST)));
        coupon.setAmount(50);
        coupon.setTitle("30% OFF Everything!!!");
        coupon.setDescription("price 30% off all products");
        try {
            companyService.updateCoupon(companyId,6, coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Service - show all company coupons");
        companyService.getCompanyCoupons(companyId).forEach(System.out::println);

        Test.test("Company Service - show all company coupons - electricity");
        companyService.getCompanyCoupons(companyId, Category.ELECTRICITY).forEach(System.out::println);

        Test.test("Company Service - show all company coupons - max price 500");
        companyService.getCompanyCoupons(companyId,500.0).forEach(System.out::println);

        Test.test("Company Service - delete coupon id=6");
        try {
            companyService.deleteCoupon(companyId,6);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Service - show all company coupons");
        companyService.getCompanyCoupons(companyId).forEach(System.out::println);

        Test.test("Company Service - get company details");
        System.out.println(companyService.getCompanyDetails(companyId));
    }
}
