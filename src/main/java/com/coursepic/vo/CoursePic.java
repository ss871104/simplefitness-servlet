package com.coursepic.vo;

public class CoursePic {
	private Integer coursepicId;
	private Integer courselistId;
	private byte[] coursePic;
	
	public Integer getCoursepicId() {
		return coursepicId;
	}
	public void setCoursepicId(Integer coursepicId) {
		this.coursepicId = coursepicId;
	}
	public Integer getCourselistId() {
		return courselistId;
	}
	public void setCourselistId(Integer courselistId) {
		this.courselistId = courselistId;
	}
	public byte[] getCoursePic() {
		return coursePic;
	}
	public void setCoursePic(byte[] coursePic) {
		this.coursePic = coursePic;
	}
}
