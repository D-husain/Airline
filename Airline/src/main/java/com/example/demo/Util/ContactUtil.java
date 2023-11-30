package com.example.demo.Util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Bean.Contact;

public interface ContactUtil extends JpaRepository<Contact, Integer>{

}
