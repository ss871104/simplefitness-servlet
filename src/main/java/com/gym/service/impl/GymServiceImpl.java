package com.gym.service.impl;

import java.util.List;

import com.gym.dao.impl.GymDaoImpl;
import com.gym.dao.intf.GymDaoIntf;
import com.gym.service.intf.GymServiceIntf;
import com.gym.vo.Gym;
import com.gym.vo.GymPic;

import com.common.adapter.Base64Adapter;

public class GymServiceImpl implements GymServiceIntf{
	private GymDaoIntf dao;
	private Base64Adapter base64;
	
	public GymServiceImpl() {
		dao = new GymDaoImpl();
		base64 = new Base64Adapter();
	}

	@Override
	public Gym add(Gym gym) {
		try {
			if (gym.getGymName().isEmpty()) {
				gym.setMessage("健身房名稱未輸入");
				gym.setSuccessful(false);
				return gym;
			}
			if (gym.getAddress().isEmpty()) {
				gym.setMessage("健身房地址未輸入");
				gym.setSuccessful(false);
				return gym;
			}
			if (gym.getPhone().isEmpty()) {
				gym.setMessage("電話號碼未輸入");
				gym.setSuccessful(false);
				return gym;
			}
			if (gym.getOpenDate() == null) {
				gym.setMessage("開幕日未輸入");
				gym.setSuccessful(false);
				return gym;
			}
			if (gym.getOpenTime() == null) {
				gym.setMessage("開門時間未輸入");
				gym.setSuccessful(false);
				return gym;
			}
			if (gym.getCloseTime() == null) {
				gym.setMessage("關門時間未輸入");
				gym.setSuccessful(false);
				return gym;
			}
			if (gym.getMaxPeople() == null) {
				gym.setMessage("人數上限未輸入");
				gym.setSuccessful(false);
				return gym;
			}
			if (gym.getIntro().isEmpty()) {
				gym.setMessage("健身房介紹未輸入");
				gym.setSuccessful(false);
				return gym;
			}
//			beginTransaction();
			if (dao.insert(gym) == false) {
				gym.setMessage("新增發生錯誤!");
				gym.setSuccessful(false);
//				rollback();
				return gym;
			}
//			commit();
			gym.setMessage("新增成功");
			gym.setSuccessful(true);
			return gym;
		} catch (Exception e) {
			e.printStackTrace();
//			rollback();
			return null;
		}
	}

	@Override
	public Gym edit(Gym gym) {
		try {
			if (gym.getGymName().isEmpty()) {
				gym.setMessage("健身房名稱未輸入");
				gym.setSuccessful(false);
				return gym;
			}
			if (gym.getAddress().isEmpty()) {
				gym.setMessage("健身房地址未輸入");
				gym.setSuccessful(false);
				return gym;
			}
			if (gym.getPhone().isEmpty()) {
				gym.setMessage("電話號碼未輸入");
				gym.setSuccessful(false);
				return gym;
			}
			if (gym.getOpenDate() == null) {
				gym.setMessage("開幕日未輸入");
				gym.setSuccessful(false);
				return gym;
			}
			if (gym.getOpenTime() == null) {
				gym.setMessage("開門時間未輸入");
				gym.setSuccessful(false);
				return gym;
			}
			if (gym.getCloseTime() == null) {
				gym.setMessage("關門時間未輸入");
				gym.setSuccessful(false);
				return gym;
			}
			if (gym.getMaxPeople() == null) {
				gym.setMessage("人數上限未輸入");
				gym.setSuccessful(false);
				return gym;
			}
			if (gym.getIntro().isEmpty()) {
				gym.setMessage("健身房介紹未輸入");
				gym.setSuccessful(false);
				return gym;
			}
			if (dao.update(gym) == false) {
				gym.setMessage("新增發生錯誤!");
				gym.setSuccessful(false);
				return gym;
			}
			gym.setMessage("編輯成功");
			gym.setSuccessful(true);
			return gym;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Gym findById(Gym gym) {
		try {
			return dao.selectById(gym.getGymId());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Gym> findAll() {
		try {
			return (List<Gym>) dao.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<GymPic> findPicById(Gym gym) {
		try {
			List<GymPic> list = dao.selectPicById(gym.getGymId());
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setPicBase64(base64.Encoder(list.get(i).getPic()));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Gym> joinFindById(Gym gym) {
		try {
			return (List<Gym>) dao.joinSelectByGymId(gym.getGymId());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
