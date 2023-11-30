package com.example.demo.Util;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Bean.Round_Way_Flights;

@Repository
public interface Round_Util extends JpaRepository<Round_Way_Flights, Integer> {

	@Query(value = "select * from roundway f where f.source like %:source% and f.dastination like %:dastination% and f.departuredate like %:departuredate% and f.returndate like %:returndate% ",
			nativeQuery = true)
	 List<Round_Way_Flights> findByKeyword(@Param("source") String source,@Param("dastination") String dastination,@Param("departuredate")String departuredate,@Param("returndate")String returndate);


	/*@Query(value = "select * from flight s where s.source like %:keyword% or s.dastination %:keyword%", nativeQuery = true)
	List<Flights> findByKeyword(@Param("keyword") String keyword);*/
	
	//and f.departuredate like %:keyword% and f.arrivaldate like %:keyword%  
}
