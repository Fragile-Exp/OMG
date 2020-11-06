package com.omg.commuting.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.cmn.DTO;
import com.omg.cmn.Search;
import com.omg.cmn.StringUtil;
import com.omg.commuting.domain.Commuting;
import com.omg.commuting.domain.PresentState;
import com.omg.commuting.domain.State;
import com.omg.comutting.dao.CommutingDao;

@Service("commutingService")
public class CommutingServiceImpl implements CommutingService {
	

	final Logger LOG = LoggerFactory.getLogger(CommutingServiceImpl.class);
	
	final static int MIN_HOUR_FOR_ATTEND = 9;
	final static int QUITTING_HOUR = 18;
	final static int CUTTENT_HOUR = Integer.valueOf((StringUtil.formatDate("HH")));
	
	@Autowired
	CommutingDao commutingDao;
	
	public CommutingServiceImpl() {}
	
	@Override
	public int doInit() {
		LOG.debug("-------------------------------");
		LOG.debug("-doInit()");
		LOG.debug("-------------------------------");
		return commutingDao.doInit();
	}

	@Override
	public int doInsert(DTO dto) {
		LOG.debug("-------------------------------");
		LOG.debug("-doInsert()");
		LOG.debug("-------------------------------");
		return commutingDao.doInsert(dto);
	}

	@Override
	public int doDelete(DTO dto) {
		LOG.debug("-------------------------------");
		LOG.debug("-doDelete()");
		LOG.debug("-------------------------------");
		return commutingDao.doDelete(dto);
	}

	@Override
	public int doUpdateAttendTime(DTO dto) {
		LOG.debug("-------------------------------");
		LOG.debug("-doUpdateAttendTime()");
		Commuting inVO = (Commuting) dto;
		
		LOG.debug("-CURRENT_TIME : " + CUTTENT_HOUR);
		
		if(MIN_HOUR_FOR_ATTEND < CUTTENT_HOUR
				&&CUTTENT_HOUR < QUITTING_HOUR ) {
			
			inVO.setPresentState(PresentState.근무중);
			inVO.setState(State.지각);
			
		}else {
			inVO.setPresentState(PresentState.근무중);
			inVO.setState(State.정상);
		}
		
		inVO.setAttendTime(StringUtil.formatDate("yyyyMMdd 085000"));
		inVO.setLeaveTime("");
		
		LOG.debug("-inVO :\n"+inVO);
		
		int verify = commutingDao.doUpdate(inVO);
		
		LOG.debug("-verify :\n"+verify);
		LOG.debug("-------------------------------");
		return verify;
	}

	@Override
	public int doUpdateLeaveTime(DTO dto) {
		LOG.debug("-------------------------------");
		LOG.debug("-doUpdateLeaveTime()");
		
		Commuting inVO = (Commuting) dto;
		
		LOG.debug("-CURRENT_TIME : " + CUTTENT_HOUR);
		
		if(MIN_HOUR_FOR_ATTEND < CUTTENT_HOUR
				&&CUTTENT_HOUR < QUITTING_HOUR ) {
			
			inVO.setPresentState(PresentState.퇴근);
			
			if(inVO.getState() == State.지각) {
				inVO.setState(State.지각조퇴);
			} else {
				inVO.setState(State.조퇴);
			}
			
		}else {
			inVO.setPresentState(PresentState.퇴근);
			inVO.setState(State.정상);
		}
		
		inVO.setLeaveTime(StringUtil.formatDate("yyyyMMdd 155000"));
		
		LOG.debug("-inVO :\n"+inVO);
		
		int verify = commutingDao.doUpdate(inVO);
		verify = commutingDao.doUpdateWorkTime(inVO);
		
		LOG.debug("-verify :\n"+verify);
		LOG.debug("-------------------------------");
		return verify;
	}

	@Override
	public DTO doSelectOne(DTO dto) {
		LOG.debug("-------------------------------");
		LOG.debug("-doSelectOne()");
		LOG.debug("-------------------------------");
		return commutingDao.doSelectOne(dto);
	}

	@Override
	public List<Commuting> doSelectList(Search search) {
		LOG.debug("-------------------------------");
		LOG.debug("-doSelectList()");
		LOG.debug("-------------------------------");
		return commutingDao.doSelectList(search);
	}

	
}
