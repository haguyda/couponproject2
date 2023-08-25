package com.hagayproject.demo12.jobs;


import com.hagayproject.demo12.repos.CouponRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class CouponExpirationDailyJob {

    @Autowired
    private CouponRepository couponRepository;

    @Transactional
    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void deleteExpiredCoupon() throws InterruptedException {
        Thread.sleep(1000 * 5);
        couponRepository.deleteByEndDateBefore(Date.valueOf(LocalDate.now()));
    }
}