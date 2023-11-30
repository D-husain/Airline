package com.example.demo.Util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Bean.User;

public interface UserUtil extends JpaRepository<User, Integer> {

	 @Query("select u from User u where u.email = :email")
	    public User getUserByUserName(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email); 
	
	@Query("SELECT u FROM User u WHERE u.email = ?1")
    public User matchEmail(String email); 
     
    
    
    public boolean existsByEmail(String email);
    
}
