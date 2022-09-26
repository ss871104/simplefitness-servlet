package com.emp.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.common.pojo.CommonHibernate;
import com.emp.enums.JobEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "emp")
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
	@Column(insertable = false)
	private String phone;
	@Column(insertable = false)
	private String email;
	private String job;
	private String gender;
	@Column(insertable = false)
	private Date birth;
	@Column(name = "employ_date", insertable = false)
	private Date employDate;
	@Column(insertable = false)
	private String intro;
	private String status;
	@Column(insertable = false)
	private byte[] pic;
	@Transient
	private String picBase64;
	
//	public JobEnum getJob() {
//		return job;
//	}
//	public void setJob(JobEnum number) {
//		job = number;
//	}

}
