package com.mem.vo;

import java.sql.Date;

import com.common.pojo.Common;

public class Member extends Common {
	private static final long serialVersionUID = 1L;
	
	private Integer memId;
	private String memName;
	private String memNickname;
	private String memUsername;
	private String memPassword;
	private String memPhone;
	private String memEmail;
	private String memGender;
	private Date memBirth;
	private Date memRegister;
	private Date memStart;
	private Date memExpire;
	private Date memLogin;
	private String memStatus;
	private byte[] memPic;
	private String memQrCode;
	private String memNewPassword;

	public String getMemNewPassword() {
		return memNewPassword;
	}

	public void setMemNewPassword(String memNewPassword) {
		this.memNewPassword = memNewPassword;
	}

	public Member() {
		
	}
	
	public Member(Integer memId, String memName, String memNickname, String memUsername, String memPassword, String memPhone, String memEmail, String memGender, Date memBirth, Date memRegister, Date memStart, Date memExpire, Date memLogin, String memStatus, byte[] memPic, String memQrCode) {
		this.memId = memId;
		this.memName = memName;
		this.memNickname = memNickname;
		this.memUsername = memUsername;
		this.memPassword = memPassword;
		this.memPhone = memPhone;
		this.memEmail = memEmail;
		this.memGender = memGender;
		this.memBirth = memBirth;
		this.memRegister = memRegister;
		this.memStart = memStart;
		this.memExpire = memExpire;
		this.memLogin = memLogin;
		this.memStatus = memStatus;
		this.memPic = memPic;
		this.memQrCode = memQrCode;
	}

	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemNickname() {
		return memNickname;
	}
	public void setMemNickname(String memNickname) {
		this.memNickname = memNickname;
	}
	public String getMemUsername() {
		return memUsername;
	}
	public void setMemUsername(String memUsername) {
		this.memUsername = memUsername;
	}
	public String getMemPassword() {
		return memPassword;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemGender() {
		return memGender;
	}
	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}
	public Date getMemBirth() {
		return memBirth;
	}
	public void setMemBirth(Date memBirth) {
		this.memBirth = memBirth;
	}
	public Date getMemRegister() {
		return memRegister;
	}
	public void setMemRegister(Date memRegister) {
		this.memRegister = memRegister;
	}
	public Date getMemStart() {
		return memStart;
	}
	public void setMemStart(Date memStart) {
		this.memStart = memStart;
	}
	public Date getMemExpire() {
		return memExpire;
	}
	public void setMemExpire(Date memExpire) {
		this.memExpire = memExpire;
	}
	public Date getMemLogin() {
		return memLogin;
	}
	public void setMemLogin(Date memLogin) {
		this.memLogin = memLogin;
	}
	public String getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(String memStatus) {
		this.memStatus = memStatus;
	}
	public byte[] getMemPic() {
		return memPic;
	}
	public void setMemPic(byte[] memPic) {
		this.memPic = memPic;
	}
	public String getMemQrCode() {
		return memQrCode;
	}
	public void setMemQrCode(String memQrCode) {
		this.memQrCode = memQrCode;
	}
	

}
