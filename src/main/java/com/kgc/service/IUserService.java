package com.kgc.service;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.UsersCondition;
import com.kgc.entity.Users;

public interface IUserService  {

   /**
     * 实现分页查询
     *
*/
    public PageInfo<Users> getUsersBypage(UsersCondition userCondition);

/** 实现修改
     * */
    public int upUsers(Users users);

/**
     *实现删除*/
    public int deleteUsers(Integer id);

  /**
     * 实现批量删除
     */
    public int deleteMoreUsers(Integer[] ids);

    /**
     * 实现用户登陆
     */

    public int checkUname(String username);

    /**
     *实现注册功能
     *
     */
    public int addUser(Users users);

    /**
     * 实现登陆功能
     * @param username
     * @param password
     * @param is    0 reg user  1 admin user
     * @return
     */
    public Users login(String username,String password,Integer is);
}


