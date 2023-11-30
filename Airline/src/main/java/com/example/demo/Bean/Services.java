package com.example.demo.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="services")
public class Services {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String sname;
	private String simg;
	@Column(length = 1000)
	private String sdes;
	
	public Services() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Services [id=" + id + ", sname=" + sname + ", simg=" + simg + ", sdes=" + sdes + "]";
	}
	public Services(int id, String sname, String simg, String sdes) {
		super();
		this.id = id;
		this.sname = sname;
		this.simg = simg;
		this.sdes = sdes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSimg() {
		return simg;
	}
	public void setSimg(String simg) {
		this.simg = simg;
	}
	public String getSdes() {
		return sdes;
	}
	public void setSdes(String sdes) {
		this.sdes = sdes;
	}
	
}
