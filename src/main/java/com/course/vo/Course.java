package com.course.vo;

import java.sql.Date;
import java.sql.Timestamp;

import com.common.pojo.Common;

public class Course extends Common {
	
	private static final long serialVersionUID = 1L;
	private Integer courseId;
	private Integer empId;
	private Integer gymId;
	private Integer courseListId;
	private Date courseDate;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp uploadTime;
	private String status;
	private String pubStatus;
	
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
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
	public Integer getCourseListId() {
		return courseListId;
	}
	public void setCourseListId(Integer courseListId) {
		this.courseListId = courseListId;
	}
	public Date getCourseDate() {
		return courseDate;
	}
	public void setCourseDate(Date courseDate) {
		this.courseDate = courseDate;
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