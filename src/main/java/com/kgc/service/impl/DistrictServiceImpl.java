package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.District;
import com.kgc.entity.DistrictExample;
import com.kgc.mapper.DistrictMapper;
import com.kgc.mapper.StreetMapper;
import com.kgc.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;

    @Override
    public PageInfo<District> getDistrictBypage(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        DistrictExample example = new DistrictExample();
        List<District> list = districtMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public int addDistrict(District district) {
     return districtMapper.insertSelective(district);

    }

    @Override
    public int upDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Transactional
    @Override
    public int deleteDistrict(Integer id) {
        try {
            //删除街道
            streetMapper.deleltstreetByDid(id);
            //删除区域
            districtMapper.deleteByPrimaryKey(id);
            return 1;
        }catch (Exception e){
            return 0;
        }

    }

    @Override
    public int deleteMoreDistrict(Integer[] ids) {
        return districtMapper.deleteMoreDistrict(ids);
    }

    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }

}
