package com.mieba.demo.dao;


import com.mieba.demo.bean.Cost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CostDao {

    //查找全部记录
    List<Cost> findAll();

    //按照id查找
    List<Cost> findById(@Param("id") int id);

    boolean deleteCost(@Param("id") int id);

    boolean updateCost(Cost cost);

    boolean insertCost(Cost cost);
}
