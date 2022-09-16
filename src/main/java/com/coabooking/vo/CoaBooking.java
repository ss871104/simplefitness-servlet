package com.coabooking.vo;

import java.sql.Timestamp;

public class CoaBooking {
	private Integer coabookId;
	private Integer memId;
	private Integer coaId;
	private Timestamp coabookTime;
	private String coabookStatus;
	private Timestamp checkTime;
	
	public Integer getCoabookId() {
		return coabookId;
	}
	public void setCoabookId(Integer coabookId) {
		this.coabookId = coabookId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getCoaId() {
		return coaId;
	}
	public void setCoaId(Integer coaId) {
		this.coaId = coaId;
	}
	public Timestamp getCoabookTime() {
		return coabookTime;
	}
	public void setCoabookTime(Timestamp coabookTime) {
		this.coabookTime = coabookTime;
	}
	public String getCoabookStatus() {
		return coabookStatus;
	}
	public void setCoabookStatus(String coabookStatus) {
		this.coabookStatus = coabookStatus;
	}
	public Timestamp getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Timestamp checkTime) {
		this.checkTime = checkTime;
	}
}
