package com.kgc.service;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.Type;

import java.util.List;

public interface ITypeService {
    /**
     * 实现分页查询
     * */

    public PageInfo<Type> getTypeBypage(Integer page, Integer pageSize);


    /**
     * 实现新增
     * */
    public int addType(Type type);


    /**
     * 实现修改
     * */
    public int upType(Type type);


    /**
     *实现删除*/
    public int deleteType(Integer id);

    /**
     * 实现批量删除
     */
    public int deleteMoreType(Integer[] ids);

    /***
     *查询所有的类型
     * @return
     */


    List<Type> getAllType();
}
