package com.idvproduct.vo;

import com.common.pojo.Common;

public class IdvProduct extends Common{
	private static final long serialVersionUID = 1L;
	
	private Integer idvId;
	private Integer gymId;
	private Integer prodId;
	private String status;
	private Integer count;
	private Integer inCart;
	
	public Integer getIdvId() {
		return idvId;
	}
	public void setIdvId(Integer idvId) {
		this.idvId = idvId;
	}
	public Integer getGymId() {
		return gymId;
	}
	public void setGymId(Integer gymId) {
		this.gymId = gymId;
	}
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getInCart() {
		return inCart;
	}
	public void setInCart(Integer inCart) {
		this.inCart = inCart;
	}
	
	
}