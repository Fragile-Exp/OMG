package com.omg.schedule.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omg.schedule.domain.ScheduleCatVO;

@Repository("scheduleCatDao")
public class ScheduleCatDaoImpl implements ScheduleCatDao {
    
    private final Logger LOG = LoggerFactory.getLogger(ScheduleCatDaoImpl.class);
    private final String NAMESPACE = "mappers.schedule.scheduleCat";

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    
    public ScheduleCatDaoImpl() {}
    
    /**
     * 등록
     * @param scheduleCat
     * @return flag
     * @author 박정민
     */
    @Override
    public int doInsert(ScheduleCatVO scheduleCat) {
	LOG.debug("doInsert.....");
	
	String statement = NAMESPACE + ".doInsert";
	
	int flag = sqlSessionTemplate.insert(statement, scheduleCat);
	LOG.debug("[doInsert]flag: " + flag);
	
	return flag;
    }
    
    /**
     * 삭제
     * @param scheduleCat
     * @return flag
     * @author 박정민
     */
    @Override
    public int doDelete(ScheduleCatVO scheduleCat) {
	LOG.debug("doDelete.....");
	
	String statement = NAMESPACE + ".doDelete";
	
	int flag = sqlSessionTemplate.delete(statement, scheduleCat);
	LOG.debug("[doDelete]flag: " + flag);
	
	return flag;
    }
    
    /**
     * 수정
     * @param scheduleCat
     * @return flag
     * @author 박정민
     */
    @Override
    public int doUpdate(ScheduleCatVO scheduleCat) {
	LOG.debug("doUpdate.....");
	
	String statement = NAMESPACE + ".doUpdate";
	
	int flag = sqlSessionTemplate.delete(statement, scheduleCat);
	LOG.debug("[doUpdate]flag: " + flag);
	
	return flag;
    }
    
    /**
     * 단건조회
     * @param scheduleCat
     * @return outVO
     * @author 박정민
     */
    @Override
    public ScheduleCatVO doSelectOne(ScheduleCatVO scheduleCat) {
	LOG.debug("doSelectOne.....");
	
	String statement = NAMESPACE + ".doSelectOne";
	
	ScheduleCatVO outVO = sqlSessionTemplate.selectOne(statement, scheduleCat);
	
	LOG.debug("=========================");
	LOG.debug("= [one]outVO: " + outVO);
	LOG.debug("=========================");

	return outVO;
    }

    /**
     * 다건조회
     * @return list
     * @author 박정민
     */
    @Override
    public List<ScheduleCatVO> doSelectList() {
	LOG.debug("doSelectList.....");
	
	String statement = NAMESPACE + ".doSelectList";
	
	List<ScheduleCatVO> list = sqlSessionTemplate.selectList(statement);
	
	LOG.debug("=========================");
	list.forEach(outVO -> LOG.debug("= [list]outVO: " + outVO));
	LOG.debug("=========================");
	
	return list;
    }
    
}
