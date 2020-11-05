package com.omg.comutting.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.omg.cmn.DTO;
import com.omg.cmn.Search;
import com.omg.cmn.StringUtil;
import com.omg.commuting.domain.Commuting;
import com.omg.commuting.domain.PresentState;
import com.omg.commuting.domain.State;

import oracle.net.ns.Communication;

@Repository("commutingDao")
public class CommutingDaoImpl implements CommutingDao {

	/** LOG */
	final static Logger LOG = LoggerFactory.getLogger(CommutingDaoImpl.class);
	
	
	/** namespace*/
	private final String NAMESPACE = "com.omg.commuting";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
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
			outData.setNum(rs.getInt("rnum"));
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


		String statement = NAMESPACE + ".doDelete";

		LOG.debug(">statement>" + statement);
		LOG.debug(">param>" + inVO);
		
		int verify = this.sqlSessionTemplate.insert(statement, inVO);

		LOG.debug("=verify=" + verify);
		LOG.debug("====================================");

		return verify;
	}

	@Override
	public DTO doSelectOne(DTO dto) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doSelectOne=");

		Commuting inVO = (Commuting) dto;
		 
		
		String statement = NAMESPACE + ".doSelectOne";
		Commuting outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("=selectone.param=" + inVO);
		LOG.debug("=outVO=" + outVO);

		if (null == outVO) {
			throw new EmptyResultDataAccessException(1);
		}

		LOG.debug("====================================");
		return outVO;
	}

	@Override
	public List<Commuting> doSelectList(Search search) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doSelectList=");

		Search inVO = (Search) search;
		List<Commuting> cList = null;
		
		StringBuilder sbWhere = new StringBuilder();
		if (null != inVO.getSearchDiv() || !"".equals(inVO.getSearchDiv())) {
			if (inVO.getSearchDiv().equals("10")) { // 부서 + 오늘
				
				sbWhere.append("WHERE TO_CHAR(reg_dt,'yyyyMMdd') = TO_CHAR(sysdate,'yyyyMMdd') \n");
				sbWhere.append("AND dept_no = ?                                                \n");
				
			} else if (inVO.getSearchDiv().equals("20")) { // 부서+월별
				
				sbWhere.append("WHERE to_char(reg_dt,'yyyymm') = ? \n");  //search.div에 저장
				sbWhere.append("AND dept_no = ?                    \n");
				
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT C.*                                                                           \n");
		sb.append(" FROM                                                                                 \n");
		sb.append("   (                                                                                  \n");
		sb.append(" 		SELECT B.rnum,                                                               \n");
		sb.append(" 			 B.seq,                                                                  \n");
		sb.append(" 			 B.employee_id,                                                          \n");
		sb.append(" 			 B.name,                                                                 \n");
		sb.append(" 			 B.dept_no,                                                              \n");
		sb.append(" 			 DECODE(TO_CHAR(SYSDATE,'YYYYMMDD'),TO_CHAR(B.attend_time,'YYYYMMDD')    \n");
		sb.append(" 				   ,TO_CHAR(B.attend_time,'HH24:MI')                                 \n");
		sb.append(" 				   ,TO_CHAR(B.attend_time,'YYYY-MM-DD HH24:MI')) attend_time,        \n");
		sb.append(" 			 DECODE(TO_CHAR(SYSDATE,'YYYYMMDD'),TO_CHAR(B.leave_time,'YYYYMMDD')     \n");
		sb.append(" 				   ,TO_CHAR(B.leave_time,'HH24:MI')                                  \n");
		sb.append(" 				   ,TO_CHAR(B.leave_time,'YYYY-MM-DD HH24:MI')) leave_time,          \n");
		sb.append(" 			 B.present_state,                                                        \n");
		sb.append(" 			 B.state,                                                                \n");
		sb.append(" 			 B.work_time,                                                            \n");
		sb.append(" 			 DECODE(TO_CHAR(SYSDATE,'YYYYMMDD'),TO_CHAR(B.reg_dt,'YYYYMMDD')         \n");
		sb.append(" 				   ,TO_CHAR(B.reg_dt,'HH24:MI')                                      \n");
		sb.append(" 				   ,TO_CHAR(B.reg_dt,'YYYY-MM-DD')) reg_dt                           \n");
		sb.append(" 		FROM (                                                                       \n");
		sb.append(" 		  SELECT ROWNUM rnum, A.*                                                    \n");
		sb.append(" 		  FROM(                                                                      \n");
		sb.append(" 			  SELECT *                                                               \n");
		sb.append(" 			  FROM  commuting                                                        \n");
		sb.append("               --AND reg_dt = sysdate                                                 \n");
		sb.append("               --AND dept_no = 10000                                                  \n");
		sb.append("               --AND to_char(reg_dt,'yyyymm') = '202011'                              \n");
		// ----------------------------------------------------------------------------------------------------
		sb.append(sbWhere.toString());
		// ----------------------------------------------------------------------------------------------------
		sb.append(" 		  )A                                                                         \n");
		sb.append(" 		  WHERE ROWNUM <=(? *(?-1)+?)                    						     \n");
		sb.append(" 		)B                                                                           \n");
		sb.append(" 		WHERE b.rnum >=(? *(?-1)+1)                                					 \n");
		sb.append(" 	) C                                                                              \n");

		LOG.debug("=selectlist.param=" + inVO);
		LOG.debug("=selectlist.sql=" + sb.toString());
		
		List<Object> listArg = new ArrayList<Object>();
		
		if (null != inVO.getSearchDiv() || !"".equals(inVO.getSearchDiv())) {
			if (inVO.getSearchDiv().equals("10")) { // 부서 + 오늘
				listArg.add(inVO.getSearchWord());
				listArg.add(inVO.getPageSize());
				listArg.add(inVO.getPageNum());
				listArg.add(inVO.getPageSize());
				listArg.add(inVO.getPageSize());
				listArg.add(inVO.getPageNum());
			} else if (inVO.getSearchDiv().equals("20")) { // 부서+월별
				listArg.add(inVO.getSearchDiv()); // 'yyyyMM' 저장변수
				listArg.add(inVO.getSearchWord()); // 
				listArg.add(inVO.getPageSize());
				listArg.add(inVO.getPageNum());
				listArg.add(inVO.getPageSize());
				listArg.add(inVO.getPageSize());
				listArg.add(inVO.getPageNum());
			}else {
				listArg.add(inVO.getPageSize());
				listArg.add(inVO.getPageNum());
				listArg.add(inVO.getPageSize());
				listArg.add(inVO.getPageSize());
				listArg.add(inVO.getPageNum());
			}
		}
		
		cList = (List<Commuting>) this.jdbcTemplate.query(sb.toString(), listArg.toArray(), rowMapper);

		LOG.debug("=cList=" + cList);

		LOG.debug("====================================");
		return cList;
	}

	@Override
	public int doInsert(DTO dto) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doInsert=");
		
		Commuting inVO = (Commuting) dto;
		String statement = NAMESPACE + ".doInsert";

		LOG.debug(">statement>" + statement);
		LOG.debug(">param>" + inVO);
		
		int verify = this.sqlSessionTemplate.insert(statement, inVO);

		LOG.debug("=verify=" + verify);
		LOG.debug("====================================");

		return verify;
	}

	public int doUpdate(DTO dto) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doUpdate=");
		
		Commuting inVO = (Commuting) dto;
		String statement = NAMESPACE + ".doUpdate";

		LOG.debug(">statement>" + statement);
		LOG.debug(">param>" + inVO);
		
		int verify = this.sqlSessionTemplate.update(statement, inVO);

		LOG.debug("=verify=" + verify);
		LOG.debug("====================================");

		return verify;
		
		
	}
	
	public int doUpdateWorkTime(DTO dto) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doUpdate=");
		
		Commuting inVO = (Commuting) dto;
		String statement = NAMESPACE + ".doUpdateWorkTime";

		LOG.debug(">statement>" + statement);
		LOG.debug(">param>" + inVO);
		
		int verify = this.sqlSessionTemplate.update(statement, inVO);

		LOG.debug("=verify=" + verify);
		LOG.debug("====================================");

		return verify;
	}
	
	public int doInit() {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doInit=");
		
		String statement = NAMESPACE + ".doInit";

		LOG.debug(">statement>" + statement);
		
		int verify = this.sqlSessionTemplate.insert(statement);

		LOG.debug("=verify=" + verify);
		LOG.debug("====================================");
		return verify;
	}
}
