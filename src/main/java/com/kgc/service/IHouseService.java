package com.kgc.service;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.House;
import com.kgc.entity.HouseCodition;

public interface IHouseService {
    //添加出租房
    public int addHouse(House house);


    public PageInfo<House> getUsersHouseByPage(Integer page,Integer rows,Integer uid);


    public House getHouse(String id);

    //修改出租房
    public int updateHouse(House house);

    //删除出租房
    public int  delHouse(String id);

    //增加审核
    public PageInfo<House> getHouseByState(Integer page,Integer rows,Integer state);


    //审核出租房
    public int passHouse(String id);


    public PageInfo<House> getHouseByBrowser(HouseCodition codition);

}
