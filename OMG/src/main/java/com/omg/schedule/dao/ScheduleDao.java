package com.omg.schedule.dao;

import java.util.List;

import com.omg.schedule.domain.ScheduleVO;

public interface ScheduleDao {

    public int doInsert(ScheduleVO scheduleVO);
    
    public int doDelete(int scheduleNo);
    
    public int doUpdate(ScheduleVO scheduleVO);
    
    public ScheduleVO doSelectOne(int scheduleNo);
    
    public List<ScheduleVO> doSelectList(int deptNo);

}
