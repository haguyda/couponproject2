package com.hagayproject.demo12.services;


import com.hagayproject.demo12.beans.Category;
import com.hagayproject.demo12.beans.Company;
import com.hagayproject.demo12.beans.Coupon;
import com.hagayproject.demo12.exceptions.CouponSystemException;

import java.util.List;

public interface CompanyService {
    void addCoupon(int companyId, Coupon coupon) throws CouponSystemException;

    void updateCoupon(int companyId, int id, Coupon coupon) throws CouponSystemException;

    void deleteCoupon(int companyId, int id) throws CouponSystemException;

    List<Coupon> getCompanyCoupons(int companyId);

    List<Coupon> getCompanyCoupons(int companyId, Category category);

    List<Coupon> getCompanyCoupons(int companyId, double maxPrice);

    Company getCompanyDetails(int companyId) throws CouponSystemException;
}
