package com.courbooking.dao.sql;

public class CourBookingDaoSQL {
	public static final String INSERT = "INSERT INTO COUR_BOOKING (memId,courId,courbookTime,courbookStatus) VALUES (?, ?, ?, ?)";
	public static final String SELECT_ALL = "SELECT courbookId,memId,courId,courbookTime,courbookStatus FROM COUR_BOOKING order by courbookId";
	public static final String SELECT_BY_ID = "SELECT courbookId,memId,courId,courbookTime,courbookStatus FROM COUR_BOOKING where courbookId = ?";
	public static final String DELETE = "DELETE FROM COUR_BOOKING where coabookId = ?";
	public static final String UPDATE = "UPDATE COUR_BOOKING set memId=?, courId=?, courbookTime=?, courbookStatus=? where courbookId = ?";
	
}
