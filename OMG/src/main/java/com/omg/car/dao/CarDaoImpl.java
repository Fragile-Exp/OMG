package com.omg.car.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.omg.car.domain.CarVO;

@Repository("carDao")
public class CarDaoImpl {

	final static Logger LOG = LoggerFactory.getLogger(CarDaoImpl.class);
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE ="com.omg.car";
	
	DataSource dataSource; 
	
	RowMapper rowMapper = new RowMapper<CarVO>() {
		@Override
		public CarVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CarVO outVO = new CarVO();

			outVO.setCarNum(rs.getString("carNum"));			// 차량번호 pk
			outVO.setKind(rs.getString("kind"));  				// 차량종류
			outVO.setCarUse(rs.getString("carUse"));			// 차량연료   
			outVO.setEmployeeId(rs.getString("employeeId"));  // 사번    fk
			outVO.setCarSet(rs.getInt("carSet"));             // 차량상태   
			outVO.setRentDay(rs.getString("rentDay"));        // 대여기간   
			outVO.setReason(rs.getString("reason"));            // 대여사유   
			 
		    return outVO;
			}
	    };
	    
	
	public CarDaoImpl() {}
	
	public int doInsert(CarVO carVO) {
		LOG.debug("=doInsert=");
		
		String statement = NAMESPACE+".doInsert";
		LOG.debug("=statement="+statement);
		LOG.debug("=boardVO="+carVO);
		
		int flag = sqlSessionTemplate.insert(statement, carVO);
		return flag;
			
	}

	
	public int doDelete(CarVO carVO) {
		LOG.debug("=doDelete=");
		
		String statement = NAMESPACE+".doDelete";
		LOG.debug("=statement="+statement);
		LOG.debug("=boardVO="+carVO);
		
		int flag = sqlSessionTemplate.delete(statement, carVO);
		return flag;
		
	}

	public int doUpdate(CarVO carVO) {
		LOG.debug("=doUpdate=");
		
		String statement = NAMESPACE+".doUpdate";
		LOG.debug("=statement="+statement);
		LOG.debug("=boardVO="+carVO);
		
		int flag = sqlSessionTemplate.update(statement, carVO);
		return flag;
		
	}

	
	public CarVO doSelectOne(CarVO carVO) {
		LOG.debug("=doSelectOne=");
		String statement = NAMESPACE+".doSelectOne";
		LOG.debug("=statement="+statement);
		LOG.debug("=boardVO="+carVO);
		
		CarVO outVO = this.sqlSessionTemplate.selectOne(statement, carVO);
		LOG.debug("=outVO="+outVO);
		
		return outVO;
	}

	
	public List<CarVO> doSelectList() {
		LOG.debug("=doSelectList=");
		String statement = NAMESPACE+".doSelectList";
		LOG.debug("=statement="+statement);
		
		List<CarVO> list = this.sqlSessionTemplate.selectList(statement);
		
		return list;
	}
		

	
}
