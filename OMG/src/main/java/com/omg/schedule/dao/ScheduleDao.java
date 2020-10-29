package com.omg.schedule.dao;

import java.util.List;

import com.omg.schedule.domain.ScheduleVO;

public interface ScheduleDao {

    public int doInsert(ScheduleVO scheduleVO);
    
    public int doDelete(ScheduleVO scheduleVO);
    
    public int doUpdate(ScheduleVO scheduleVO);
    
    public ScheduleVO doSelectOne(ScheduleVO scheduleVO);
    
    public List<ScheduleVO> doSelectList(ScheduleVO scheduleVO);

}
