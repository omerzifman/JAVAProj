package com.spring.entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Session implements Comparable<Object>,Serializable{
	private String sessionID;
	private String hallID;
	private Date date;
	private Date time;
	private int capacity;
	private float ticketPrice;
	private String movieID;
	public Session(String sessionID,String hallID, String date, String time,int capacity, float ticketPrice, String movieID) {
		this.sessionID=sessionID;
		this.hallID=hallID;
		SimpleDateFormat d1= new SimpleDateFormat("yyyy/MM/dd");
		try {
			this.date=d1.parse(date);
		} catch (ParseException e) {
			System.out.println("Bad date format");
		}
		SimpleDateFormat t1= new SimpleDateFormat("hh:mm");
		try {
			this.time=t1.parse(time);
		} catch (ParseException e) {
			System.out.println("Bad time format");
		}
		this.capacity=capacity;
		this.ticketPrice=ticketPrice;
		this.movieID = movieID;
	}
	public String getMovieID()
	{
		return movieID;
	}
	public String getSessionID()
	{
		return sessionID;
	}
	public int getCapacity()
	{
		return capacity;
	}
	@Override
	public String toString() {
		return String.format("ID: %s\n hall: %s\n date: %s\n time: %s\n capacity: %d\n ticketPrice: %f\n movie: %s\n", sessionID, hallID, date, time, capacity, ticketPrice, movieID);
	}

	@Override
	public boolean equals(Object obj) {
		Session s;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj.getClass() == this.getClass()) {
			s = (Session) obj;
			return this.sessionID == s.sessionID;
		} else
			return false;
	}

	@Override
	public int compareTo(Object o) {
		if(this.date.equals(((Session)o).date))
			return this.time.compareTo(((Session)o).time);
		return this.date.compareTo(((Session)o).date);
	}
}