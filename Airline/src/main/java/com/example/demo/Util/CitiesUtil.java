package com.example.demo.Util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Bean.Cities;

public interface CitiesUtil extends JpaRepository<Cities, Integer> {

}
