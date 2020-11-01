package com.omg.comutting.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.omg.cmn.DTO;
import com.omg.cmn.Search;

import oracle.net.ns.Communication;
@Repository("commutingDao")
public class CommutingDaoImpl implements CommutingDao{
	
	/** LOG */
	final static Logger LOG = LoggerFactory.getLogger(CommutingDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<Commuting> rowMapper = new RowMapper<Commuting>() {

		@Override
		public Commuting mapRow(ResultSet rs, int rowNum) throws SQLException {
			Commuting outData = new Commuting();
			outData.setSeq(rs.getString("seq"));
			outData.setEmployeeId(rs.getString("employee_id"));
			outData.setName(rs.getString("name"));
			outData.setDeptNo(rs.getString("dept_no"));
			outData.setAttendTime(rs.getString("attend_time"));
			outData.setLeaveTime(rs.getString("leave_time"));
			outData.setPresentState(PresentState.valueOf(rs.getInt("present_state")));
			outData.setState(State.valueOf(rs.getInt("state")));
			outData.setWorkTime(rs.getString("work_time"));
			outData.setRegDt(rs.getString("reg_dt"));
			return outData;
		}
	};
	
	public CommutingDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	

	
	@Override
	public int doDelete(DTO dto) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doDelete=");
		Commuting inVO = (Commuting) dto;
		int verify = 0;
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE FROM commuting   \n");
		sb.append(" WHERE                   \n");
		sb.append("     employee_id = ?     \n");
		
		LOG.debug("=delete.param=" + inVO);
		LOG.debug("=delete.sql=" + sb.toString());
		
		Object[] args = {inVO.getEmployeeId()};
		verify = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("====================================");
		return verify;
	}

	

	@Override
	public DTO doSelectToday(DTO dto) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doSelectOne=");
		Commuting inVO = (Commuting) dto;
		Commuting outVO = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT                                       \n");
		sb.append("    /*+INDEX_DESC(c PK_COMMUTING) */ seq,     \n");
		sb.append("     employee_id,                             \n");
		sb.append("     name,                                    \n");
		sb.append("     dept_no,                                 \n");
		sb.append("     attend_time,                             \n");
		sb.append("     leave_time,                              \n");
		sb.append("     present_state,                           \n");
		sb.append("     state,                                   \n");
		sb.append("     work_time,                               \n");
		sb.append("     reg_dt                                   \n");
		sb.append(" FROM                                         \n");
		sb.append("     commuting c                              \n");
		sb.append(" WHERE c.employee_id= ?                       \n");
		sb.append(" AND seq > '0'                                \n");
		sb.append(" AND rownum =1                                \n");
		
		LOG.debug("=selectone.param=" + inVO);
		LOG.debug("=selectone.sql=" + sb.toString());
		
		Object[] args = {inVO.getEmployeeId()};
		
		outVO = this.jdbcTemplate.queryForObject(
				sb.toString(), 
				args,
				rowMapper);
		
		LOG.debug("=outVO=" + outVO);
		
		if (null == outVO) { throw new EmptyResultDataAccessException(1); }
		
		LOG.debug("====================================");
		return outVO;
	}

	@Override
	public List<Commuting> doSelectList(Search search) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int doUpsert(DTO dto) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doUpsert=");
		int verify = 0;
		Commuting inVO = (Commuting) dto;
		
		StringBuilder sb = new StringBuilder();
		sb.append("  MERGE INTO commuting c                                                                                    \n");
		sb.append("  USING DUAL                                                                                                \n");
		sb.append("  ON (                                                                                                      \n");
		sb.append("      c.employee_id = ?                                                                                     \n");
		sb.append("      AND TO_CHAR(c.reg_dt,'yyyy-mm-dd') = TO_CHAR(SYSDATE,'yyyy-mm-dd')                                    \n");
		sb.append("      )                                                                                                     \n");
		sb.append("  WHEN MATCHED THEN                                                                                         \n");
		sb.append("  UPDATE                                                                                                    \n");
		sb.append("  SET                                                                                                       \n");
		sb.append("      leave_time = SYSDATE,                                                                                 \n");
		sb.append("      present_state = 20,                                                                                   \n");
		sb.append("      state =                                                                                               \n");
		sb.append("  	(                                                                                                      \n");
		sb.append("              CASE                                                                                          \n");
		sb.append("              WHEN TO_CHAR(SYSDATE,'hh24') < '18' AND state = 0 THEN 2                                      \n");
		sb.append("              WHEN TO_CHAR(SYSDATE,'hh24') < '18' AND state = 1 THEN 12                                     \n");
		sb.append("              ELSE state                                                                                    \n");
		sb.append("              END                                                                                           \n");
		sb.append("  	 ),                                                                                                    \n");
		sb.append("      work_time =                                                                                           \n");
		sb.append("                (                                                                                           \n");
		sb.append("                TO_CHAR( TRUNC(((SYSDATE - attend_time) - TRUNC(SYSDATE - attend_time)) * 24-1))            \n");
		sb.append("                || '시간' ||                                                                                  \n");
		sb.append("                TO_CHAR(FLOOR(((((SYSDATE - attend_time) -TRUNC(SYSDATE - attend_time)) * 24)               \n");
		sb.append("                        - TRUNC(((SYSDATE - attend_time)-TRUNC(SYSDATE - attend_time)) * 24)) * 60)         \n");
		sb.append("                        )                                                                                   \n");
		sb.append("                || '분'                                                                                      \n");
		sb.append("                )                                                                                           \n");
		sb.append("  WHEN NOT MATCHED THEN                                                                                     \n");
		sb.append("  INSERT (                                                                                                  \n");
		sb.append("      seq,                                                                                                  \n");
		sb.append("      employee_id,                                                                                          \n");
		sb.append("      name,                                                                                                 \n");
		sb.append("      dept_no,                                                                                              \n");
		sb.append("      attend_time,                                                                                          \n");
		sb.append("      present_state,                                                                                        \n");
		sb.append("      state,                                                                                                \n");
		sb.append("      reg_dt                                                                                                \n");
		sb.append("  ) VALUES (                                                                                                \n");
		sb.append("      ATTEND_SEQ.nextval,                                                                                   \n");
		sb.append("      ?,                                                                                                    \n");
		sb.append("      ?,                                                                                                    \n");
		sb.append("      ?,                                                                                                    \n");
		sb.append("      SYSDATE,                                                                                              \n");
		sb.append("      10,                                                                                                   \n");
		sb.append("      ( 		                                                                                               \n");
		sb.append("       		 CASE                                                                                          \n");
		sb.append("              WHEN TO_CHAR(SYSDATE,'hh24') < '09' AND TO_CHAR(SYSDATE,'hh24') > '24' THEN 0                 \n");
		sb.append("              WHEN TO_CHAR(SYSDATE,'hh24') > '09' AND TO_CHAR(SYSDATE,'hh24') < '24' THEN 1                 \n");
		sb.append("              END                                                                                           \n");
		sb.append("  	 ),                                                                                                    \n");
		sb.append("      SYSDATE                                                                                               \n");
		sb.append("  )                                                                                                         \n");
		
		LOG.debug("=upsert.param=" + inVO);
		LOG.debug("=upsert.sql=" + sb.toString());
		
		Object[] args = {
				inVO.getEmployeeId(),
				inVO.getEmployeeId(),
				inVO.getName(),
				inVO.getDeptNo()
		};
		
		verify = this.jdbcTemplate.update(sb.toString(), args);
		
		LOG.debug("=verify=" + verify);
		LOG.debug("====================================");

		return verify;
	}
}
