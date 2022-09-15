package com.coabooking.vo;

import java.sql.Date;

public class CoaBooking {
	private Integer coabookId;
	private Integer memId;
	private Integer coaId;
	private Date coabookTime;
	private String coabookStatus;
	private Date checkTime;
	
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
	public Date getCoabookTime() {
		return coabookTime;
	}
	public void setCoabookTime(Date coabookTime) {
		this.coabookTime = coabookTime;
	}
	public String getCoabookStatus() {
		return coabookStatus;
	}
	public void setCoabookStatus(String coabookStatus) {
		this.coabookStatus = coabookStatus;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
}
