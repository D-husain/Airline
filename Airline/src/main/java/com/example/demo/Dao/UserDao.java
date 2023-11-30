package com.example.demo.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Bean.Checkout;
import com.example.demo.Bean.Contact;
import com.example.demo.Bean.One_Way_Flight;
import com.example.demo.Bean.Round_Way_Flights;
import com.example.demo.Bean.User;
import com.example.demo.Util.CheckoutUtil;
import com.example.demo.Util.ContactUtil;
import com.example.demo.Util.Oneway_util;
import com.example.demo.Util.Round_Util;
import com.example.demo.Util.UserUtil;

@Service
public class UserDao {

/*---------------------------------------------------------------------------------------------------------------*/
	@Autowired 
	private ContactUtil cutil;
	
	public void addcontact(Contact c) {
		cutil.save(c);
	}
	
	public List<Contact> showcontact(){
        return cutil.findAll();
    }
	
	public Contact getContactById(int id) {
		Optional<Contact> e = cutil.findById(id);
		if (e.isPresent()) {
			return e.get();
		}
		return null;
	}

	public void deletecontact(int id) {
		cutil.deleteById(id);
	}
	
/*---------------------------------------------------------------------------------------------------------------*/
	@Autowired
	private UserUtil uutil;
	
	public boolean register(User user) {
		boolean f = false;
		try {
			this.uutil.save(user);
			return f = true;
		} catch (Exception e) {
			System.out.println("error : " + e);
		}
		return f;
	}
	
	public boolean checkemail(String email) {
		return uutil.existsByEmail(email);
	}
		
	
	public List<User> fechAllUser() {
		return this.uutil.findAll();
	}
	
	public void deleteuser(int id) {
		uutil.deleteById(id);
	}
	
	public User getUserById(int id) {
		Optional<User> u = uutil.findById(id);
		if (u.isPresent()) {
			return u.get();
		}
		return null;
	}
	
     
    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
         
        uutil.save(user);
    }
	
/*---------------------------------------------------------------------------------------------------------------*/
	@Autowired
	private CheckoutUtil Cutil;
	
	public void Addcheckout(Checkout c) {
		Cutil.save(c);
	}
	
	public List<Checkout> showcheckout(){
        return Cutil.findAll();
    }
	
	public void deletedetail(int id) {
		Cutil.deleteById(id);
	}
	
/*---------------------------------------------------------------------------------------------------------------*/
	
	@Autowired
	private Round_Util rutil;
	
	public List<Round_Way_Flights> findallflight(String source,String dastination,String departuredate,String returndate) {
		if (source != null) {
			return rutil.findByKeyword(source, dastination, departuredate, returndate);
		}
		else{
		return  rutil.findAll();
	}
	}
	
	public Round_Way_Flights getRoundById(int id) {
		Optional<Round_Way_Flights> r = rutil.findById(id);
		if (r.isPresent()) {
			return r.get();
		}
		return null;
	}
	
/*---------------------------------------------------------------------------------------------------------------*/	
	
	@Autowired
	private Oneway_util outil;
	
	public List<One_Way_Flight> findallflights(String source,String dastination,String departuredate) {
		if (source != null) {
			return outil.findByKeyword(source, dastination, departuredate);
		}
		else{
		return  outil.findAll();
	}
	}
	
	
	
}
