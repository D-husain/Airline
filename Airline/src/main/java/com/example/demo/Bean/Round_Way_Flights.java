package com.example.demo.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roundway")
public class Round_Way_Flights {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String fimg;
	private String rimg;
	private String fname;
	private String rname;
	private String source;
	private String dastination;
	private String day;
	private String luggage;
	private String departuredate;
	private String departuretime;
	private String arrivaltime;
	private String returndate;
	private String rdeparturetime;
	private String rarrivaltime;
	private String duration;
	private String rduration;
	private String stop;
	private double price;
	
	
	public Round_Way_Flights() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Round_Way_Flights [id=" + id + ", fimg=" + fimg + ", rimg=" + rimg + ", fname=" + fname + ", rname="
				+ rname + ", source=" + source + ", dastination=" + dastination + ", day=" + day + ", luggage="
				+ luggage + ", departuredate=" + departuredate + ", departuretime=" + departuretime + ", arrivaltime="
				+ arrivaltime + ", returndate=" + returndate + ", rdeparturetime=" + rdeparturetime + ", rarrivaltime="
				+ rarrivaltime + ", duration=" + duration + ", rduration=" + rduration + ", stop=" + stop + ", price="
				+ price + "]";
	}
	public Round_Way_Flights(int id, String fimg, String rimg, String fname, String rname, String source,
			String dastination, String day, String luggage, String departuredate, String departuretime,
			String arrivaltime, String returndate, String rdeparturetime, String rarrivaltime, String duration,
			String rduration, String stop, double price) {
		super();
		this.id = id;
		this.fimg = fimg;
		this.rimg = rimg;
		this.fname = fname;
		this.rname = rname;
		this.source = source;
		this.dastination = dastination;
		this.day = day;
		this.luggage = luggage;
		this.departuredate = departuredate;
		this.departuretime = departuretime;
		this.arrivaltime = arrivaltime;
		this.returndate = returndate;
		this.rdeparturetime = rdeparturetime;
		this.rarrivaltime = rarrivaltime;
		this.duration = duration;
		this.rduration = rduration;
		this.stop = stop;
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
	public String getRimg() {
		return rimg;
	}
	public void setRimg(String rimg) {
		this.rimg = rimg;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
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
	public String getDeparturedate() {
		return departuredate;
	}
	public void setDeparturedate(String departuredate) {
		this.departuredate = departuredate;
	}
	public String getDeparturetime() {
		return departuretime;
	}
	public void setDeparturetime(String departuretime) {
		this.departuretime = departuretime;
	}
	public String getArrivaltime() {
		return arrivaltime;
	}
	public void setArrivaltime(String arrivaltime) {
		this.arrivaltime = arrivaltime;
	}
	public String getReturndate() {
		return returndate;
	}
	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}
	public String getRdeparturetime() {
		return rdeparturetime;
	}
	public void setRdeparturetime(String rdeparturetime) {
		this.rdeparturetime = rdeparturetime;
	}
	public String getRarrivaltime() {
		return rarrivaltime;
	}
	public void setRarrivaltime(String rarrivaltime) {
		this.rarrivaltime = rarrivaltime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getRduration() {
		return rduration;
	}
	public void setRduration(String rduration) {
		this.rduration = rduration;
	}
	public String getStop() {
		return stop;
	}
	public void setStop(String stop) {
		this.stop = stop;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
