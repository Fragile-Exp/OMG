package com.omg.employee.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.omg.cmn.DTO;
import com.omg.cmn.Search;
import com.omg.employee.domain.EmployeeVO;
import com.omg.employee.service.EmployeeService;

import org.springframework.jdbc.core.RowMapper;

@Repository("employeeDao")
public class EmployeeDao  {
	final static Logger LOG=LoggerFactory.getLogger(EmployeeDao.class);
	
	private final String NAMESPACE="com.omg.employee";
	
	@Autowired
   SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	DataSource dataSource;
	
	 RowMapper rowMapper= new RowMapper<EmployeeVO>() {

		@Override
		public EmployeeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmployeeVO outVO=new EmployeeVO();

			outVO.setEmployee_id(rs.getString("employee_id"));
			outVO.setPassword(rs.getString("password"));
			outVO.setName(rs.getString("name"));
			outVO.setDept_no(rs.getInt("dept_no"));
			outVO.setPosition_no(rs.getInt("position_no"));
			outVO.setCell_phone(rs.getInt("cell_phone"));
			outVO.setEmail(rs.getString("email"));
			outVO.setAddress(rs.getString("address"));
			outVO.setHire_date(rs.getString("hire_date"));
			outVO.setBirth_day(rs.getString("birth_day"));
			outVO.setHoliday(rs.getInt("holiday"));
			outVO.setImg_code(rs.getString("img_code"));
			
			return outVO;
		}
		
	};
	
	RowMapper idMapper=new RowMapper() {

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			int outCnt=rs.getInt("cnt");
			return outCnt;
		}
	};
	RowMapper passwdMapper=new RowMapper() {

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			int outCnt=rs.getInt("cnt");
			return outCnt;
		}
	};
	
	
	public EmployeeDao() {}
	
	/**
	 * 비밀번호 확인
	 * @param employee
	 * @return 1(비밀번호 일치)/0(비밀번호 불일치)
	 */
	public int passwdConfirm(EmployeeVO employee)  {
		LOG.debug("==================");
		LOG.debug("passwdConfirm");
		LOG.debug("==================");
		
		String statement = NAMESPACE + ".passwdConfirm";
		LOG.debug("statement:" + statement);
		LOG.debug("EmployeeVO:" + employee);
		
		int cnt=this.sqlSessionTemplate.selectOne(statement, employee);
		LOG.debug("cnt:" + cnt);
		
		return cnt;
	}
	
	/**
	 * 아이디 존재여부
	 * @param employee
	 * @return 1(존재)/0(존재하지 않는 아이디)
	 */
	public int idConfirm(EmployeeVO employee) {
		LOG.debug("==================");
		LOG.debug("idConfirm");
		LOG.debug("==================");
		
		String statement = NAMESPACE + ".idConfirm";
		LOG.debug("statement:" + statement);
		LOG.debug("EmployeeVO:" + employee);
		
		int cnt=this.sqlSessionTemplate.selectOne(statement, employee);
		LOG.debug("cnt:" + cnt);
		
		return cnt;
	}
	
	
	public List<EmployeeVO> doSelectList(Search search){
		LOG.debug("==================");
		LOG.debug("doSelectList");
		LOG.debug("==================");
       
		String statement = NAMESPACE + ".doSelectList";
		LOG.debug("statement:" + statement);
		LOG.debug("search:" + search);
		
		List<EmployeeVO> list=this.sqlSessionTemplate.selectList(statement, search);
		
		for(EmployeeVO vo:list) {
			LOG.debug("vo:"+vo);
		}
		
		return list;
	}
	
	public List<EmployeeVO> doSelectAll(EmployeeVO employee){
		LOG.debug("==================");
		LOG.debug("doSelectAll");
		LOG.debug("==================");
       
		String statement = NAMESPACE + ".doSelectAll";
		LOG.debug("statement:" + statement);
		LOG.debug("EmployeeVO:" + employee);
		

		List<EmployeeVO> list=this.sqlSessionTemplate.selectList(statement, employee);
		
		for(EmployeeVO vo:list) {
			LOG.debug("====================================");
			LOG.debug("=vo="+vo);
			LOG.debug("====================================");
		}
		return list;
	}
	
	
	public int doUpdate(EmployeeVO employee) {
		LOG.debug("==================");
		LOG.debug("doUpdate");
		LOG.debug("==================");
       
		String statement = NAMESPACE + ".doUpdate";
		LOG.debug("statement" + statement);
		LOG.debug("EmployeeVO:" + employee);
		
		int flag=this.sqlSessionTemplate.update(statement, employee);
		LOG.debug("flag" + flag);
		
		return flag;
	}
	
	public String getImgCode( ) {
		LOG.debug("==================");
	    LOG.debug("getImgCode");
	    LOG.debug("==================");
	    
	    String statement = NAMESPACE + ".getImgCode";
	    LOG.debug("statement:" + statement);
	    
	    String outVO=this.sqlSessionTemplate.selectOne(statement);
	    LOG.debug("outVO" + outVO);
	    
		return outVO;
	}
	
	
	public EmployeeVO doSelectOne(EmployeeVO employee) {
		LOG.debug("==================");
	    LOG.debug("doSelectOne");
	    LOG.debug("==================");
	    
	    String statement = NAMESPACE + ".doSelectOne";
	    LOG.debug("statement:" + statement);
	    LOG.debug("EmployeeVO:" + employee);
	    
	    EmployeeVO outVO=this.sqlSessionTemplate.selectOne(statement, employee);
	    LOG.debug("outVO" + outVO);
	    
		return outVO;
	}
	
	/**
	 * 전체삭제
	 * @return
	 */
	public int doDeleteAll() {
		LOG.debug("==================");
		LOG.debug("doDelete");
		LOG.debug("==================");
      
		//.중요
		String statement = NAMESPACE + ".doDeleteAll";
		LOG.debug("statement" + statement);
	      
		int flag = sqlSessionTemplate.insert(statement);
		LOG.debug("flag" + flag);
      
		return flag;
	}
	
	/**
	 * 삭제
	 * @param employee
	 * @return
	 */

	public int doDelete(EmployeeVO employee){
		LOG.debug("==================");
		LOG.debug("doDelete");
		LOG.debug("==================");
		
		String statement = NAMESPACE + ".doDelete";
	    LOG.debug("statement" + statement);
	    LOG.debug("EmployeeVO:" + employee);
		
	    int flag=sqlSessionTemplate.delete(statement, employee);
	    LOG.debug("flag" + flag);
	    
		return flag;
	}
	
	/**
	 * 등록
	 * @param employee
	 * @return
	 */
	public int doInsert(EmployeeVO employee) {
		LOG.debug("==================");
	    LOG.debug("doInsert");
	    LOG.debug("==================");

	    //.중요
	    String statement = NAMESPACE + ".doInsert";
	    LOG.debug("statement:" + statement);
	    LOG.debug("EmployeeVO:" + employee);
	    
	    int flag=sqlSessionTemplate.delete(statement, employee);
		
		return flag;
	}

	
}
