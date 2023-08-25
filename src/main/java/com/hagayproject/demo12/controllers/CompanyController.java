package com.hagayproject.demo12.controllers;


import com.hagayproject.demo12.exceptions.CouponSystemException;
import com.hagayproject.demo12.exceptions.ErrMsg;
import com.hagayproject.demo12.login.ClientType;
import com.hagayproject.demo12.security.TokenService;
import com.hagayproject.demo12.beans.Category;
import com.hagayproject.demo12.beans.Company;
import com.hagayproject.demo12.beans.Coupon;
import com.hagayproject.demo12.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("coupon/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@RequestHeader(value = "token") UUID token, @RequestBody Coupon coupon) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int companyId = tokenService.getId(token);
        companyService.addCoupon(companyId, coupon);
    }

    @PutMapping("coupon/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCoupon(@RequestHeader(value = "token") UUID token, @PathVariable int id, @RequestBody Coupon coupon) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int companyId = tokenService.getId(token);
        companyService.updateCoupon(companyId, id, coupon);
    }

    @DeleteMapping("coupon/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@RequestHeader(value = "token") UUID token, @PathVariable int id) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int companyId = tokenService.getId(token);
        companyService.deleteCoupon(companyId, id);
    }

    @GetMapping("coupons")
    public List<Coupon> getCompanyCoupons(@RequestHeader(value = "token") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int companyId = tokenService.getId(token);
        return companyService.getCompanyCoupons(companyId);
    }

    @GetMapping("coupons/category/{category}")
    public List<Coupon> getCompanyCoupons(@RequestHeader(value = "token") UUID token, @PathVariable String category) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int companyId = tokenService.getId(token);
        return companyService.getCompanyCoupons(companyId, Category.valueOf(category.toUpperCase()));
    }

    @GetMapping("coupons/maxPrice/{maxPrice}")
    public List<Coupon> getCompanyCoupons(@RequestHeader(value = "token") UUID token, @PathVariable double maxPrice) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int companyId = tokenService.getId(token);
        return companyService.getCompanyCoupons(companyId, maxPrice);
    }

    @GetMapping("details")
    public Company getCompanyDetails(@RequestHeader(value = "token") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int companyId = tokenService.getId(token);
        return companyService.getCompanyDetails(companyId);
    }
}