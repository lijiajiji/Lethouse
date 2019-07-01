package com.kgc.mapper;

import com.kgc.entity.Users;
import com.kgc.entity.UsersExample;
import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);


    //实现批量删除  //传list集合或者数组可以
    int deleteMoreUsers(Integer [] ids);
}
