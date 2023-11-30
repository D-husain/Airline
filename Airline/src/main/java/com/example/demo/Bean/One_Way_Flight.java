package com.example.demo.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oneway")
public class One_Way_Flight {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String fimg;
	private String fname;
	private String source;
	private String dastination;
	private String day;
	private String luggage;
	private String departuredate;
	private String departuretime;
	private String arrivaltime;
	private String duration;
	private String stop;
	private double price;
	
	public One_Way_Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "One_Way_Flight [id=" + id + ", fimg=" + fimg + ", fname=" + fname + ", source=" + source
				+ ", dastination=" + dastination + ", day=" + day + ", luggage=" + luggage + ", departuretime="
				+ departuretime + ", departuredate=" + departuredate + ", arrivaltime=" + arrivaltime + ", duration="
				+ duration + ", price=" + price + ",stop=" + stop + "]";
	}

	public One_Way_Flight(int id, String fimg, String fname, String source, String dastination, String day,
			String luggage, String departuretime, String departuredate, String arrivaltime,String stop, String duration,
			double price) {
		super();
		this.id = id;
		this.fimg = fimg;
		this.fname = fname;
		this.source = source;
		this.dastination = dastination;
		this.day = day;
		this.luggage = luggage;
		this.departuretime = departuretime;
		this.departuredate = departuredate;
		this.arrivaltime = arrivaltime;
		this.duration = duration;
		this.stop=stop;
		this.price = price;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFimg() {
		return fimg;
	}
	public void setFimg(String fimg) {
		this.fimg = fimg;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDastination() {
		return dastination;
	}
	public void setDastination(String dastination) {
		this.dastination = dastination;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getLuggage() {
		return luggage;
	}
	public void setLuggage(String luggage) {
		this.luggage = luggage;
	}
	public String getDeparturetime() {
		return departuretime;
	}
	public void setDeparturetime(String departuretime) {
		this.departuretime = departuretime;
	}
	public String getDeparturedate() {
		return departuredate;
	}
	public void setDeparturedate(String departuredate) {
		this.departuredate = departuredate;
	}
	public String getArrivaltime() {
		return arrivaltime;
	}
	public void setArrivaltime(String arrivaltime) {
		this.arrivaltime = arrivaltime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getStop() {
		return stop;
	}
	public void setStop(String stop) {
		this.stop = stop;
	}
	
	
}
