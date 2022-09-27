package com.emp.enums;

public enum JobEnum {
	
	STAFF("0", "行政"),
	COACH("1", "教練");

	private final String number;
	private final String title;
	
	private JobEnum(String number, String title) {
		this.number = number;
		this.title = title;
	}
	
	public String getNumber() {
		return number;
	}
	
	public String getTitle() {
		return title;
	}

}
