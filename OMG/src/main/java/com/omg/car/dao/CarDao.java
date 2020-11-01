package com.omg.car.dao;

import java.util.List;

import com.omg.car.domain.CarVO;
import com.omg.schedule.domain.ScheduleVO;

public interface CarDao {

	public int doInsert(CarVO carVO);
    
    public int doDelete(CarVO carVO);
    
    public int doUpdate(CarVO carVO);
    
    public CarVO doSelectOne(CarVO carVO);
    
    public List<CarVO> doSelectList(CarVO carVO);

}
