package com.omg.schedule.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.schedule.dao.ScheduleCatDao;
import com.omg.schedule.domain.ScheduleCatVO;

@Service("scheduleCatService")
public class ScheduleCatServiceImpl implements ScheduleCatService {
    final Logger LOG = LoggerFactory.getLogger(ScheduleCatServiceImpl.class);
    
    @Autowired
    private ScheduleCatDao scheduleCatDao;
    
    public void setScheduleCatDao(ScheduleCatDao scheduleCatDao) {
	this.scheduleCatDao = scheduleCatDao;
    }
    
    public ScheduleCatServiceImpl() {}

    /**
     * 카테고리 추가
     */
    @Override
    public int doInsert(ScheduleCatVO scheduleCat) {
	return scheduleCatDao.doInsert(scheduleCat);
    }

    /**
     * 카테고리 삭제
     */
    @Override
    public int doDelete(ScheduleCatVO scheduleCat) {
	return scheduleCatDao.doDelete(scheduleCat);
    }

    /**
     * 카테고리 수정
     */
    @Override
    public int doUpdate(ScheduleCatVO scheduleCat) {
	return scheduleCatDao.doUpdate(scheduleCat);
    }

    /**
     * 카테고리 선택
     */
    @Override
    public ScheduleCatVO doSelectOne(ScheduleCatVO scheduleCat) {
	return scheduleCatDao.doSelectOne(scheduleCat);
    }

    /**
     * 카테고리 리스트화
     */
    @Override
    public List<ScheduleCatVO> doSelectList() {
	return scheduleCatDao.doSelectList();
    }

}
