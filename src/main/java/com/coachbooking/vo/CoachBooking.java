package com.coachbooking.vo;

import java.sql.Timestamp;

public class CoachBooking {
	private Integer coachbookId;
	private Integer memId;
	private Integer coachId;
	private Timestamp coachbookTime;
	private String coachbookStatus;
	private Timestamp checkTime;
	
	public Integer getCoachbookId() {
		return coachbookId;
	}
	public void setCoachbookId(Integer coachbookId) {
		this.coachbookId = coachId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getCoachId() {
		return coachId;
	}
	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
	}
	public Timestamp getCoachbookTime() {
		return coachbookTime;
	}
	public void setCoachbookTime(Timestamp coachbookTime) {
		this.coachbookTime = coachbookTime;
	}
	public String getCoachbookStatus() {
		return coachbookStatus;
	}
	public void setCoachbookStatus(String coachbookStatus) {
		this.coachbookStatus = coachbookStatus;
	}
	public Timestamp getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Timestamp checkTime) {
		this.checkTime = checkTime;
	}
}
