package com.courlist.vo;

import com.common.pojo.Common;

public class CourList extends Common {
	private static final long serialVersionUID = 1L;
	
	private Integer courListId;
	private String courName;
	private String courType;
	private Integer courMaxP;
	private String courIntro;
	private String courStatus;
	
	
	public Integer getCourListId() {
		return courListId;
	}
	public void setCourListId(Integer courListId) {
		this.courListId = courListId;
	}
	public String getCourName() {
		return courName;
	}
	public void setCourName(String courName) {
		this.courName = courName;
	}
	public String getCourType() {
		return courType;
	}
	public void setCourType(String courType) {
		this.courType = courType;
	}
	public Integer getCourMaxP() {
		return courMaxP;
	}
	public void setCourMaxP(Integer courMaxP) {
		this.courMaxP = courMaxP;
	}
	public String getCourIntro() {
		return courIntro;
	}
	public void setCourIntro(String courIntro) {
		this.courIntro = courIntro;
	}
	public String getCourStatus() {
		return courStatus;
	}
	public void setCourStatus(String courStatus) {
		this.courStatus = courStatus;
	}
	
	
}