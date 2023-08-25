package com.hagayproject.demo12.repos;


import com.hagayproject.demo12.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    boolean existsByName(String name);

    @Query(value = "SELECT id FROM spring_coupon_system.companies WHERE email = ?;", nativeQuery = true)
    int getIdByEmail(String email);
}
