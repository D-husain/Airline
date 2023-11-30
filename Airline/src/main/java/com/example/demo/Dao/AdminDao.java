package com.example.demo.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Bean.Admin;
import com.example.demo.Bean.Cities;
import com.example.demo.Bean.One_Way_Flight;
import com.example.demo.Bean.Round_Way_Flights;
import com.example.demo.Bean.Services;
import com.example.demo.Util.AdminUtil;
import com.example.demo.Util.CitiesUtil;
import com.example.demo.Util.Oneway_util;
import com.example.demo.Util.Round_Util;
import com.example.demo.Util.ServicesUtil;

@Service
public class AdminDao {
	
/*---------------------------------------------------------------------------------------------------------------*/	
	@Autowired
	private AdminUtil autil;
	
	public boolean adminregister(Admin admin) {
		boolean f = false;
		try {
			this.autil.save(admin);
			return f = true;
		} catch (Exception e) {
			System.out.println("error : " + e);
		}
		return f;
	}

	public List<Admin> fechAllAdmin() {
		return this.autil.findAll();
	}
	
	public Admin getAdminById(int id) {
		Optional<Admin> a = autil.findById(id);
		if (a.isPresent()) {
			return a.get();
		}
		return null;
	}
/*---------------------------------------------------------------------------------------------------------------*/
	@Autowired
	private ServicesUtil sutil;
	
	public boolean addservices(Services s) {
		return this.sutil.save(s) != null;
	}
	
	public List<Services> showservices(){
        return sutil.findAll();
    }
	
	public Services getServicesById(int id) {
		Optional<Services> s = sutil.findById(id);
		if (s.isPresent()) {
			return s.get();
		}
		return null;
	}

	public void deleteservices(int id) {
		sutil.deleteById(id);
	}
	
/*---------------------------------------------------------------------------------------------------------------*/	

	@Autowired
	private Round_Util rutil;
	
	public boolean Round_Way_Flights(Round_Way_Flights f) {
		return this.rutil.save(f) != null;
	}
	
	public List<Round_Way_Flights> showRound_Wayflight(){
        return rutil.findAll();
    }
	
	public Round_Way_Flights getFlightById(int id) {
		Optional<Round_Way_Flights> f = rutil.findById(id);
		if (f.isPresent()) {
			return f.get();
		}
		return null;
	}
	
	public void deleteflightr(int id) {
		rutil.deleteById(id);
	}
	
/*---------------------------------------------------------------------------------------------------------------*/	
	
	@Autowired
	private Oneway_util outil;
	
	public boolean Oneway_Flights(One_Way_Flight f) {
		return this.outil.save(f) != null;
	}
	
	public List<One_Way_Flight> showOneway_Flights(){
        return outil.findAll();
    }
	
	public One_Way_Flight getFlightsById(int id) {
		Optional<One_Way_Flight> f = outil.findById(id);
		if (f.isPresent()) {
			return f.get();
		}
		return null;
	}
	
	public void deleteflighto(int id) {
		outil.deleteById(id);
	}
	
/*---------------------------------------------------------------------------------------------------------------*/	

	@Autowired
	private CitiesUtil cutil;
	public boolean addcities(Cities c) {
		return this.cutil.save(c) != null;
	}
	
	public List<Cities> ShowCities(){
        return cutil.findAll();
    }
	
	public void deletecity(int id) {
		cutil.deleteById(id);
	}
}
