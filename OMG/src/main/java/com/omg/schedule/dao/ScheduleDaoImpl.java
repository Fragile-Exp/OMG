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
	private final String NAMESPACE = "com.omg.mapper.schedule";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public ScheduleDaoImpl() {
	}

	/**
	 * 생성
	 * 
	 * @param schedule
	 * @return flag
	 * @author 박정민
	 */
	@Override
	public int doInsert(ScheduleVO schedule) {
		LOG.debug("doInsert.....");
		LOG.debug("[Insert]Param: " + schedule);

		String statement = NAMESPACE + ".insert";

		// 날짜 문자열 T 치환
		schedule.setStart_dt(schedule.getStart_dt().replace("T", " "));
		schedule.setEnd_dt(schedule.getEnd_dt().replace("T", " "));

		int flag = sqlSessionTemplate.insert(statement, schedule);
		LOG.debug("[doInsert]flag: " + flag);

		return flag;
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
		LOG.debug("doDelete.....");
		LOG.debug("[Delete]Param: " + schedule);

		String statement = NAMESPACE + ".delete";

		int flag = sqlSessionTemplate.delete(statement, schedule);
		LOG.debug("[doDelete]flag: " + flag);

		return flag;
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
		LOG.debug("doUpdate.....");
		LOG.debug("[Update]Param: " + schedule);

		String statement = NAMESPACE + ".update";

		// 날짜 문자열 T 치환
		schedule.setStart_dt(schedule.getStart_dt().replace("T", " "));
		schedule.setEnd_dt(schedule.getEnd_dt().replace("T", " "));

		int flag = sqlSessionTemplate.update(statement, schedule);
		LOG.debug("[doUpdate]flag: " + flag);

		return flag;
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
		LOG.debug("doSelectOne.....");
		LOG.debug("[Read]Param: " + schedule);

		String statement = NAMESPACE + ".read";

		ScheduleVO outVO = sqlSessionTemplate.selectOne(statement, schedule);

		LOG.debug("=========================");
		LOG.debug("= [one]outVO: " + outVO);
		LOG.debug("=========================");

		return outVO;
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
		LOG.debug("doSelectList.....");
		LOG.debug("[List]Param: " + cri);

		String statement = NAMESPACE + ".getList";

		List<ScheduleVO> list = sqlSessionTemplate.selectList(statement, cri);
		LOG.debug("cri: " + cri);

		LOG.debug("=========================");
		list.forEach(outVO -> LOG.debug("= [list]outVO: " + outVO));
		LOG.debug("=========================");

		return list;
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
		LOG.debug("getTotalCount.....");
		LOG.debug("[count]Param: " + cri);
		
		String statement = NAMESPACE + ".getTotalCount";
		int count = sqlSessionTemplate.selectOne(statement, cri);
		
		LOG.debug("COUNT: " + count);

		return count;
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
		LOG.debug("doSelectList.....");
		LOG.debug("[List]Param: " + cri);

		String statement = NAMESPACE + ".toDoList";
		
		List<ScheduleVO> list = sqlSessionTemplate.selectList(statement, cri);
		LOG.debug("cri: " + cri);
		
		LOG.debug("=========================");
		list.forEach(outVO -> LOG.debug("= [list]outVO: " + outVO));
		LOG.debug("=========================");
		
		return list;
	}

}
