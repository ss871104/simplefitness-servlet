package com.order.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo<T> {
	
	private Integer pageNo;
	private Integer pageSize;
	private Integer totalPage;
	private Long totalCount;
	private List<Order> content;
	
}
