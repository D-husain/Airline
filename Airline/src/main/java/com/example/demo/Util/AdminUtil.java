package com.example.demo.Util;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Bean.Admin;
import com.example.demo.Bean.User;

public interface AdminUtil extends JpaRepository<Admin, Integer> {

	
	@Query(value = "SELECT * FROM admin a WHERE a.email = :email", nativeQuery = true)
	public Admin getAdminByName(String email);
	
	@Query("select a from Admin a where a.email = :email")
    public Admin getAdminByAdminName(@Param("email") String email);

	@Query("SELECT a FROM Admin a WHERE a.email = ?1")
    public Admin findByEmail(String email); 
}
