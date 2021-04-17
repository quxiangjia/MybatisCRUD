package com.mieba.demo.service.impl;

import com.mieba.demo.bean.Cost;
import com.mieba.demo.dao.CostDao;
import com.mieba.demo.service.intf.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CostServiceImpl implements CostService {

    @Autowired
    private CostDao costDao;


    @Override
    public Cost queryById(int id) {
        List<Cost> costList=new ArrayList<>();
        if (id > 0) {
            costList = costDao.findById(id);
        }
        return costList.get(0);
    }

    @Override
    public List<Cost> queryAll() {
        return costDao.findAll();
    }

    @Override
    public boolean delete(int id) {
        if (costDao.deleteCost(id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateById(Cost cost) {
        if (costDao.updateCost(cost)){
            return true;
        }
        return false;
    }

    @Override
    public boolean insert(Cost cost) {
        if (costDao.insertCost(cost)) {
            return true;
        }
        return false;
    }


}
