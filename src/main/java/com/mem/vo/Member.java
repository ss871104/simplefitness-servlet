package com.mem.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
	private LocalDateTime memLogin;
	private String memStatus;
	private byte[] memPic;
	private String memQrCode;
	private String memNewPassword;
	private String memVerification;
	private String memInputCode;
	private LocalDateTime memCurrentLogin;
	private String memPicBase64;

	public String getMemPicBase64() {
		return memPicBase64;
	}

	public void setMemPicBase64(String memPicBase64) {
		this.memPicBase64 = memPicBase64;
	}

	public LocalDateTime getMemCurrentLogin() {
		return memCurrentLogin;
	}

	public void setMemCurrentLogin(LocalDateTime memCurrentLogin) {
		this.memCurrentLogin = memCurrentLogin;
	}

	public String getMemInputCode() {
		return memInputCode;
	}

	public void setMemInputCode(String memInputCode) {
		this.memInputCode = memInputCode;
	}

	public String getMemVerification() {
		return memVerification;
	}

	public void setMemVerification(String memVerification) {
		this.memVerification = memVerification;
	}

	public String getMemNewPassword() {
		return memNewPassword;
	}

	public void setMemNewPassword(String memNewPassword) {
		this.memNewPassword = memNewPassword;
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
	public LocalDateTime getMemLogin() {
		return memLogin;
	}
	public void setMemLogin(LocalDateTime memLogin) {
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
