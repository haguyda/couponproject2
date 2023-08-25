package com.hagayproject.demo12.repos;


import com.hagayproject.demo12.beans.Category;
import com.hagayproject.demo12.beans.Coupon;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO spring_coupon_system.customers_coupons (`customer_id`, `coupons_id`) VALUES (?, ?)", nativeQuery = true)
    void addCouponPurchase(int customerId, int couponId);

    boolean existsByCompany_idAndTitle(int companyId, String title);

    List<Coupon> findByCompany_id(int companyId);

    List<Coupon> findByCompany_idAndCategory(int companyId, Category category);

    List<Coupon> findByCompany_idAndPriceLessThanEqual(int companyId, double MaxPrice);

    @Query(value = "SELECT EXISTS (SELECT * FROM spring_coupon_system.customers_coupons WHERE customer_id = ? AND coupons_id = ?)", nativeQuery = true)
    Long existsPurchase(int customerId, int couponId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM spring_coupon_system.customers_coupons WHERE coupons_id = ?", nativeQuery = true)
    void deletePurchaseByCouponId(int id);

    @Query(value = "SELECT coupons.id,coupons.company_id,coupons.category,coupons.title,coupons.description,coupons.start_date,coupons.end_date,coupons.amount,coupons.price,coupons.image\n" +
            " FROM spring_coupon_system.coupons\n" +
            "INNER JOIN spring_coupon_system.customers_coupons ON coupons.id=customers_coupons.coupons_id WHERE customer_id=?", nativeQuery = true)
    List<Coupon> findByCustomer(int customerId);

    @Query(value = "SELECT coupons.id,coupons.company_id,coupons.category,coupons.title,coupons.description,coupons.start_date,coupons.end_date,coupons.amount,coupons.price,coupons.image\n" +
            " FROM spring_coupon_system.coupons\n" +
            "INNER JOIN spring_coupon_system.customers_coupons ON coupons.id=customers_coupons.coupons_id WHERE customer_id=? AND coupons.category=?", nativeQuery = true)
    List<Coupon> findByCustomerAndCategory(int customerId, String category);

    @Query(value = "SELECT coupons.id,coupons.company_id,coupons.category,coupons.title,coupons.description,coupons.start_date,coupons.end_date,coupons.amount,coupons.price,coupons.image\n" +
            " FROM spring_coupon_system.coupons\n" +
            "INNER JOIN spring_coupon_system.customers_coupons ON coupons.id=customers_coupons.coupons_id WHERE customer_id=? AND price<=?", nativeQuery = true)
    List<Coupon> findByCustomerAndMaxPrice(int customerId, double maxPrice);

    void deleteByEndDateBefore(Date date);


}
