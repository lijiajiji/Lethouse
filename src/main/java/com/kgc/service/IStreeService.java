package com.kgc.service;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.Street;

import java.util.List;

public interface IStreeService {
    //通过区域显示街道
    PageInfo<Street> getStreetByDistrict(Integer page,Integer pageSize,Integer districtId);

    //
    List<Street> getStreetByDistrict(Integer districtId);
}
