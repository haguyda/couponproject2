package com.hagayproject.demo12.Utils;


import com.hagayproject.demo12.beans.Category;
import com.hagayproject.demo12.beans.Company;
import com.hagayproject.demo12.beans.Coupon;
import com.hagayproject.demo12.beans.Customer;
import com.hagayproject.demo12.repos.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
public class FactoryUtils {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> initCompanies() {
        Company c1 = Company.builder().name("KSP").email("KSP@KSP.com").password("1234").build();
        Company c2 = Company.builder().name("BBB").email("info@BBB.com").password("1234").build();

        Company c3 = Company.builder().name("Global Industries").email("info@globalindustries.com").password("1234").build();

        Company c4 = Company.builder().name("Tours").email("info@tours.com").password("1234").build();

        Company c5 = Company.builder().name("Web Solutions Inc.").email("info@websolutionsinc.com").password("1234").build();

        Company c6 = Company.builder().name("Best Buy Co. Inc.").email("info@bestbuy.com").password("1234").build();

        Company c7 = Company.builder().name("Newegg Inc.").email("info@newegg.com").password("1234").build();

        Company c8 = Company.builder().name("Nike Inc.").email("info@nike.com").password("1234").build();

        Company c9 = Company.builder().name("The Coca-Cola Company").email("info@coca-cola.com").password("1234").build();

        Company c10 = Company.builder().name("Walmart Inc.").email("info@walmart.com").password("1234").build();

        List<Company> companies = List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);

        return companies;
    }

    public List<Coupon> initCoupons() {
        Coupon c1 = Coupon.builder().company(companyRepository.findById(2).get()).category(Category.RESTAURANT).title("1+1 Burger").description("buy burger 200g and get second for free").startDate(Date.valueOf(LocalDate.now())).endDate(Date.valueOf(LocalDate.now().plusWeeks(2))).amount(100).price(70.0).image("https://media3.giphy.com/media/IgOEWPOgK6uVa/giphy.gif?cid=ecf05e47dtsmbz3xkfjphy7ul1fj4iluf7uke5ww1kmf7dao&rid=giphy.gif&ct=g").build();

        Coupon c2 = Coupon.builder().company(companyRepository.findById(7).get()).category(Category.ELECTRICITY).title("Dell free mouse").description("buy DELL laptop and get mouse for free").startDate(Date.valueOf(LocalDate.now())).endDate(Date.valueOf(LocalDate.now().plusWeeks(3))).amount(100).price(2900.0).image("https://media1.giphy.com/media/W639VPdqy5uX5NZPvN/giphy.gif?cid=ecf05e47fyz661w3v079lpayvlpazh00yqazjtbphrv1xva4&rid=giphy.gif&ct=g").build();

        Coupon c3 = Coupon.builder().company(companyRepository.findById(4).get()).category(Category.VACATION).title("All included London").description("everything included travel to london").startDate(Date.valueOf(LocalDate.now())).endDate(Date.valueOf(LocalDate.now().plusWeeks(2))).amount(50).price(4200.0).image("https://media1.giphy.com/media/3o6nV8OYdUhiuKja1i/giphy.gif?cid=ecf05e47zap0mk2hvwrmdnqhz58v80tufdxbq8tbjpyx3bbq&rid=giphy.gif&ct=g").build();

        Coupon c4 = Coupon.builder().company(companyRepository.findById(1).get()).category(Category.ELECTRICITY).title("Laptop + bag").description("buy laptop get free bag").startDate(Date.valueOf(LocalDate.now())).endDate(Date.valueOf(LocalDate.now().plusWeeks(4))).amount(100).price(3200.0).image("https://media1.giphy.com/media/VbnUQpnihPSIgIXuZv/giphy.gif?cid=ecf05e47buqlag8yzqk4s9ipwacv2n543e4i6jsuv6afgok5&rid=giphy.gif&ct=g").build();

        Coupon c5 = Coupon.builder().company(companyRepository.findById(9).get()).category(Category.FOOD).title("6+2 ON ALL DRINKS").description("get 8 carton instead of 6 - All Drinks").startDate(Date.valueOf(LocalDate.now().minusWeeks(2))).endDate(Date.valueOf(LocalDate.now().minusDays(2))).amount(50).price(48.0).image("https://media4.giphy.com/media/BdXrpSYzVOf0A/giphy.gif?cid=ecf05e47q738c0ciq6v7l7e93d0dueihxw8y7kqjgiprr2g4&ep=v1_gifs_search&rid=giphy.gif&ct=g").build();

        List<Coupon> coupons = List.of(c1, c2, c3, c4, c5);
        return coupons;
    }

    public List<Customer> initCustomers() {
        Customer c1 = Customer.builder().firstName("John").lastName("Doe").email("johndoe@email.com").password("1234").build();

        Customer c2 = Customer.builder().firstName("Jane").lastName("Smith").email("janesmith@email.com").password("1234").build();

        Customer c3 = Customer.builder().firstName("Bob").lastName("Jones").email("bobjones@email.com").password("1234").build();

        Customer c4 = Customer.builder().firstName("Samantha").lastName("Lee").email("samlee@email.com").password("1234").build();

        Customer c5 = Customer.builder().firstName("Michael").lastName("Johnson").email("michaelj@email.com").password("1234").build();

        Customer c6 = Customer.builder().firstName("Emily").lastName("Davis").email("emilyd@email.com").password("1234").build();

        Customer c7 = Customer.builder().firstName("Chris").lastName("Wilson").email("chrisw@email.com").password("1234").build();

        Customer c8 = Customer.builder().firstName("Amanda").lastName("Taylor").email("amandat@email.com").password("1234").build();

        Customer c9 = Customer.builder().firstName("David").lastName("Garcia").email("davidg@email.com").password("1234").build();

        Customer c10 = Customer.builder().firstName("Maria").lastName("Martinez").email("mariam@email.com").password("1234").build();

        List<Customer> customers = List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);
        return customers;
    }
}
