package com.omg.schedule.dao;

import java.util.List;

import com.omg.schedule.domain.ScheduleCatVO;

public interface ScheduleCatDao {

    public int doInsert(ScheduleCatVO scheduleCatVO);

    public int doDelete(ScheduleCatVO scheduleCatVO);

    public int doUpdate(ScheduleCatVO scheduleCatVO);

    public ScheduleCatVO doSelectOne(ScheduleCatVO scheduleCatVO);

    public List<ScheduleCatVO> doSelectList();
}
