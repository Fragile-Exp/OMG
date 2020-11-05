package com.omg.commuting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.cmn.DTO;
import com.omg.cmn.Search;
import com.omg.commuting.domain.Commuting;
import com.omg.comutting.dao.CommutingDao;

@Service("commutingService")
public class CommutingServiceImpl implements CommutingService {
	
	final static String MIN_TIME_FOR_ATTEND = "09";
	final static String QUITTING_TIME = "18";
	
	@Autowired
	CommutingDao commutingDao;
	
	public CommutingServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int doInit() {
		return commutingDao.doInit();
	}

	@Override
	public int doInsert(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdateAttendTime(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdateLeaveTime(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DTO doSelectOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Commuting> doSelectList(Search search) {
		// TODO Auto-generated method stub
		return null;
	}

}
