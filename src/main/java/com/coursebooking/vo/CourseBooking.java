package com.coursebooking.vo;

import java.sql.Timestamp;

public class CourseBooking {
	private Integer coursebookId;
	private Integer memId;
	private Integer courseId;
	private Timestamp coursebookTime;
	private String coursebookStatus;
	
	
	public Integer getCoursebookId() {
		return coursebookId;
	}
	public void setCoursebookId(Integer coursebookId) {
		this.coursebookId = coursebookId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Timestamp getCoursebookTime() {
		return coursebookTime;
	}
	public void setCoursebookTime(Timestamp coursebookTime) {
		this.coursebookTime = coursebookTime;
	}
	public String getCoursebookStatus() {
		return coursebookStatus;
	}
	public void setCoursebookStatus(String coursebookStatus) {
		this.coursebookStatus = coursebookStatus;
	}
}