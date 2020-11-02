package com.omg.schedule.service;

import java.util.List;

import com.omg.schedule.domain.ScheduleVO;

public interface ScheduleService {
    
    public int doInsert(ScheduleVO scheduleVO);
    
    public int doDelete(int scheduleNo);
    
    public int doUpdate(ScheduleVO scheduleVO);
    
    public ScheduleVO doSelectOne(int scheduleNo);
    
    public List<ScheduleVO> doSelectList(int deptNo);
    
}
