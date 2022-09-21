package com.common.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class CommonHibernate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private boolean successful;
	private String message;

}
