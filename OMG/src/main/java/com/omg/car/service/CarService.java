package com.omg.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.car.dao.CarDaoImpl;
import com.omg.car.domain.CarVO;
import com.omg.document.dao.DocumentDaoImpl;
import com.omg.document.domain.DocumentVO;

@Service("carService")
public class CarService {

	@Autowired
	CarDaoImpl carDaoImpl;
	
	/**
	 * 차 데이터 삽입 
	 * @param documentVO
	 * @return
	 */
	public int doInsert(CarVO carVO) {
		return carDaoImpl.doInsert(carVO);
	}
	
	/**
	 * 문서 삭제 
	 * @param documentVO
	 * @return
	 */
	public int doDelete(CarVO carVO) {
		return carDaoImpl.doDelete(carVO);
	}
	
	/**
	 * 문서 갱신
	 * @param documentVO
	 * @return
	 */
	public int doUpdate(CarVO carVO) {
		return carDaoImpl.doUpdate(carVO);
	}
	
	public CarVO doSelectOne(CarVO carVO) {
		return carDaoImpl.doSelectOne(carVO);
	}
	
}
