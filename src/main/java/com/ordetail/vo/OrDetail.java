package com.ordetail.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer orderCode;
	private Integer orderId;
	private Integer idvId;
	private Timestamp pickupTime;
	private Timestamp returnTime;
	private String status;
	
	public OrDetail(Integer orderCode, Integer orderId, Integer idvId, Timestamp pickupTime, Timestamp returnTime,
			String status) {
		super();
		this.orderCode = orderCode;
		this.orderId = orderId;
		this.idvId = idvId;
		this.pickupTime = pickupTime;
		this.returnTime = returnTime;
		this.status = status;
	}

	public OrDetail() {
		// TODO Auto-generated constructor stub
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

	
	
}
