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
    
    /**
	 * 생성
	 * 
	 * @param schedule
	 * @return flag
	 * @author 박정민
	 */
    @Override
    public int doInsert(ScheduleVO schedule) {
	return scheduleDao.doInsert(schedule);
    }

    /**
	 * 삭제
	 * 
	 * @param schedule
	 * @return flag
	 * @author 박정민
	 */
    @Override
    public int doDelete(ScheduleVO schedule) {
	return scheduleDao.doDelete(schedule);
    }

    /**
	 * 수정
	 * 
	 * @param schedule
	 * @return flag
	 * @author 박정민
	 */
    @Override
    public int doUpdate(ScheduleVO schedule) {
	return scheduleDao.doUpdate(schedule);
    }

    /**
	 * 단건조회
	 * 
	 * @param schedule
	 * @return outVO
	 * @author 박정민
	 */
    @Override
    public ScheduleVO doSelectOne(ScheduleVO schedule) {
	return scheduleDao.doSelectOne(schedule);
    }

    /**
	 * 다건조회
	 * 
	 * @param schedule
	 * @return list
	 * @author 박정민
	 */
    @Override
    public List<ScheduleVO> doSelectList(Criteria cri) {
	return scheduleDao.doSelectList(cri);
    }

    /**
	 * 게시판별 게시글 수 조회
	 * 
	 * @param cri
	 * @return flag
	 * @author 박정민
	 */
	@Override
	public int getTotalCount(Criteria cri) {
		return scheduleDao.getTotalCount(cri);
	}

	/**
	 * toDoList 리스트 조회
	 * 
	 * @param cri
	 * @return list
	 * @author 박정민
	 */
	@Override
	public List<ScheduleVO> toDoList(Criteria cri) {
		return scheduleDao.toDoList(cri);
	}

}
