package com.hagayproject.demo12.services;


import com.hagayproject.demo12.beans.Category;
import com.hagayproject.demo12.beans.Company;
import com.hagayproject.demo12.beans.Coupon;
import com.hagayproject.demo12.exceptions.CouponSystemException;
import com.hagayproject.demo12.exceptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {
    //todo : remove companyId and add as argument
    @Override
    public boolean login(String email, String password) {
        if (companyRepository.existsByEmailAndPassword(email, password)) {
            return true;
        }
        return false;
    }

    @Override
    public void addCoupon(int companyId, Coupon coupon) throws CouponSystemException {
        if (couponRepository.existsByCompany_idAndTitle(companyId, coupon.getTitle())) {
            throw new CouponSystemException(ErrMsg.COUPON_TITLE_EXISTS);
        }
        if (coupon.getCompany().getId() != companyId) {
            throw new CouponSystemException(ErrMsg.COUPON_WRONG_COMPANY);
        }
        couponRepository.save(coupon);
    }

    @Override
    public void updateCoupon(int companyId, int id, Coupon coupon) throws CouponSystemException {
        if (!couponRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.COUPON_NOT_EXISTS);
        }
        if (coupon.getCompany().getId() != companyId) {
            throw new CouponSystemException(ErrMsg.COUPON_WRONG_COMPANY);
        }
        Coupon original = couponRepository.findById(id).get();
        if (original.getId() != coupon.getId()) {
            throw new CouponSystemException(ErrMsg.COUPON_ID_NOT_MATCH);
        }
        if (original.getCompany().equals(coupon.getCompany())) {
            throw new CouponSystemException(ErrMsg.COUPON_COMPANY_NOT_MATCH);
        }
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int companyId, int id) throws CouponSystemException {
        if (!couponRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.COUPON_NOT_EXISTS);
        }
        if (couponRepository.findById(id).get().getCompany().getId() != companyId) {
            throw new CouponSystemException(ErrMsg.COUPON_WRONG_COMPANY);
        }
        couponRepository.deletePurchaseByCouponId(id);
        couponRepository.deleteById(id);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId) {
        return couponRepository.findByCompany_id(companyId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId, Category category) {
        return couponRepository.findByCompany_idAndCategory(companyId, category);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId, double maxPrice) {
        return couponRepository.findByCompany_idAndPriceLessThanEqual(companyId, maxPrice);
    }

    @Override
    public Company getCompanyDetails(int companyId) throws CouponSystemException {
        return companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.COMPANY_NOT_EXIST));
    }
}