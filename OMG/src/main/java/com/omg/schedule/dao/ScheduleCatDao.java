package com.omg.schedule.dao;

import java.util.List;

import com.omg.schedule.domain.ScheduleCatVO;

public interface ScheduleCatDao {

    public int doInsert(ScheduleCatVO scheduleCat);

    public int doDelete(ScheduleCatVO scheduleCat);

    public int doUpdate(ScheduleCatVO scheduleCat);

    public ScheduleCatVO doSelectOne(ScheduleCatVO scheduleCat);

    public List<ScheduleCatVO> doSelectList();
}
