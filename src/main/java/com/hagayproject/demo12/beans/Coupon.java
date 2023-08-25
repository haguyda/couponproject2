package com.hagayproject.demo12.beans;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "coupons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String title;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC")
    private Date endDate;
    private int amount;

    private double price;
    private String image;
}