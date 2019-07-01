package com.kgc.service;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.District;

import java.util.List;

public interface IDistrictService {
    /**
     * 实现分页查询
     * */

    public PageInfo<District> getDistrictBypage(Integer page, Integer pageSize);


    /**
     * 实现新增
     * */
    public int addDistrict(District district);


    /**
     * 实现修改
     * */
    public int upDistrict(District district);


    /**
     *实现删除*/
    public int deleteDistrict(Integer id);

    /**
     * 实现批量删除
     */
    public int deleteMoreDistrict(Integer[] ids);

    /***
     *
     */
    List<District> getAllDistrict();
}
