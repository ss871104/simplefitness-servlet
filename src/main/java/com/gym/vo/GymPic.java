package com.gym.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.pojo.CommonHibernate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gym_pic")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GymPic extends CommonHibernate {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gym_pic_id")
	private Integer picId;
	@Column(name = "gym_id")
	private Integer gymId;
	private byte[] pic;
	private String picBase64;
	
	public GymPic(byte[] pic) {
		super();
		this.pic = pic;
	}
	
	
}
