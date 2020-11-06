package com.omg.schedule.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    /**
     * 일정추가
     */
    @Override
    public int doInsert(ScheduleVO schedule) {
	return scheduleDao.doInsert(schedule);
    }

    /**
     * 일정삭제
     */
    @Override
    public int doDelete(ScheduleVO schedule) {
	return scheduleDao.doDelete(schedule);
    }

    /**
     * 일정수정
     */
    @Override
    public int doUpdate(ScheduleVO schedule) {
	return scheduleDao.doUpdate(schedule);
    }

    /**
     * 일정선택
     */
    @Override
    public ScheduleVO doSelectOne(ScheduleVO schedule) {
	return scheduleDao.doSelectOne(schedule);
    }

    /**
     * 일정 리스트화
     */
    @Override
    public List<ScheduleVO> doSelectList(ScheduleVO schedule) {
	return scheduleDao.doSelectList(schedule);
    }

}
