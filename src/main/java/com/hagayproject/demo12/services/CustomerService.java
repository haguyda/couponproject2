package com.hagayproject.demo12.services;



import com.hagayproject.demo12.beans.Category;
import com.hagayproject.demo12.beans.Coupon;
import com.hagayproject.demo12.beans.Customer;
import com.hagayproject.demo12.exceptions.CouponSystemException;


import java.util.List;

public interface CustomerService {
    void purchaseCoupon(int customerId, Coupon coupon) throws CouponSystemException;

    List<Coupon> getCustomerCoupons(int customerId);

    List<Coupon> getCustomerCoupons(int customerId, Category category);

    List<Coupon> getCustomerCoupons(int customerId, double maxPrice);

    Customer getCustomerDetails(int customerId) throws CouponSystemException;
}