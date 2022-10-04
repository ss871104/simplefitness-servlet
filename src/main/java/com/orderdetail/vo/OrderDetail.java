package com.orderdetail.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.idvproduct.vo.IdvProduct;

public class OrderDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer orderCode;
	private Integer orderId;
	private Integer idvId;
	private Timestamp pickupTime;
	private Timestamp returnTime;
	private String status;
	private Integer gymId;
	private Integer prodId;
	private Integer inCart;
	
	public OrderDetail(Integer orderCode, Integer orderId, Integer idvId, Timestamp pickupTime, Timestamp returnTime,
			String status) {
		super();
		this.orderCode = orderCode;
		this.orderId = orderId;
		this.idvId = idvId;
		this.pickupTime = pickupTime;
		this.returnTime = returnTime;
		this.status = status;
	}

	public OrderDetail() {
	}

	public Integer getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getIdvId() {
		return idvId;
	}

	public void setIdvId(Integer idvId) {
		this.idvId = idvId;
	}

	public Timestamp getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Timestamp pickupTime) {
		this.pickupTime = pickupTime;
	}

	public Timestamp getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Timestamp returnTime) {
		this.returnTime = returnTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Integer getInCart() {
		return inCart;
	}

	public void setInCart(Integer inCart) {
		this.inCart = inCart;
	}

	
	
}
