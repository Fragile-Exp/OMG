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
	
	sb.append(" INSERT INTO schedule (                \n");   
	sb.append("     schedule_no,                      \n");   
	sb.append("     dept_no,                          \n");   
	sb.append("     employee_id,                      \n");   
	sb.append("     category_id,                      \n");   
	sb.append("     time_status,                      \n");   
	sb.append("     title,                            \n");   
	sb.append("     content,                          \n");   
	sb.append("     start_dt,                         \n");   
	sb.append("     end_dt                            \n");   
	sb.append(" ) VALUES (                            \n");   
	sb.append("     SCHEDULE_SEQ.nextval,             \n");   
	sb.append("     ?,                                \n"); 
	sb.append("     ?,                                \n"); 
	sb.append("     ?,                                \n"); 
	sb.append("     ?,                                \n"); 
	sb.append("     ?,                                \n"); 
	sb.append("     ?,                                \n"); 
	sb.append("     TO_DATE(?, 'yyyy-MM-dd HH24:mi'), \n"); 
	sb.append("     TO_DATE(?, 'yyyy-MM-dd HH24:mi')  \n"); 
	sb.append(" )                                     \n");
	
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
    public int doDelete(int scheduleNo) {
	int flag = 0;
	
	StringBuilder sb = new StringBuilder();
	
	sb.append(" DELETE FROM schedule  \n");
	sb.append(" WHERE schedule_no = ? \n");
	
	LOG.debug("==============================");
	LOG.debug("= Parameter: " + scheduleNo);
	LOG.debug("==============================");
	
	flag = jdbcTemplate.update(sb.toString(), scheduleNo);
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
	sb.append(" SET category_id = ?,  \n");
	sb.append("     time_status = ?,  \n");
	sb.append("     title = ?,        \n");
	sb.append("     content = ?,      \n");
	sb.append("     start_dt = TO_DATE(?, 'yyyy-MM-dd HH24:mi'), \n");
	sb.append("     end_dt = TO_DATE(?, 'yyyy-MM-dd HH24:mi')    \n");
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
    public ScheduleVO doSelectOne(int scheduleNo) {
	ScheduleVO outVO = null;
	
	StringBuilder sb = new StringBuilder();
	
	sb.append(" SELECT schedule_no,   \n");
	sb.append(" 	   dept_no,       \n");
	sb.append(" 	   employee_id,   \n");
	sb.append(" 	   category_id,   \n");
	sb.append(" 	   time_status,   \n");
	sb.append(" 	   title,         \n");
	sb.append(" 	   content,       \n");
	sb.append(" 	   TO_CHAR(start_dt, 'yyyy-MM-dd HH24:mi') AS start_dt, \n");
	sb.append(" 	   TO_CHAR(end_dt, 'yyyy-MM-dd HH24:mi') AS end_dt      \n");
	sb.append(" FROM schedule         \n");
	sb.append(" WHERE schedule_no = ? \n");
	
	LOG.debug("==============================");
	LOG.debug("= Parameter: " + scheduleNo);
	LOG.debug("==============================");
	
	Object[] args = {scheduleNo};

	outVO = (ScheduleVO) jdbcTemplate.queryForObject(sb.toString(), args, rowMapper);
	
	LOG.debug("==============================");
	LOG.debug("= outVO: " + outVO);
	LOG.debug("==============================");
	
	return outVO;
    }
 
    /**
     * 일정조회
     * deptNo가 0이면 전체조회 아니면 부서별 조회
     * @param deptNo 부서번호
     * @return outVO
     * @author 박정민
     * @Date 2020-10-28
     */
    @Override
    public List<ScheduleVO> doSelectList(int deptNo) {
	List<ScheduleVO> list = null;

	StringBuilder sb = new StringBuilder();
	
	sb.append(" SELECT schedule_no,  \n");
	sb.append(" 	   dept_no,      \n");
	sb.append(" 	   employee_id,  \n");
	sb.append(" 	   category_id,  \n");
	sb.append(" 	   time_status,  \n");
	sb.append(" 	   title,        \n");
	sb.append(" 	   content,      \n");
	sb.append(" 	   TO_CHAR(start_dt, 'yyyy-MM-dd HH24:MI') AS start_dt,\n");
	sb.append(" 	   TO_CHAR(end_dt, 'yyyy-MM-dd HH24:MI') AS end_dt     \n");
	sb.append(" FROM schedule        \n");
	if(deptNo != 0) {
	    sb.append(" WHERE dept_no = ? \n");
	}
	sb.append(" ORDER BY schedule_no \n");
	
	LOG.debug("==============================");
	LOG.debug("= Parameter: " + deptNo);
	LOG.debug("==============================");
	
	Object[] args = {deptNo};
	
	//0이면 전체조회 아니면 부서별조회
	if(deptNo == 0) {
	    list = jdbcTemplate.query(sb.toString(), rowMapper);
	} else {
	    list = jdbcTemplate.query(sb.toString(), args, rowMapper);
	}

	LOG.debug("==============================");
	for(ScheduleVO vo : list) {
	    LOG.debug("= VO: " + vo);
	}
	LOG.debug("==============================");
	
	return list;
    }
}
