package com.omg.car.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.omg.car.domain.CarVO;
import com.omg.document.domain.DocumentVO;
import com.omg.schedule.domain.ScheduleVO;


public class CarDaoImpl implements CarDao {

	final static Logger LOG = LoggerFactory.getLogger(CarDaoImpl.class);
	public CarDaoImpl() {}
	@Autowired
    private JdbcTemplate jdbcTemplate;
	DataSource dataSource; 
	
	RowMapper rowMapper = new RowMapper<CarVO>() {
		@Override
		public CarVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CarVO outVO = new CarVO();

			outVO.setCar_num(rs.getString("car_num"));			// 차량번호 pk
			outVO.setKind(rs.getString("kind"));  				// 차량종류
			outVO.setCar_use(rs.getString("car_use"));			// 차량연료   
			outVO.setEmployee_id(rs.getString("employee_id"));  // 사번    fk
			outVO.setCar_set(rs.getInt("car_set"));             // 차량상태   
			outVO.setRent_day(rs.getString("rent_day"));        // 대여기간   
			outVO.setReason(rs.getString("reason"));            // 대여사유   
			 
		    return outVO;
			}
	    };
	
	
	@Override
	public int doInsert(CarVO carVO) {
		int flag = 0;
		Object[] args = {
				carVO.getCar_num(),
				carVO.getKind(),
				carVO.getCar_use(),
				carVO.getEmployee_id(),
				carVO.getCar_set(),
				carVO.getRent_day(),
				carVO.getReason()
				
			
		};
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" INSERT INTO car (         \n");
		sb.append("     car_num,              \n");
		sb.append("     kind,                 \n");
		sb.append("     car_use,              \n");
		sb.append("     employee_id,          \n");
		sb.append("     car_set,              \n");
		sb.append("     rent_day,             \n");
		sb.append("     reason,               \n");
		sb.append(" ) VALUES (                \n");
		sb.append("     ?,                    \n");
		sb.append("     ?,                    \n");
		sb.append("     ?,                    \n");
		sb.append("     ?,                    \n");
		sb.append("     ?,                    \n");
		sb.append("     ?,                    \n");
		sb.append("     ?,                    \n");
		sb.append(" )                         \n");
		
		LOG.debug("==============================");
		LOG.debug("= Parameter: " + carVO);
		LOG.debug("==============================");
		
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("flag: " + flag);
		
		return flag;
		
	}

	@Override 
	public int doDelete(CarVO carVO) {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" DELETE FROM car  \n");
		sb.append(" WHERE car_num = ? \n");
		
		LOG.debug("==============================");
		LOG.debug("= Parameter: " + carVO);
		LOG.debug("==============================");
		
		Object[] args = { carVO.getCar_num() };
		
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("flag: " + flag);
		
		return flag;
		
	}

	@Override
	public int doUpdate(CarVO carVO) {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" UPDATE car         \n");
		sb.append(" SET rent_day = ?   \n");
		sb.append("     reason = ?     \n");
		sb.append(" WHERE car_num = ?  \n");
		
		LOG.debug("==============================");
		LOG.debug("= Parameter: " + carVO);
		LOG.debug("==============================");
		
		Object[] args = {
			carVO.getRent_day(),
			carVO.getReason(),
			carVO.getCar_num()
		};
		
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("flag: " + flag);	
		
		return flag;
		
	}

	@Override
	public CarVO doSelectOne(CarVO carVO) {
		CarVO outVO = null;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT car_num,       \n");
		sb.append(" 	   kind,   		  \n");
		sb.append(" 	   car_use,   	  \n");
		sb.append(" 	   employee_id,   \n");
		sb.append(" 	   car_set, 	  \n");
		sb.append(" 	   TO_CHAR(end_dt, 'YYYY-MM-DD') AS  rent_day,  \n");
		sb.append(" 	   reason		  \n");
		sb.append(" FROM car              \n");
		sb.append(" WHERE car_num = ?     \n");
		
		LOG.debug("==============================");
		LOG.debug("= Parameter: " + carVO);
		LOG.debug("==============================");
		
		Object[] args = { carVO.getCar_num() };
		outVO = (CarVO) jdbcTemplate.queryForObject(sb.toString(), args, rowMapper);
		
		LOG.debug("==============================");
		LOG.debug("= outVO: " + outVO);
		LOG.debug("==============================");
		
		return outVO;
	}

	@Override
	public List<CarVO> doSelectList(CarVO carVO) {
		
		
		return null;
	}
		

	
}
