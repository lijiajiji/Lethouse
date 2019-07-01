package com.kgc.mapper;

import com.kgc.entity.District;
import com.kgc.entity.DistrictExample;
import java.util.List;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    //已经封装好了查询的方法
    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    //实现批量删除  //传list集合或者数组可以
    int deleteMoreDistrict(Integer [] ids);



}