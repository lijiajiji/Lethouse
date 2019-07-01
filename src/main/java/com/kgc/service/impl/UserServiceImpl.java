package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.Users;
import com.kgc.entity.UsersCondition;
import com.kgc.entity.UsersExample;
import com.kgc.service.IUserService;
import com.kgc.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private com.kgc.mapper.UsersMapper UsersMapper;


    @Override
    public PageInfo<Users> getUsersBypage(UsersCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        UsersExample example = new UsersExample();
        //添加条件
        UsersExample.Criteria criteria=example.createCriteria();
        criteria.andIsadminEqualTo(new Integer(0));//表示管理员
        //添加查询条件
        if(condition.getTelephone()!=null){
            criteria.andTelephoneLike("%"+condition.getTelephone()+"%");
        }
        if(condition.getStartAge()!=null){
            criteria.andAgeGreaterThan(condition.getStartAge());
        }
        if(condition.getEndAge()!=null){
            criteria.andAgeLessThan(condition.getEndAge());
        }
        List<Users> list = UsersMapper.selectByExample(example);
        return new PageInfo<>(list);
    }


    @Override
    public int upUsers(Users sers) {
        return UsersMapper.updateByPrimaryKeySelective(sers);
    }

    @Transactional
    @Override
    public int deleteUsers(Integer id) {
        return UsersMapper.deleteByPrimaryKey(id);

    }

    @Override
    public int deleteMoreUsers(Integer[] ids) {
        return UsersMapper.deleteMoreUsers(ids);
    }



    @Override
    public int checkUname(String username) {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(username);
        criteria.andIsadminEqualTo(0);//注册用户
        List<Users> users = UsersMapper.selectByExample(example);
        return users.size()==0?0:1;
    }

    @Override
    public int addUser(Users users) {
        //设置为前台注册用户
        users.setIsadmin(0);
        //对用户的密码使用MD5加密
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        return UsersMapper.insertSelective(users);
    }

    @Override
    public Users login(String username, String password,Integer is) {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andIsadminEqualTo(is);
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> users = UsersMapper.selectByExample(example);
        if (users.size() == 1) {
            return users.get(0);
        }
            return null;
        }
    }

