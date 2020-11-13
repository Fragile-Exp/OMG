package com.omg.schedule.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omg.cmn.Criteria;
import com.omg.schedule.domain.ScheduleVO;

@Repository("scheduleDao")
public class ScheduleDaoImpl implements ScheduleDao {
    
    private final Logger LOG = LoggerFactory.getLogger(ScheduleDaoImpl.class);
    private final String NAMESPACE = "mappers.schedule.schedule";

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    
    public ScheduleDaoImpl() {}

    /**
     * 생성
     * @param schedule
     * @return flag
     * @author 박정민
     */
    @Override
    public int doInsert(ScheduleVO schedule) {
	LOG.debug("doInsert.....");
	
	String statement = NAMESPACE + ".doInsert";
	
	int flag = sqlSessionTemplate.insert(statement, schedule);
	LOG.debug("[doInsert]flag: " + flag);
	
	return flag;
    }

    /**
     * 삭제
     * @param schedule
     * @return flag
     * @author 박정민
     */
    @Override
    public int doDelete(ScheduleVO schedule) {
	LOG.debug("doDelete.....");
	
	String statement = NAMESPACE + ".doDelete";
	
	int flag = sqlSessionTemplate.delete(statement, schedule);
	LOG.debug("[doDelete]flag: " + flag);
	
	return flag;
    }

    /**
     * 수정
     * @param schedule
     * @return flag
     * @author 박정민 
     */
    @Override
    public int doUpdate(ScheduleVO schedule) {
	LOG.debug("doUpdate.....");
	
	String statement = NAMESPACE + ".doUpdate";
	
	int flag = sqlSessionTemplate.update(statement, schedule);
	LOG.debug("[doUpdate]flag: " + flag);

	return flag;
    }

    /**
     * 단건조회
     * @param schedule
     * @return outVO
     * @author 박정민
     */
    @Override
    public ScheduleVO doSelectOne(ScheduleVO schedule) {
	LOG.debug("doSelectOne.....");
	
	String statement = NAMESPACE + ".doSelectOne";
	
	ScheduleVO outVO = sqlSessionTemplate.selectOne(statement, schedule);
	
	LOG.debug("=========================");
	LOG.debug("= [one]outVO: " + outVO);
	LOG.debug("=========================");
	
	return outVO;
    }

    /**
     * 다건조회
     * @param schedule
     * @return list
     * @author 박정민
     */
    @Override
    public List<ScheduleVO> doSelectList(Criteria cri) {
	LOG.debug("doSelectList.....");
	
	String statement = NAMESPACE + ".doSelectList";
	
	List<ScheduleVO> list = sqlSessionTemplate.selectList(statement, cri);
	LOG.debug("cri: " + cri);
	
	LOG.debug("=========================");
	list.forEach(outVO -> LOG.debug("= [list]outVO: " + outVO));
	LOG.debug("=========================");
	
	return list;
    }

    @Override
    public int getTotalCount(Criteria cri) {
	LOG.debug("getTotalCount.....");
	
	String statement = NAMESPACE + ".getTotalCount";
	int total = sqlSessionTemplate.selectOne(statement, cri);
	LOG.debug("total: " + total);
	
	return total;
    }

    

}
