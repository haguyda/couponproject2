package com.hagayproject.demo12.services;



import com.hagayproject.demo12.beans.Category;
import com.hagayproject.demo12.beans.Coupon;
import com.hagayproject.demo12.beans.Customer;
import com.hagayproject.demo12.exceptions.CouponSystemException;
import com.hagayproject.demo12.exceptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {
    @Override
    public boolean login(String email, String password) {
        if (customerRepository.existsByEmailAndPassword(email, password)) {
            return true;
        }
        return false;
    }

    @Override
    public void purchaseCoupon(int customerId, Coupon coupon) throws CouponSystemException {
        coupon = couponRepository.findById(coupon.getId()).orElseThrow(() -> new CouponSystemException(ErrMsg.COUPON_NOT_EXISTS));
        if (couponRepository.existsPurchase(customerId, coupon.getId()) == 1) {
            throw new CouponSystemException(ErrMsg.COUPON_ALREADY_PURCHASED);
        }
        if (coupon.getAmount() == 0) {
            throw new CouponSystemException(ErrMsg.COUPON_AMOUNT_ZERO);
        }
        if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException(ErrMsg.COUPON_EXPIRED);
        }
        couponRepository.addCouponPurchase(customerId, coupon.getId());
        coupon.setAmount(coupon.getAmount() - 1);
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId) {
        return couponRepository.findByCustomer(customerId);
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId, Category category) {
        return couponRepository.findByCustomerAndCategory(customerId, category.name());
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId, double maxPrice) {
        return couponRepository.findByCustomerAndMaxPrice(customerId, maxPrice);
    }

    @Override
    public Customer getCustomerDetails(int customerId) throws CouponSystemException {
        return customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException(ErrMsg.CUSTOMER_NOT_EXISTS));
    }
}