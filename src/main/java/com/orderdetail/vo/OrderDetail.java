package com.orderdetail.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.common.pojo.Common;

public class OrderDetail extends Common implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer orderCode;
	private Integer orderId;
	private Integer idvId;
	private LocalDateTime pickupTime;
	private LocalDateTime returnTime;
	private String status;
	private Integer gymId;
	private Integer prodId;
	private Integer inCart;
	private String prodName;
	
	public OrderDetail(Integer orderCode, Integer orderId, Integer idvId, LocalDateTime pickupTime, LocalDateTime returnTime,
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

	public LocalDateTime getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(LocalDateTime pickupTime) {
		this.pickupTime = pickupTime;
	}

	public LocalDateTime getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(LocalDateTime returnTime) {
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

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	
	
}
