package com.omg.employee.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.omg.cmn.DTO;
import com.omg.cmn.Search;

import org.springframework.jdbc.core.RowMapper;

@Repository("employeeDao")
public class EmployeeDao {
	final static Logger LOG=LoggerFactory.getLogger(EmployeeDao.class);
	
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
	
	public int passwdConfirm(EmployeeVO employee)  {
		int cnt=0;
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT count(*) cnt  \n");
		sb.append("FROM employee        \n");
		sb.append("WHERE employee_id=?  \n");
		sb.append("    AND password=?   \n");
		LOG.debug("=====================================");
		LOG.debug("=sql\n="+sb.toString());
		LOG.debug("=param="+employee);
		LOG.debug("=====================================");
		
		Object[] args= {employee.getEmployee_id(),
						employee.getPassword()};
		cnt=(int) this.jdbcTemplate.queryForObject(sb.toString(), (Object[]) args, idMapper);
		return cnt;
	}
	
	public int idConfirm(EmployeeVO employee) {
		int cnt=0;
		StringBuilder sb=new StringBuilder();
		
		sb.append("SELECT count(*) cnt          \n");
		sb.append("FROM employee                \n");
		sb.append("WHERE employee_id=?          \n");
		LOG.debug("=====================================");
		LOG.debug("=sql\n="+sb.toString());
		LOG.debug("=param="+employee.getEmployee_id());
		LOG.debug("=====================================");
	
		Object[] args = {employee.getEmployee_id()};
		cnt=(int) this.jdbcTemplate.queryForObject(sb.toString(), (Object[]) args, idMapper);

		LOG.debug("=flag idConfrim="+cnt);
		return cnt;
	}
	
	
	public List<EmployeeVO> doSelectList(Search search){
		StringBuilder sbWhere=new StringBuilder();
		
		//이름(10), 부서(20)
		if(null != search.getSearchDiv() && !"".equals(search.getSearchDiv())) {
			if("10".equals(search.getSearchDiv())) {
				sbWhere.append(" WHERE name like '%'|| ? ||'%'  \n");
			}else if("20".equals(search.getSearchDiv())) {
				sbWhere.append(" WHERE dept_no like '%'|| ? ||'%'  \n");
			}
		}
		
		StringBuilder sb=new StringBuilder();
		sb.append(" SELECT T1.*,T2.*                                                                \n");
		sb.append(" FROM                                                                            \n");
		sb.append("  (                                                                              \n");
		sb.append("      SELECT B.rnum,                                                             \n");
		sb.append("             B.employee_id,                                                             \n");
		sb.append("             B.password,                                                             \n");
		sb.append("             B.name,                                                           \n");
		sb.append("             B.dept_no,                                                          \n");
		sb.append("             B.position_no,                                                            \n");
		sb.append("             B.cell_phone,                                                        \n");
		sb.append("             B.email,                                                             \n");
		sb.append("             B.address,                                                             \n");
		sb.append("             TO_CHAR(B.hire_date,'YYYYMMDD') hire_date,                                                         \n");
		sb.append("             B.birth_day,                                                             \n");
		sb.append("             B.holiday,                                                             \n");
		sb.append("             B.img_code                                                             \n");
		sb.append("      FROM (                                                                     \n");
		sb.append("          SELECT ROWNUM rnum, A.*                                                \n");
		sb.append("          FROM(                                                                  \n");
		sb.append("              SELECT *                                                           \n");
		sb.append("              FROM  employee                                                 \n");
		//----------------------------------------------------------------------------------------------
		//Where조건 
		//----------------------------------------------------------------------------------------------		
		sb.append(sbWhere.toString() );

		sb.append("              ORDER BY hire_date DESC                                               \n");
		sb.append("          )A                                                                     \n");
		//sb.append("          WHERE ROWNUM <=(&PAGE_SIZE *(&PAGE_NUM-1)+&PAGE_SIZE)                  \n");
		sb.append("          WHERE ROWNUM <=(? *(?-1)+?)                  \n");

		sb.append("      )B                                                                         \n");
		//sb.append("      WHERE b.rnum >=(&PAGE_SIZE *(&PAGE_NUM-1)+1)                               \n");
		sb.append("      WHERE b.rnum >=(? *(?-1)+1)                               \n");
		sb.append("      )T1                                                                        \n");
		sb.append("      CROSS JOIN                                                                 \n");
		sb.append("      --전체COUNT                                                                  \n");
		sb.append("      (SELECT COUNT(*) total_cnt                                                 \n");
		sb.append("       FROM  employee                                                           \n");
		//----------------------------------------------------------------------------------------------
		//Where조건 
		//----------------------------------------------------------------------------------------------		
		sb.append(sbWhere.toString() );
		sb.append("      )T2                                                                        \n");
		//param 처리
		List<Object> listArg=new ArrayList<Object>();
		
		//검색조건+:7개 ?
		if(null != search.getSearchDiv() && !"".equals(search.getSearchDiv())) {
			listArg.add(search.getSearchWord());
			
			listArg.add(search.getPageSize());
			listArg.add(search.getPageNum());
			listArg.add(search.getPageSize());
			listArg.add(search.getPageSize());
			listArg.add(search.getPageNum());
			
			listArg.add(search.getSearchWord());
		}else {
			//&PAGE_SIZE *(&PAGE_NUM-1)+&PAGE_SIZE
			//&PAGE_SIZE *(&PAGE_NUM
			listArg.add(search.getPageSize());
			listArg.add(search.getPageNum());
			listArg.add(search.getPageSize());
			listArg.add(search.getPageSize());
			listArg.add(search.getPageNum());
		}
		LOG.debug("========================");
		LOG.debug("=sql\n="+sb.toString());
		LOG.debug("=param="+search);
		LOG.debug("========================");	
		
		List<EmployeeVO> list=(List<EmployeeVO>)jdbcTemplate.query(sb.toString(), listArg.toArray(), rowMapper);
		for(EmployeeVO vo: list) {
			LOG.debug("=vo="+vo);
		}
		
		
		return list;
	}
	
	public List<EmployeeVO> doSelectAll(EmployeeVO employee){
		List<EmployeeVO> list=null;
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT                      \n");
		sb.append("    employee_id,            \n");
		sb.append("    password,               \n");
		sb.append("    name,                   \n");
		sb.append("    dept_no,                \n");
		sb.append("    position_no,            \n");
		sb.append("    cell_phone,             \n");
		sb.append("    email,                  \n");
		sb.append("    address,                \n");
		sb.append("    hire_date,              \n");
		sb.append("    birth_day,              \n");
		sb.append("    holiday,                \n");
		sb.append("    img_code                \n");
		sb.append("FROM                        \n");
		sb.append("    employee                \n");
		sb.append("WHERE employee_id like ?    \n");
		LOG.debug("========================");
		LOG.debug("=sql\n="+sb.toString());
		LOG.debug("=param="+employee);
		LOG.debug("========================");
		
		list=this.jdbcTemplate.query(sb.toString(), 
									new Object[] {"%"+employee.getEmployee_id()+"%"},
									rowMapper);
		for(EmployeeVO vo:list) {
			LOG.debug("====================================");
			LOG.debug("=vo="+vo);
			LOG.debug("====================================");
		}
		return list;
	}
	
	public int count(EmployeeVO employee) {
		int cnt=0;
		
		StringBuilder  sb=new StringBuilder();
		sb.append("SELECT COUNT(*) cnt        \n");
		sb.append("FROM employee              \n");
		sb.append("WHERE employee_id like ?   \n");
		
		LOG.debug("========================");
		LOG.debug("=sql\n="+sb.toString());
		LOG.debug("=param="+employee);
		LOG.debug("========================");
		
		cnt=this.jdbcTemplate.queryForObject(sb.toString(), 
											new Object[] {"%"+employee.getEmployee_id()+"%"},
											Integer.class
				);
		LOG.debug("========================");
		LOG.debug("=cnt="+cnt);
		LOG.debug("========================");		
		return cnt;
	}
	
	public int doUpdate(EmployeeVO employee) {
		int flag=0;
		StringBuilder sb=new StringBuilder();
		sb.append("UPDATE employee             \n");
		sb.append("SET	password 	= ?,       \n");
		sb.append("	dept_no 	= ?,           \n");
		sb.append("	position_no = ?,           \n");
		sb.append("	cell_phone 	= ?,           \n");
		sb.append("	email 		= ?,           \n");
		sb.append("	address 	= ?,           \n");
		sb.append("	holiday 	= ?,           \n");
		sb.append("	img_code 	= ?            \n");
		sb.append("WHERE employee_id = ?       \n");
		LOG.debug("========================");
		LOG.debug("=sql\n="+sb.toString());
		LOG.debug("=param="+employee);
		LOG.debug("========================");	
		
		Object[] args= {	employee.getPassword(),
							employee.getDept_no(),
							employee.getPosition_no(),
							employee.getCell_phone(),
							employee.getEmail(),
							employee.getAddress(),
							employee.getHoliday(),
							employee.getImg_code(),
							employee.getEmployee_id()
		};
		
		flag=this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("=flag="+flag);	
		return flag;
		
	}
	
	public EmployeeVO doSelectOne(String id) {
		EmployeeVO outVO=null;
		StringBuilder  sb=new StringBuilder();
		
		sb.append("SELECT                   \n");
		sb.append("    employee_id,         \n");
		sb.append("    password,            \n");
		sb.append("    name,                \n");
		sb.append("    dept_no,             \n");
		sb.append("    position_no,         \n");
		sb.append("    cell_phone,          \n");
		sb.append("    email,               \n");
		sb.append("    address,             \n");
//		sb.append("         TO_CHAR(hire_date,'YYYY-MM-DD HH24MISS') AS hire_date \n");
		sb.append("         TO_CHAR(hire_date,'YY/MM/DD ') AS hire_date, \n");
		sb.append("    birth_day,           \n");
		sb.append("    holiday,             \n");
		sb.append("    img_code             \n");
		sb.append("FROM                     \n");
		sb.append("    employee             \n");
		sb.append("WHERE employee_id = ?    \n");
		
		LOG.debug("========================");
		LOG.debug("=sql\n="+sb.toString());
		LOG.debug("=param="+id);
		LOG.debug("========================");	
		
		Object args[] = {id};
		outVO = (EmployeeVO) this.jdbcTemplate.queryForObject(sb.toString(), 
    			                        args, 
    			                        rowMapper);
		
		LOG.debug("========================");
		LOG.debug("=outVO="+outVO);
		LOG.debug("========================");	
		
		return outVO;
	}
	
	/**
	 * 삭제
	 * @param employee
	 * @return
	 */

	public int doDelete(EmployeeVO employee){
		int flag = 0;
		StringBuilder  sb=new StringBuilder();
		sb.append("DELETE FROM employee       \n");
		sb.append("WHERE employee_id = ?      \n");
		
		LOG.debug("=====================================");
		LOG.debug("=sql=\n"+sb.toString());
		LOG.debug("=param="+employee);
		LOG.debug("=====================================");
		
		Object[] args= {employee.getEmployee_id()};
		flag=this.jdbcTemplate.update(sb.toString(),args);
		
		return flag;
	}
	
	/**
	 * 등록
	 * @param employee
	 * @return
	 */
	public int doInsert(EmployeeVO employee) {
		int flag=0;
		Object[] args= {
						employee.getEmployee_id(),
						employee.getPassword(),
						employee.getName(),
						employee.getDept_no(),
						employee.getPosition_no(),
						employee.getCell_phone(),
						employee.getEmail(),
						employee.getAddress(),
						employee.getHire_date(),
						employee.getBirth_day(),
						employee.getHoliday(),
						employee.getImg_code()
		};
		
		StringBuilder sb=new StringBuilder();
		sb.append("INSERT INTO employee (       \n");
		sb.append("    employee_id,             \n");
		sb.append("    password,                \n");
		sb.append("    name,                    \n");
		sb.append("    dept_no,                 \n");
		sb.append("    position_no,             \n");
		sb.append("    cell_phone,              \n");
		sb.append("    email,                   \n");
		sb.append("    address,                 \n");
		sb.append("    hire_date,                 \n");
		sb.append("    birth_day,               \n");
		sb.append("    holiday,                 \n");
		sb.append("    img_code                 \n");
		sb.append(") VALUES (                   \n");
		sb.append("    ?,                       \n");
		sb.append("    ?,                       \n");
		sb.append("    ?,                       \n");
		sb.append("    ?,                       \n");
		sb.append("    ?,                       \n");
		sb.append("    ?,                       \n");
		sb.append("    ?,                       \n");
		sb.append("    ?,                       \n");
		sb.append("    ?,                       \n");
		sb.append("    ?,                       \n");
		sb.append("    ?,                       \n");
		sb.append("    ?                        \n");
		sb.append(")                            \n");
		LOG.debug("========================");
		LOG.debug("=sql=\n"+sb.toString());
		LOG.debug("=param=\n"+employee);

		LOG.debug("========================");		
		
		flag=this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("=flag="+flag);
		
		return flag;
	 
	}
	
	
}
