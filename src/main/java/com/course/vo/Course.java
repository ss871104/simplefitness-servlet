package com.course.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.common.pojo.Common;

public class Course extends Common {
	
	private static final long serialVersionUID = 1L;
	private Integer courseId;
	private Integer empId;
	private Integer gymId;
	private Integer courseListId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private LocalDateTime uploadTime;
	private String status;
	private String pubStatus;
	private String courseName;
	private LocalDate selectedDate;
	private LocalDate dayOne;
	private LocalDate daySeven;
	
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
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
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