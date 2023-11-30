package com.example.demo.Util;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Bean.One_Way_Flight;

@Repository
public interface Oneway_util extends JpaRepository<One_Way_Flight, Integer> {

	@Query(value = "select * from oneway f where f.source like %:source% and f.dastination like %:dastination% and f.departuredate like %:departuredate% ",
			nativeQuery = true)
	 List<One_Way_Flight> findByKeyword(@Param("source") String source,@Param("dastination") String dastination,@Param("departuredate")String departuredate);


	/*@Query(value = "select * from flight s where s.source like %:keyword% or s.dastination %:keyword%", nativeQuery = true)
	List<Flights> findByKeyword(@Param("keyword") String keyword);*/
	
	//and f.departuredate like %:keyword% and f.arrivaldate like %:keyword%  
}
