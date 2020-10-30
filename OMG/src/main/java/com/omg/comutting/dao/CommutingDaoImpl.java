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
			outData.setAttendTime(rs.getString("attendTime"));
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
	public int doInsert(DTO dto) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doInsert=");
		
		Commuting inVO = (Commuting) dto;
		int verify = 0;

		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO comutting (       \n");
		sb.append("     seq,                      \n");
		sb.append("     employee_id,              \n");
		sb.append("     name,                     \n");
		sb.append("     dept_no,                  \n");
		sb.append("     attend_time,              \n");
		sb.append("     leave_time,               \n");
		sb.append("     present_state,            \n");
		sb.append("     state,                    \n");
		sb.append("     work_time,                \n");
		sb.append("     reg_dt                    \n");
		sb.append(" ) VALUES (                    \n");
		sb.append("     ATTEND_SEQ.nextval,       \n");
		sb.append("     ?,                        \n");
		sb.append("     ?,                        \n");
		sb.append("     ?,                        \n");
		sb.append("     ?,                        \n");
		sb.append("     ?,                        \n");
		sb.append("     ?,                        \n");
		sb.append("     ?,                        \n");
		sb.append("     ?,                        \n");
		sb.append("     ?                         \n");
		sb.append(" )                             \n");
		
		LOG.debug("=insert.param=" + inVO);
		LOG.debug("=insert.sql=" + sb.toString());
		
		Object[] args = {
				inVO.getEmployeeId(),
				inVO.getName(),
				inVO.getDeptNo(),
				inVO.getAttendTime(),
				inVO.getLeaveTime(),
				inVO.getPresentState().intValue(),
				inVO.getState().intValue(),
				inVO.getWorkTime(),
				inVO.getRegDt()
		};
		
		verify = this.jdbcTemplate.update(sb.toString(), args);
		
		LOG.debug("====================================");
		return verify;
	}

	@Override
	public int doDelete(DTO dto) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doDelete=");
		Commuting inVO = (Commuting) dto;
		int verify = 0;
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE FROM comutting   \n");
		sb.append(" WHERE                   \n");
		sb.append("     seq = ?             \n");
		
		LOG.debug("=delete.param=" + inVO);
		LOG.debug("=delete.sql=" + sb.toString());
		
		Object[] args = {inVO.getSeq()};
		verify = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("====================================");
		return verify;
	}

	@Override
	public int doUpdate(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DTO doSelectOne(DTO dto) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doSelectOne=");
		Commuting inVO = (Commuting) dto;
		Commuting outVO = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT                    \n");
		sb.append("     seq,                  \n");
		sb.append("     employee_id,          \n");
		sb.append("     name,                 \n");
		sb.append("     dept_no,              \n");
		sb.append("     attend_time,          \n");
		sb.append("     leave_time,           \n");
		sb.append("     present_state,        \n");
		sb.append("     state,                \n");
		sb.append("     work_time,            \n");
		sb.append("     reg_dt                \n");
		sb.append(" FROM                      \n");
		sb.append("     comutting             \n");
		sb.append(" WHERE seq=?               \n");
		
		LOG.debug("=selectone.param=" + inVO);
		LOG.debug("=selectone.sql=" + sb.toString());
		Object[] args = {inVO.getSeq()};
		
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
}
