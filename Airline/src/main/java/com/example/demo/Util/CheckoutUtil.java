package com.example.demo.Util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Bean.Checkout;

public interface CheckoutUtil extends JpaRepository<Checkout, Integer>{

}
