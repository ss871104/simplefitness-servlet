package com.coach.vo;

import java.sql.Date;
import java.sql.Timestamp;

import com.common.pojo.Common;

public class Coach extends Common {
	private static final long serialVersionUID = 1L;
	
	private Integer coaId;
	private Integer empId;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp uploadTime;
	private String status;
	private String pubStatus;
	
	
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
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime) {
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

	
	
	
	
	
}