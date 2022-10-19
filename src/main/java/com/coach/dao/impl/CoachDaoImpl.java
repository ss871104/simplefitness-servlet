package com.coach.dao.impl;

import static com.coach.dao.sql.CoachDaoSQL.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.coach.dao.intf.CoachDaoIntf;
import com.coach.dao.sql.CoachDaoSQL;
import com.coach.vo.Coach;

public class CoachDaoImpl implements CoachDaoIntf {

	private static DataSource ds = null;
	private static CoachDaoSQL SQL = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Test");
			SQL = new CoachDaoSQL();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(Coach coaVo) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coaVo.getEmpId());
			pstmt.setObject(2, coaVo.getStartTime());
			pstmt.setObject(3, coaVo.getEndTime());
			pstmt.setString(4, coaVo.getStatus());
			pstmt.setString(5, coaVo.getPubStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;

	}

	@Override
	public boolean update(Coach coaVo) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coaVo.getEmpId());
			pstmt.setObject(2, coaVo.getStartTime());
			pstmt.setObject(3, coaVo.getEndTime());
			pstmt.setString(4, coaVo.getStatus());
			pstmt.setString(5, coaVo.getPubStatus());
			pstmt.setInt(6, coaVo.getCoaId());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;

	}

	@Override
	public boolean delete(Integer coaId) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.DELETE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coaId);

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;

	}

	@Override
	public Coach selectById(Integer coaId) {

		Coach coa = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coaId);

			try (ResultSet rs = pstmt.executeQuery()) {

				coa = new Coach();

				if (rs.next()) {
					coa.setCoaId(rs.getInt("coa_id"));
					coa.setEmpId(rs.getInt("emp_id"));
					coa.setStartTime(rs.getObject("start_time", LocalDateTime.class));
					coa.setEndTime(rs.getObject("end_time", LocalDateTime.class));
					coa.setUploadTime(rs.getObject("upload_time", LocalDateTime.class));
					coa.setStatus(rs.getString("status"));
					coa.setPubStatus(rs.getString("public"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coa;

	}

	@Override
	public List<Coach> selectAll() {

		List<Coach> list = new ArrayList<Coach>();
		Coach coa = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_ALL);) {

			System.out.println("連線成功");

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					coa = new Coach();
					coa.setCoaId(rs.getInt("coa_id"));
					coa.setEmpId(rs.getInt("emp_id"));
					coa.setStartTime(rs.getObject("start_time", LocalDateTime.class));
					coa.setEndTime(rs.getObject("end_time", LocalDateTime.class));
					coa.setUploadTime(rs.getObject("upload_time", LocalDateTime.class));
					coa.setStatus(rs.getString("status"));
					coa.setPubStatus(rs.getString("public"));
					list.add(coa);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	/* *
	 *  Function:  取得會員可預約教練課
	 *  CreateBy: Iris
	 *  CreateDate: 2022/09/23
	 * */
	@Override
	public List<Coach> selectCoachByGymIdAndEmpId(Integer gymId, Integer empId) {
		
		Coach coach = null;
		var sqlStr ="select coa_id,gym.gym_id,gym.gym_name,coach.emp_id,emp.emp_name,start_time,end_time \r\n"
				+ "from coach coach \r\n"
				+ "join emp emp on emp.emp_id=coach.emp_id \r\n"
				+ "join gym on emp.gym_id=gym.gym_id\r\n"
				+ "where coach.start_time>now() and coach.`status`='1' and public='1' and gym.gym_id=? and coach.emp_id=?;";
		
		List<Coach> canBookCoachList = new ArrayList<Coach>();
		
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sqlStr);) {

			System.out.println("連線成功");

			pstmt.setInt(1, gymId);
			pstmt.setInt(2, empId);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					coach = new Coach();
					coach.setCoaId(rs.getInt("coa_id"));
					coach.setGymId(rs.getInt("gym_id"));
					coach.setGymName(rs.getString("gym_name"));
					coach.setEmpId(rs.getInt("emp_id"));
					coach.setEmpName(rs.getString("emp_name"));
					coach.setStartTime(rs.getObject("start_time",LocalDateTime.class));
					coach.setEndTime(rs.getObject("end_time",LocalDateTime.class));
					
					canBookCoachList.add(coach);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return canBookCoachList;
	}
	
	/* *
	 *  Function:取得會員教練課程詳細資料
	 *  CreateBy: Iris
	 *  CreateDate: 2022/10/06
	 * */
	public Coach selectCoachClassDetailByCoachId(Integer coachId){
		Coach coach = null;
		var sqlStr = "select coach.*,gym.gym_id,gym.gym_name,emp.emp_name\r\n"
				+ "from coach coach\r\n"
				+ "join emp emp on emp.emp_id=coach.emp_id \r\n"
				+ "join gym on emp.gym_id=gym.gym_id\r\n"
				+ "where coa_id=?;";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sqlStr);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coachId);

			try (ResultSet rs = pstmt.executeQuery()) {

				coach = new Coach();

				if (rs.next()) {
					coach.setCoaId(rs.getInt("coa_id"));
					coach.setEmpId(rs.getInt("emp_id"));
					coach.setStartTime(rs.getObject("start_time", LocalDateTime.class));
					coach.setEndTime(rs.getObject("end_time", LocalDateTime.class));
					coach.setUploadTime(rs.getObject("upload_time", LocalDateTime.class));
					coach.setStatus(rs.getString("status"));
					coach.setPubStatus(rs.getString("public"));
					coach.setGymName(rs.getString("gym_name"));
					coach.setEmpName(rs.getString("emp_name"));
					coach.setGymId(rs.getInt("gym_id"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coach;
	}
	

	/* *
	 *  Function:修改教練課清單狀態
	 *  CreateBy: Iris
	 *  CreateDate: 2022/09/24
	 * */
	@Override
	public boolean updateCoachStatus(Coach coach) {
		
		boolean flag=true;
		var sqlStr = "update coach set status=? where coa_id  = ?";
				
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sqlStr);) {

			System.out.println("連線成功");

			pstmt.setString(1, coach.getStatus());
			pstmt.setInt(2, coach.getCoaId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}
	
	/*
	 * * Function: 開放教練課預約狀態為可預約 
	 *   CreateBy: Iris 
	 *   CreateDate: 2022/10/06
	 */
	public void setCoachEnable(Integer coaId) {
		var sqlStr = "Update simple_fitness.coach Set `status` ='1' Where coa_id=?;";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sqlStr);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coaId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/*
	 * * Function: 確認此時間此教練是否已有教練課  
	 *   CreateBy: Natalie
	 *   CreateDate: 2022/10/06
	 */
	public List<Coach> selectCoachByEmpIdAndStartTime(Integer empId, LocalDateTime startTime) {
		
		List<Coach> list = new ArrayList<Coach>();
		Coach coach = null;
		
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(SELECT_COACH_BY_EMPID_AND_STARTTIME);) {

			System.out.println("連線成功");

			pstmt.setInt(1, empId);
			pstmt.setObject(2, startTime);
			pstmt.setObject(3, startTime);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					coach = new Coach();
					coach.setEmpId(rs.getInt("emp_id"));
					coach.setStartTime(rs.getObject("start_time", LocalDateTime.class));
					coach.setSuccessful(true);
					list.add(coach);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	/*
	 * * Function: 取得已安排團課by場館+日期
	 *   CreateBy: Natalie
	 *   CreateDate: 2022/10/14
	 */
	public List<Coach> selectCoachByGymIdAndStartTime(Integer gymId, LocalDate one, LocalDate seven) {

		List<Coach> list = new ArrayList<Coach>();
		Coach coach = null;

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(SELECT_BY_GYMID_AND_STARTTIME);) {

			System.out.println("連線成功");

			pstmt.setInt(1, gymId);
			pstmt.setObject(2, one);
			pstmt.setObject(3, seven);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					coach = new Coach();
					coach.setCoaId(rs.getInt("coa_id"));
					coach.setGymId(rs.getInt("gym_id"));
					coach.setStartTime(rs.getObject("start_time", LocalDateTime.class));
					coach.setEmpId(rs.getInt("emp_id"));
					coach.setStatus(rs.getString("status"));
					coach.setPubStatus(rs.getString("public"));
					coach.setSuccessful(true);

					list.add(coach);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	/*
	 * * Function: 取得已安排教練課by場館+日期+教練
	 *   CreateBy: Natalie
	 *   CreateDate: 2022/10/18
	 */
	public List<Coach> selectCoachByGymIdAndStartTimeAndEmpId(Integer gymId, LocalDate one, LocalDate seven, Integer empId) {
		List<Coach> list = new ArrayList<Coach>();
		Coach coach = null;

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(SELECT_BY_GYMID_AND_STARTTIME_AND_EMPID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, gymId);
			pstmt.setInt(2, empId);
			pstmt.setObject(3, one);
			pstmt.setObject(4, seven);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					coach = new Coach();
					coach.setCoaId(rs.getInt("coa_id"));
					coach.setGymId(rs.getInt("gym_id"));
					coach.setStartTime(rs.getObject("start_time", LocalDateTime.class));
					coach.setEmpId(rs.getInt("emp_id"));
					coach.setStatus(rs.getString("status"));
					coach.setPubStatus(rs.getString("public"));
					coach.setSuccessful(true);

					list.add(coach);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	/* *
	 *  Function: 變更團課公開狀態
	 *  CreateBy: Natalie
	 *  CreateDate: 2022/10/18
	 * */
	public boolean updateCoachPublicStatus(Coach coach) {
		boolean flag = true;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE_PUBLIC_STATUS_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setString(1, coach.getPubStatus());
			pstmt.setInt(2, coach.getCoaId());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}


}
