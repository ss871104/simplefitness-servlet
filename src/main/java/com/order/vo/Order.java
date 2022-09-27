package com.order.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Integer memId;
	private Integer gymId;
	private Integer amount;
	private Timestamp orderDate;     
	private String status;
	
	public Order(Integer orderId, Integer memId, Integer gymId, Integer amount, Timestamp orderDate, String status) {
		super();
		this.orderId = orderId;
		this.memId = memId;
		this.gymId = gymId;
		this.amount = amount;
		this.orderDate = orderDate;
		this.status = status;
	}


	public Order() {
	}


	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Integer getGymId() {
		return gymId;
	}

	public void setGymId(Integer gymId) {
		this.gymId = gymId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
