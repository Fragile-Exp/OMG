package com.omg.schedule.service;

import java.util.List;

import com.omg.schedule.domain.ScheduleCatVO;

public interface ScheduleCatService {
    
    public int doInsert(ScheduleCatVO scheduleCatVO);

    public int doDelete(int categoryId);

    public int doUpdate(ScheduleCatVO scheduleCatVO);

    public ScheduleCatVO doSelectOne(int categoryId);

    public List<ScheduleCatVO> doSelectList();
}
