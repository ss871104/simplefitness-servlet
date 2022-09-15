package com.courpic.vo;

public class CourPic {
	private Integer courpicId;
	private Integer courlistId;
	private byte[] courPic;
	
	public Integer getCourpicId() {
		return courpicId;
	}
	public void setCourpicId(Integer courpicId) {
		this.courpicId = courpicId;
	}
	public Integer getCourlistId() {
		return courlistId;
	}
	public void setCourlistId(Integer courlistId) {
		this.courlistId = courlistId;
	}
	public byte[] getCourPic() {
		return courPic;
	}
	public void setCourPic(byte[] courPic) {
		this.courPic = courPic;
	}
}
