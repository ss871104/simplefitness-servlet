package com.coursepic.dao.sql;

public class CoursePicDaoSQL {
	public static final String INSERT = "insert into cour_pic(cour_list_id,pic) values (?, ?)";
	public static final String SELECT_ALL = "select cour_pic_id ,cour_list_id,pic from cour_pic order by cour_pic_id ";
	public static final String SELECT_BY_ID = "select cour_pic_id ,cour_list_id,pic from cour_pic where cour_pic_id  = ?";
	public static final String DELETE = "delete from cour_pic where cour_pic_id  = ?";
	public static final String UPDATE = "update cour_pic set pic=? where cour_pic_id  = ?";

}
