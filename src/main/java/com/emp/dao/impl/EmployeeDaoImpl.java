package com.emp.dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import com.emp.dao.intf.EmployeeDaoIntf;
import com.emp.vo.Employee;

public class EmployeeDaoImpl implements EmployeeDaoIntf{

	@Override
	public boolean insert(Employee emp) {
		getSession().persist(emp);
		return true;
	}

	@Override
	public boolean deleteById(Integer id) {
		Employee emp = new Employee();
		emp.setEmpId(id);
		getSession().remove(emp);
		return true;
	}

	@Override
	public boolean update(Employee emp) {
		final byte[] pic = emp.getPic();
		System.out.println(pic);
		final StringBuilder hql = new StringBuilder()
				.append("UPDATE Employee SET ");
			if (pic != null) {
				hql.append("pic = :pic,");
			}
			hql.append("gymId = :gymId, ")
				.append("empName = :empName, ")
				.append("nickname = :nickname, ")
				.append("phone = :phone, ")
				.append("email = :email, ")
				.append("job = :job, ")
				.append("gender = :gender, ")
				.append("birth = :birth, ")
				.append("intro = :intro, ")
				.append("status = :status ")
				.append("where empId = :empId");

			Query<?> query = getSession().createQuery(hql.toString());
			if (pic != null) {
				query.setParameter("pic", pic);
			}
			return query
					.setParameter("gymId", emp.getGymId())
					.setParameter("empName", emp.getEmpName())
					.setParameter("nickname", emp.getNickname())
					.setParameter("phone", emp.getPhone())
					.setParameter("email", emp.getEmail())
					.setParameter("job", emp.getJob())
					.setParameter("gender", emp.getGender())
					.setParameter("birth", emp.getBirth())
					.setParameter("intro", emp.getIntro())
					.setParameter("status", emp.getStatus())
					.setParameter("empId", emp.getEmpId())
					.executeUpdate() > 0;
	}

	@Override
	public Employee selectById(Integer id) {
		Query<Employee> query =  getSession().createQuery("FROM Employee where empId = :empId", Employee.class);
		query.setParameter("empId", id);
		Employee emp = query.uniqueResult();
		return emp;
	}

	@Override
	public List<Employee> selectAll() {
		Query<Employee> query =  getSession().createQuery("FROM Employee ", Employee.class);
		List<Employee> list = query.list();
		return list;
	}

	@Override
	public Employee selectByUsername(String username) {
		Query<Employee> query =  getSession().createQuery("FROM Employee where username = :username", Employee.class);
		query.setParameter("username", username);
		Employee emp = query.uniqueResult();
		return emp;
	}

	@Override
	public Employee selectByEmail(String email) {
		Query<Employee> query =  getSession().createQuery("FROM Employee where email = :email", Employee.class);
		query.setParameter("email", email);
		Employee emp = query.uniqueResult();
		return emp;
	}

	@Override
	public Employee selectForLogin(String username, String password) {
		Query<Employee> query =  getSession().createQuery("FROM Employee where username = :username and password = :password", Employee.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		Employee emp = query.uniqueResult();
		return emp;
	}

	@Override
	public boolean updatePassById(Employee emp) {
		String hql = "UPDATE Employee SET password = :password where empId = :empId";
		Query<?> query = getSession().createQuery(hql.toString());
		return query
				.setParameter("password", emp.getNewPassword())
				.setParameter("empId", emp.getEmpId())
				.executeUpdate() > 0;
	}

}
