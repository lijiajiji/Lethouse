package com.kgc.mapper;

import com.kgc.entity.House;
import com.kgc.entity.HouseCodition;
import com.kgc.entity.HouseExample;
import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);


    //查询出租房所有星系
    List<House> selectHouseByUsersId(Integer uid);

    //查询出租房信息(带区域id)
    House getHouseAndDid(String id);


    //审核信息
    List<House> getHouseByState(Integer state);



    //查询浏览的出租房
    List<House> getHouseByBrowser(HouseCodition houseCodition);


}