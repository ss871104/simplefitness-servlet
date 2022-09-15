package com.coabooking.dao.sql;

public class CoaBookingDaoSQL {
	public static final String INSERT = "INSERT INTO COA_BOOKING (memId,coaId,coabookTime,coabookStatus,checkTime) VALUES (?, ?, ?, ?, ?)";
	public static final String SELECT_ALL = "SELECT coabookId,memId,coaId,coabookTime,coabookStatus,checkTime FROM COA_BOOKING order by coabookId";
	public static final String SELECT_BY_ID = "SELECT coabookId,memId,coaId,coabookTime,coabookStatus,checkTime FROM COA_BOOKING where coabookId = ?";
	public static final String DELETE = "DELETE FROM COA_BOOKING where coabookId = ?";
	public static final String UPDATE = "UPDATE COA_BOOKING set memId=?, coaId=?, coabookTime=?, coabookStatus=?, checkTime=? where coabookId = ?";

}
