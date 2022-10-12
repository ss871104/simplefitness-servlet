package com.coachbooking.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.coach.dao.impl.CoachDaoImpl;
import com.coach.dao.intf.CoachDaoIntf;
import com.coach.vo.Coach;
import com.coachbooking.dao.impl.CoachBookingDaoImpl;
import com.coachbooking.dao.intf.CoachBookingDaoIntf;
import com.coachbooking.service.intf.CoachBookingServiceIntf;
import com.coachbooking.vo.CoachBooking;
import com.common.util.JavaMailThread;
import com.emp.dao.impl.EmployeeDaoImpl;
import com.emp.dao.intf.EmployeeDaoIntf;
import com.emp.vo.Employee;
import com.mem.dao.impl.MemDaoImpl;
import com.mem.dao.intf.MemDaoIntf;
import com.mem.vo.Member;

public class CoachBookingServiceImpl implements CoachBookingServiceIntf {

	private CoachBookingDaoIntf _coachBookingDao;
	private CoachDaoIntf _coachDao;
	private EmployeeDaoIntf _empDao;
	private MemDaoIntf _memberDao;

	public CoachBookingServiceImpl() {
		_coachBookingDao = new CoachBookingDaoImpl();
		_coachDao = new CoachDaoImpl();
		_empDao = new EmployeeDaoImpl();
		_memberDao = new MemDaoImpl();

	}

	// 獲取教練名單(empJob="1")
	public List<Employee> searchCoachByJob() {
		var emplist = _empDao.selectAll();
		List<Employee> empCoachList = new ArrayList<Employee>();

		// 寫法1
		for (Employee emp : emplist) {
			if ("1".equals(emp.getJob())) {
				empCoachList.add(emp);
			}
		}
		// 寫法2
//		emplist.stream().filter(emp->"1".equals(emp.getJob())).collect(Collectors.toList());
		return empCoachList;
	}

	// 獲取可預約教練課課程清單
	public List<Coach> searchCoachByGymIdAndEmpId(CoachBooking coachbook) {

		var coachlist = _coachDao.selectCoachByGymIdAndEmpId(coachbook.getGymId(), coachbook.getEmpId());

		return coachlist;
	}

	// 成立教練課預約(coachbooking呼叫insert)
	public boolean createCoachBooking(CoachBooking coachbook) {
		return _coachBookingDao.insert(coachbook);
	}

	// 修改教練課清單狀態為不可預約(coach status="2")
	public boolean setCoachStatusUnableBooking(Coach coach) {
		coach.setStatus("2");
		return _coachDao.updateCoachStatus(coach);
	}

	// 取消教練課
	public boolean cancelCoachByCoachBookingId(CoachBooking coachbook) {
		coachbook.setCoachbookStatus("0");
		return _coachBookingDao.updateCoachBookingStatus(coachbook);
	}

	// 取出該會員的預約清單
	public List<CoachBooking> checkBookingCoachByMemberId(CoachBooking coachbook) {
		List<CoachBooking> coachBookingList = _coachBookingDao
				.selectBookingCoachClassByMemIdAndGymId(coachbook.getMemId(), coachbook.getGymId());

		if (!coachBookingList.isEmpty()) {
			coachBookingList.forEach(item -> {
				item.setCoachClass(_coachDao.selectCoachClassDetailByCoachId(item.getCoachId()));
			});
		}
		return coachBookingList;
	}

	// 開放課程狀態為可預約
	public void setCoachBookingEnable(Integer coachId) {
		_coachDao.setCoachEnable(coachId);

	}

	
	private void sendMail(String mailTo, String subject, String mailText) {
		// JavaMail執行緒
		JavaMailThread.to = mailTo;
		JavaMailThread.subject = subject;
		JavaMailThread.messageText = mailText;
		JavaMailThread jmt = new JavaMailThread();
		jmt.start();
	}

	//寄送預約確認信給教練
	public void sendCheckBookingMail(Integer empId) {
		Employee emp = new Employee();
		emp = _empDao.selectById(empId);
		
		var mailText =  "Hello!" + emp.getEmpName() + "有學員預約您的課程唷，請至教練專區回覆預約" + "\n"
				+ "http://localhost:8080/simplefitness-servlet/html/staff/staff_home.html";
		sendMail(emp.getEmail(),"教練課預約通知",mailText);		
	}

	//寄送取消預約信給教練
	public void sendCancelMailToCaoch(Integer empId) {
		Employee emp = new Employee();
		emp = _empDao.selectById(empId);
		var mailText =  "Hello!" + emp.getEmpName() + "有學員取消您的課程唷，請至教練專區確認" + "\n"
				+ "http://localhost:8080/simplefitness-servlet/html/staff/staff_home.html";
		sendMail(emp.getEmail(),"取消教練課預約通知",mailText);
		
	}

	//寄送預約成功信給會員
	public void sendBookingSuccessMail(Integer memberId) {
		Member member = new Member();
		member = _memberDao.selectById(memberId);
		var mailText =  "Hello!" + member.getMemName() + "感謝您的預約，教練已接受您的預約，請至會員專區確認" + "\n"
				+ "http://localhost:8080/simplefitness-servlet/html/member/member_home.html";
		sendMail(member.getMemEmail(),"教練課預約成功通知",mailText);
		
	}

	//寄送取消預約信給會員
	public void sendCancelMailToMember(Integer memberId) {
		Member member = new Member();
		member = _memberDao.selectById(memberId);
		var mailText =  "Hello!" + member.getMemName() + "很抱歉因教練個人因素，取消您已預約的課程，請至會員專區確認，很感謝您的預約" + "\n"
				+ "http://localhost:8080/simplefitness-servlet/html/member/member_home.html";
		sendMail(member.getMemEmail(),"取消教練課預約通知",mailText);
		
	}

	//取出該教練的預約清單
	public List<CoachBooking> checkBookingCoachByEmpId(CoachBooking coachBooking) {
		List<CoachBooking> coachBookingList = _coachBookingDao
				.selectBookingCoachClassByEmpId(coachBooking.getEmpId());
		
		if(!coachBookingList.isEmpty()) {
			coachBookingList.forEach(item -> {
				item.setMemberDetail(_memberDao.selectById(item.getMemId()));
				item.setCoachClass(_coachDao.selectCoachClassDetailByCoachId(item.getCoachId()));
			});
		}
		return coachBookingList;
	}

	//教練接受預約
	public boolean acceptCoachByCoachBookingId(CoachBooking coachBooking) {
		coachBooking.setCoachbookStatus("2");
		return _coachBookingDao.updateCoachBookingStatus(coachBooking);
	}

}
