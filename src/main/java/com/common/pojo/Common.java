package com.common.pojo;

import java.io.Serializable;

public class Common implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private boolean successful;
	private String message;
	
	public Common() {
	}

	public Common(boolean successful, String message) {
		this.successful = successful;
		this.message = message;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
