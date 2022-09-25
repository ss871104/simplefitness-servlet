package com.emp.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.common.pojo.CommonHibernate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends CommonHibernate{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Integer empId;
	@Column(name = "gym_id")
	private Integer gymId;
	@Column(name = "emp_name")
	private String empName;
	private String nickname;
	private String username;
	@Column(name = "pass")
	private String password;
	private String phone;
	private String email;
	private String job;
	private String gender;
	private Date birth;
	@Column(name = "employ_date")
	private Date employDate;
	private String intro;
	private String status;
	private byte[] pic;
	

}
