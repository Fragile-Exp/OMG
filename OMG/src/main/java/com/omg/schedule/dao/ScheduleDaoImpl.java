package com.omg.schedule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.omg.schedule.domain.ScheduleVO;

@Repository("scheduleDao")
public class ScheduleDaoImpl implements ScheduleDao{
    
    final static Logger LOG = LoggerFactory.getLogger(ScheduleDaoImpl.class);
    
    public ScheduleDaoImpl() {}
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    DataSource dataSource; 
    
    RowMapper rowMapper = new RowMapper<ScheduleVO>() {
	@Override
	public ScheduleVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	    ScheduleVO outVO = new ScheduleVO();

	    outVO.setScheduleNo(rs.getInt("schedule_no"));
	    outVO.setDeptNo(rs.getInt("dept_no"));
	    outVO.setEmployeeId(rs.getString("employee_id"));
	    outVO.setCategoryId(rs.getInt("category_id"));
	    outVO.setTimeStatus(rs.getInt("time_status"));
	    outVO.setTitle(rs.getString("title"));
	    outVO.setContent(rs.getString("content"));
	    outVO.setStartDt(rs.getString("start_dt"));
	    outVO.setEndDt(rs.getString("end_dt"));
	    
	    return outVO;
	}
    };
    
    /**
     * 등록
     * @param scheduleVO
     * @return flag
     * @author 박정민
     * @Date 2020-10-28
     */
    @Override
    public int doInsert(ScheduleVO scheduleVO) {
	int flag = 0;
	Object[] args = {
		scheduleVO.getScheduleNo(),
		scheduleVO.getDeptNo(),
		scheduleVO.getEmployeeId(),
		scheduleVO.getCategoryId(),
		scheduleVO.getTimeStatus(),
		scheduleVO.getTitle(),
		scheduleVO.getContent(),
		scheduleVO.getStartDt(),
		scheduleVO.getEndDt()
	};
	
	StringBuilder sb = new StringBuilder();
	
	sb.append(" INSERT INTO schedule (    \n");
	sb.append("     schedule_no,          \n");
	sb.append("     dept_no,              \n");
	sb.append("     employee_id,          \n");
	sb.append("     category_id,          \n");
	sb.append("     time_status,          \n");
	sb.append("     title,                \n");
	sb.append("     content,              \n");
	sb.append("     start_dt,             \n");
	sb.append("     end_dt                \n");
	sb.append(" ) VALUES (                \n");
	sb.append("     SCHEDULE_SEQ.nextval, \n");
	sb.append("     ?,                    \n");
	sb.append("     ?,                    \n");
	sb.append("     ?,                    \n");
	sb.append("     ?,                    \n");
	sb.append("     ?,                    \n");
	sb.append("     ?,                    \n");
	sb.append("     ?,                    \n");
	sb.append("     ?                     \n");
	sb.append(" )                         \n");
	
	LOG.debug("==============================");
	LOG.debug("= Parameter: " + scheduleVO);
	LOG.debug("==============================");
	
	flag = jdbcTemplate.update(sb.toString(), args);
	LOG.debug("flag: " + flag);
	
	return flag;
    }
    
    /**
     * 삭제
     * @param scheduleVO
     * @return flag
     * @author 박정민
     * @Date 2020-10-28
     */
    @Override
    public int doDelete(ScheduleVO scheduleVO) {
	int flag = 0;
	
	StringBuilder sb = new StringBuilder();
	
	sb.append(" DELETE FROM schedule  \n");
	sb.append(" WHERE schedule_no = ? \n");
	
	LOG.debug("==============================");
	LOG.debug("= Parameter: " + scheduleVO);
	LOG.debug("==============================");
	
	Object[] args = { scheduleVO.getScheduleNo() };
	
	flag = jdbcTemplate.update(sb.toString(), args);
	LOG.debug("flag: " + flag);
	
	return flag;
    }

    /**
     * 수정
     * @param scheduleVO
     * @return flag
     * @author 박정민
     * @Date 2020-10-28
     */
    @Override
    public int doUpdate(ScheduleVO scheduleVO) {
	int flag = 0;
	
	StringBuilder sb = new StringBuilder();
	
	sb.append(" UPDATE schedule       \n");
	sb.append(" SET category_id = ?   \n");
	sb.append("     time_status = ?   \n");
	sb.append("     title = ?         \n");
	sb.append("     content = ?       \n");
	sb.append("     start_dt = ?      \n");
	sb.append("     end_dt = ?        \n");
	sb.append(" WHERE schedule_no = ? \n");
	
	LOG.debug("==============================");
	LOG.debug("= Parameter: " + scheduleVO);
	LOG.debug("==============================");
	
	Object[] args = {
		scheduleVO.getCategoryId(),
		scheduleVO.getTimeStatus(),
		scheduleVO.getTitle(),
		scheduleVO.getContent(),
		scheduleVO.getStartDt(),
		scheduleVO.getEndDt(),
		scheduleVO.getScheduleNo()
	};
	
	flag = jdbcTemplate.update(sb.toString(), args);
	LOG.debug("flag: " + flag);	
	
	return flag;
    }
  
    /**
     * 단일 일정조회
     * @param scheduleVO
     * @return outVO
     * @author 박정민
     * @Date 2020-10-28
     */
    @Override
    public ScheduleVO doSelectOne(ScheduleVO scheduleVO) {
	ScheduleVO outVO = null;
	
	StringBuilder sb = new StringBuilder();
	
	sb.append(" SELECT schedule_no,   \n");
	sb.append(" 	   dept_no,       \n");
	sb.append(" 	   employee_id,   \n");
	sb.append(" 	   category_id,   \n");
	sb.append(" 	   time_status,   \n");
	sb.append(" 	   title,         \n");
	sb.append(" 	   content,       \n");
	sb.append(" 	   TO_CHAR(start_dt, 'YYYY-MM-DD HH24:MI') AS start_dt \n");
	sb.append(" 	   TO_CHAR(end_dt, 'YYYY-MM-DD HH24:MI') AS end_dt     \n");
	sb.append(" FROM schedule         \n");
	sb.append(" WHERE schedule_no = ? \n");
	
	LOG.debug("==============================");
	LOG.debug("= Parameter: " + scheduleVO);
	LOG.debug("==============================");
	
	Object[] args = { scheduleVO.getScheduleNo() };
	outVO = (ScheduleVO) jdbcTemplate.queryForObject(sb.toString(), args, rowMapper);
	
	LOG.debug("==============================");
	LOG.debug("= outVO: " + outVO);
	LOG.debug("==============================");
	
	return outVO;
    }
 
    /**
     * 일정조회
     * dept_no가 0이면 전체조회 아니면 부서별 조회
     * @param scheduleVO
     * @return outVO
     * @author 박정민
     * @Date 2020-10-28
     */
    @Override
    public List<ScheduleVO> doSelectList(ScheduleVO scheduleVO) {
	List<ScheduleVO> list = null;
	
	StringBuilder sbSearch = new StringBuilder();
	
	StringBuilder sb = new StringBuilder();
	
	sb.append(" SELECT schedule_no,  \n");
	sb.append(" 	   dept_no,      \n");
	sb.append(" 	   employee_id,  \n");
	sb.append(" 	   category_id,  \n");
	sb.append(" 	   time_status,  \n");
	sb.append(" 	   title,        \n");
	sb.append(" 	   content,      \n");
	sb.append(" 	   TO_CHAR(start_dt, 'YYYY-MM-DD HH24:MI') AS start_dt,\n");
	sb.append(" 	   TO_CHAR(end_dt, 'YYYY-MM-DD HH24:MI') AS end_dt     \n");
	sb.append(" FROM schedule        \n");
	if(scheduleVO.getDeptNo() != 0) {
	    sb.append(" WHERE dept_no = ?	 \n");
	}
	sb.append(" ORDER BY schedule_no \n");
	
	LOG.debug("==============================");
	LOG.debug("= Parameter: " + scheduleVO);
	LOG.debug("==============================");
	
	list = jdbcTemplate.query(sb.toString(), new Object[] {scheduleVO.getDeptNo()}, rowMapper);
	
	LOG.debug("==============================");
	for(ScheduleVO vo : list) {
	    LOG.debug("= VO: " + vo);
	}
	LOG.debug("==============================");
	
	return list;
    }
}
