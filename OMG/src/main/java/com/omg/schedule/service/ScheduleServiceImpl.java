package com.omg.schedule.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.schedule.dao.ScheduleDao;
import com.omg.schedule.domain.ScheduleVO;

@Service("scheduleServiceImpl")
public class ScheduleServiceImpl implements ScheduleService {
    final Logger LOG = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    
    @Autowired
    private ScheduleDao scheduleDao;

    public void setScheduleDao(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    public ScheduleServiceImpl() {}
    
    /**
     * 
     */
    
    /**
     * 종일체크 구분
     * time_status: 0(설정한 기간), 1(00:00 ~ 24:00)
     */

    @Override
    public int doInsert(ScheduleVO scheduleVO) {
	return scheduleDao.doInsert(scheduleVO);
    }

    @Override
    public int doDelete(ScheduleVO scheduleVO) {
	return scheduleDao.doDelete(scheduleVO);
    }

    @Override
    public int doUpdate(ScheduleVO scheduleVO) {
	return scheduleDao.doUpdate(scheduleVO);
    }

    @Override
    public ScheduleVO doSelectOne(ScheduleVO scheduleVO) {
	return scheduleDao.doSelectOne(scheduleVO);
    }

    @Override
    public List<ScheduleVO> doSelectList(ScheduleVO scheduleVO) {
	return scheduleDao.doSelectList(scheduleVO);
    }

}
