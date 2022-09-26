package com.coachbooking.vo;

import java.time.LocalDateTime;

public class CoachBooking {
	private Integer coachbookId;
	private Integer memId;
	private Integer coachId;
	private LocalDateTime coachbookTime;
	private String coachbookStatus;
	private LocalDateTime checkTime;
	private Integer gymId;
	private Integer empId;
	
	public Integer getCoachbookId() {
		return coachbookId;
	}
	public void setCoachbookId(Integer coachbookId) {
		this.coachbookId = coachbookId;
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
	public LocalDateTime getCoachbookTime() {
		return coachbookTime;
	}
	public void setCoachbookTime(LocalDateTime coachbookTime) {
		this.coachbookTime = coachbookTime;
	}
	public String getCoachbookStatus() {
		return coachbookStatus;
	}
	public void setCoachbookStatus(String coachbookStatus) {
		this.coachbookStatus = coachbookStatus;
	}
	public LocalDateTime getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(LocalDateTime checkTime) {
		this.checkTime = checkTime;
	}
	public Integer getGymId() {
		return gymId;
	}
	public void setGymId(Integer gymId) {
		this.gymId = gymId;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	
	
}
