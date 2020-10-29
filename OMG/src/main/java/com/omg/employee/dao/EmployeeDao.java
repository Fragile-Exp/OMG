package com.omg.employee.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


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
	
	//湲곕낯 �깮�꽦�옄
	public EmployeeDao() {}
	
	/**
	 * �궗�썝�벑濡�
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
