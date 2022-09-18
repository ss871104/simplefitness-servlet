package com.courselist.vo;

import com.common.pojo.Common;

public class CourseList extends Common {
	private static final long serialVersionUID = 1L;
	
	private Integer courseListId;
	private String courseName;
	private String courseType;
	private Integer courseMaxP;
	private String courseIntro;
	private String courseStatus;
	
	public Integer getCourseListId() {
		
		return courseListId;
	}
	public void setCourseListId(Integer courseListId) {
		this.courseListId = courseListId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public Integer getCourseMaxP() {
		return courseMaxP;
	}
	public void setCourseMaxP(Integer courseMaxP) {
		this.courseMaxP = courseMaxP;
	}
	public String getCourseIntro() {
		return courseIntro;
	}
	public void setCourseIntro(String courseIntro) {
		this.courseIntro = courseIntro;
	}
	public String getCourseStatus() {
		return courseStatus;
	}
	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}
	
	
}