package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.Type;
import com.kgc.entity.TypeExample;
import com.kgc.mapper.TypeMapper;
import com.kgc.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private TypeMapper TypeMapper;


    @Override
    public PageInfo<Type> getTypeBypage(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        TypeExample example = new TypeExample();
        List<Type> list = TypeMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public int addType(Type type) {
     return TypeMapper.insertSelective(type);

    }

    @Override
    public int upType(Type type) {
        return TypeMapper.updateByPrimaryKeySelective(type);
    }

    @Transactional
    @Override
    public int deleteType(Integer id) {
        return TypeMapper.deleteByPrimaryKey(id);

    }

    @Override
    public int deleteMoreType(Integer[] ids) {
        return TypeMapper.deleteMoreType(ids);
    }

    @Override
    public List<Type> getAllType() {

        return TypeMapper.selectByExample(new TypeExample());
    }

}
