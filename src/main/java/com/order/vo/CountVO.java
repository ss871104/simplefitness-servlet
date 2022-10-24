package com.order.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountVO {
	
	private Integer inCart; 
	private Integer prodId;
	private String prodName;
}
