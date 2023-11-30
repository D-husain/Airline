package com.example.demo.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="checkout")
public class Checkout {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String email;
	private String number;
	private String fname;
	private String lname;
	private String gender;
	
	public Checkout() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Checkout [id=" + id + ", email=" + email + ", number=" + number + ", fname=" + fname + ", lname="
				+ lname + ", gender=" + gender + "]";
	}
	public Checkout(int id, String email, String number, String fname, String lname, String gender) {
		super();
		this.id = id;
		this.email = email;
		this.number = number;
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
