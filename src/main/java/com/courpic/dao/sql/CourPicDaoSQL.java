package com.courpic.dao.sql;

public class CourPicDaoSQL {
	public static final String INSERT = "INSERT INTO COUR_PIC (cour_list_id,pic) VALUES (?, ?)";
	public static final String SELECT_ALL = "SELECT courpicId,cour_list_id,pic FROM COUR_PIC order by courpicId";
	public static final String SELECT_BY_ID = "SELECT courpicId,cour_list_id,pic FROM COUR_PIC where courpicId = ?";
	public static final String DELETE = "DELETE FROM COUR_PIC where courpicId = ?";
	public static final String UPDATE = "UPDATE COUR_PIC set courpicId=?, cour_list_id=?, pic=? where courpicId = ?";

}
