package com.hagayproject.demo12.beans;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToMany
    private List<Coupon> coupons;
}