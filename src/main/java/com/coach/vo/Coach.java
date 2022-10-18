package com.coach.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.common.pojo.Common;

public class Coach extends Common {
	private static final long serialVersionUID = 1L;
	
	private Integer coaId;
	private Integer empId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private LocalDateTime uploadTime;
	private String status;
	private String pubStatus;
	private Integer gymId;
	private String gymName;
	private String empName;
	private Integer memId;
	private LocalDate selectedDate;
	private LocalDate dayOne;
	private LocalDate daySeven;
	
	
	public Integer getCoaId() {
		return coaId;
	}
	public void setCoaId(Integer coaId) {
		this.coaId = coaId;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public LocalDateTime getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(LocalDateTime uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPubStatus() {
		return pubStatus;
	}
	public void setPubStatus(String pubStatus) {
		this.pubStatus = pubStatus;
	}
	public Integer getGymId() {
		return gymId;
	}
	public void setGymId(Integer gymId) {
		this.gymId = gymId;
	}
	public String getGymName() {
		return gymName;
	}
	public void setGymName(String gymName) {
		this.gymName = gymName;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public LocalDate getSelectedDate() {
		return selectedDate;
	}
	public void setSelectedDate(LocalDate selectedDate) {
		this.selectedDate = selectedDate;
	}
	public LocalDate getDayOne() {
		return dayOne;
	}
	public void setDayOne(LocalDate dayOne) {
		this.dayOne = dayOne;
	}
	public LocalDate getDaySeven() {
		return daySeven;
	}
	public void setDaySeven(LocalDate daySeven) {
		this.daySeven = daySeven;
	}

	

	
}