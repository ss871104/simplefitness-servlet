package com.order.vo;

import java.sql.Timestamp;
import java.util.List;

import com.common.pojo.Common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Common {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Integer memId;
	private Integer gymId;
	private Integer amount;
	private Timestamp orderDate;     
	private String status;
	private String gymName;
	private String memName;
	private List<CountVO> orderList;
	
}
