package com.order.vo;

import java.time.LocalDateTime;
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
	private LocalDateTime orderDate;     
	private String status;
	private String gymName;
	private String memName;
	private String memEmail;
	private List<CountVO> orderList;
	
}
