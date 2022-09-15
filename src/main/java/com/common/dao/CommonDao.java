package com.common.dao;

import java.util.List;

public interface CommonDao<V, I> {

	boolean insert(V vo);
	
	boolean update(V vo);

	boolean delete(I id);

	V selectById(I id);

	List<V> selectAll();
	
}
