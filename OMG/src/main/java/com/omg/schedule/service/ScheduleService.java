package com.omg.schedule.service;

import java.util.List;

import com.omg.cmn.Criteria;
import com.omg.schedule.domain.ScheduleVO;

public interface ScheduleService {
    
    public int doInsert(ScheduleVO schedule);
    
    public int doDelete(ScheduleVO schedule);
    
    public int doUpdate(ScheduleVO schedule);
    
    public ScheduleVO doSelectOne(ScheduleVO schedule);
    
    public List<ScheduleVO> doSelectList(Criteria cri);
    
    public List<ScheduleVO> toDoList(Criteria cri);
    
    public  int getTotalCount(Criteria cri);
    
}
