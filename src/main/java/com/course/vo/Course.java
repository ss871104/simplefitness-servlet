package com.course.vo;

import java.sql.Date;

import com.common.pojo.Common;

public class Course extends Common {
	private Integer courId;
	private Integer empId;
	private Integer gymId;
	private Integer courListId;
	private Date startTime;
	private Date endTime;
	private Date uploadTime;
	private String status;
	private String pubStatus;
	

	
	
	public Integer getCourId() {
		return courId;
	}
	public void setCourId(Integer courId) {
		this.courId = courId;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public Integer getGymId() {
		return gymId;
	}
	public void setGymId(Integer gymId) {
		this.gymId = gymId;
	}
	public Integer getCourListId() {
		return courListId;
	}
	public void setCourListId(Integer courListId) {
		this.courListId = courListId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
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