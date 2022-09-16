package com.courbooking.vo;

import java.sql.Timestamp;

public class CourBooking {
	private Integer courbookId;
	private Integer memId;
	private Integer courId;
	private Timestamp courbookTime;
	private String courbookStatus;
	
	
	public Integer getCourbookId() {
		return courbookId;
	}
	public void setCourbookId(Integer courbookId) {
		this.courbookId = courbookId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getCourId() {
		return courId;
	}
	public void setCourId(Integer courId) {
		this.courId = courId;
	}
	public Timestamp getCourbookTime() {
		return courbookTime;
	}
	public void setCourbookTime(Timestamp courbookTime) {
		this.courbookTime = courbookTime;
	}
	public String getCourbookStatus() {
		return courbookStatus;
	}
	public void setCourbookStatus(String courbookStatus) {
		this.courbookStatus = courbookStatus;
	}
}
