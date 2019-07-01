package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.House;
import com.kgc.entity.HouseCodition;
import com.kgc.mapper.HouseMapper;
import com.kgc.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements IHouseService {

    @Autowired
    private HouseMapper houseMapper;

    @Override
    public int addHouse(House house) {

        return houseMapper.insertSelective(house);


    }

    @Override
    public PageInfo<House> getUsersHouseByPage(Integer page, Integer rows, Integer uid) {
        PageHelper.startPage(page,rows);
        List<House> list = houseMapper.selectHouseByUsersId(uid);
        return new PageInfo<House>(list);
    }

    @Override
    public House getHouse(String id) {
        return houseMapper.getHouseAndDid(id);
    }

    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int delHouse(String id) {
        House house=new House();
        house.setId(id);
        house.setIsdel(new Integer(1));
        return houseMapper.updateByPrimaryKeySelective(house);

    }

    @Override
    public PageInfo<House> getHouseByState(Integer page, Integer rows, Integer state) {
        PageHelper.startPage(page,rows);
        List<House> list = houseMapper.getHouseByState(0);
        return new PageInfo<House>(list);
    }

    @Override
    public int passHouse(String id) {
        House house=new House();
        house.setId(id);
        house.setIspass(1);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getHouseByBrowser(HouseCodition codition) {
        PageHelper.startPage(codition.getPage(),codition.getPageSize());
        //调用业务 example 只用实现表单条件搜索查询
        List<House>list=houseMapper.getHouseByBrowser(codition);
        PageInfo<House> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
