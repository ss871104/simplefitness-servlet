package com.gym.vo;

import java.sql.Date;
import java.time.LocalTime;

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
public class Gym extends CommonHibernate {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gym_id")
	private Integer gymId;
	@Column(name = "gym_name")
	private String gymName;
	private String address;
	private String phone;
	@Column(name = "open_date")
	private Date openDate;
	@Column(name = "open_time")
	private LocalTime openTime;
	@Column(name = "close_time")
	private LocalTime closeTime;
	@Column(name = "max_p")
	private Integer maxPeople;
	private String intro;
}
