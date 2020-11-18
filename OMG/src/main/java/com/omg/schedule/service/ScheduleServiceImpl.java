package com.omg.schedule.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.cmn.Criteria;
import com.omg.schedule.dao.ScheduleDao;
import com.omg.schedule.domain.ScheduleVO;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {
    final Logger LOG = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    
    @Autowired
    private ScheduleDao scheduleDao;

    public void setScheduleDao(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    public ScheduleServiceImpl() {}
    
    @Override
    public int doInsert(ScheduleVO schedule) {
	return scheduleDao.doInsert(schedule);
    }

    @Override
    public int doDelete(ScheduleVO schedule) {
	return scheduleDao.doDelete(schedule);
    }

    @Override
    public int doUpdate(ScheduleVO schedule) {
	return scheduleDao.doUpdate(schedule);
    }

    @Override
    public ScheduleVO doSelectOne(ScheduleVO schedule) {
	return scheduleDao.doSelectOne(schedule);
    }

    @Override
    public List<ScheduleVO> doSelectList(Criteria cri) {
	return scheduleDao.doSelectList(cri);
    }

	@Override
	public int getTotalCount(Criteria cri) {
		return scheduleDao.getTotalCount(cri);
	}

	@Override
	public List<ScheduleVO> toDoList(Criteria cri) {
		return scheduleDao.toDoList(cri);
	}

}
