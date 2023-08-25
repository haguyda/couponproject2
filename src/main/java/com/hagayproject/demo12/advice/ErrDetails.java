package com.hagayproject.demo12.advice;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrDetails {
    private final String title = "Coupon System Error";
    private String description;
}