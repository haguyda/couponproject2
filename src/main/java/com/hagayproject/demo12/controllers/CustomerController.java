package com.hagayproject.demo12.controllers;


import com.hagayproject.demo12.exceptions.ErrMsg;
import com.hagayproject.demo12.login.ClientType;
import com.hagayproject.demo12.security.TokenService;
import com.hagayproject.demo12.beans.Category;
import com.hagayproject.demo12.beans.Coupon;
import com.hagayproject.demo12.beans.Customer;
import com.hagayproject.demo12.exceptions.CouponSystemException;
import com.hagayproject.demo12.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseCoupon(@RequestHeader(value = "token") UUID token, @RequestBody Coupon coupon) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int customerId = tokenService.getId(token);
        customerService.purchaseCoupon(customerId, coupon);
    }

    @GetMapping("coupons")
    public List<Coupon> getCustomerCoupons(@RequestHeader(value = "token") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int customerId = tokenService.getId(token);
        return customerService.getCustomerCoupons(customerId);
    }


    @GetMapping("coupons/category/{category}")
    public List<Coupon> getCustomerCoupons(@RequestHeader(value = "token") UUID token, @PathVariable Category category) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int customerId = tokenService.getId(token);
        return customerService.getCustomerCoupons(customerId, category);
    }

    @GetMapping("coupons/maxPrice/{maxPrice}")
    public List<Coupon> getCustomerCoupons(@RequestHeader(value = "token") UUID token, @PathVariable double maxPrice) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int customerId = tokenService.getId(token);
        return customerService.getCustomerCoupons(customerId, maxPrice);
    }

    @GetMapping("details")
    public Customer getCustomerDetails(@RequestHeader(value = "token") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int customerId = tokenService.getId(token);
        return customerService.getCustomerDetails(customerId);
    }
}
