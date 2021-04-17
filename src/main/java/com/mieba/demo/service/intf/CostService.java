package com.mieba.demo.service.intf;

import com.mieba.demo.bean.Cost;

import java.util.List;

public interface CostService {

    Cost queryById(int id);

    List<Cost> queryAll();

    boolean delete(int id);

    boolean updateById(Cost cost);

    boolean insert(Cost cost);
}
