package com.omg.schedule.dao;

import java.util.List;

import com.omg.schedule.domain.ScheduleCatVO;

public interface ScheduleCatDao {

    public int doInsert(ScheduleCatVO scheduleCatVO);

    public int doDelete(int categoryId);

    public int doUpdate(ScheduleCatVO scheduleCatVO);

    public ScheduleCatVO doSelectOne(int categoryId);

    public List<ScheduleCatVO> doSelectList();
}
