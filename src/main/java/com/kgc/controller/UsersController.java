package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.Users;
import com.kgc.entity.UsersCondition;
import com.kgc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class UsersController {
    @Autowired
    private IUserService userService;


    @RequestMapping("/getUsers")
    @ResponseBody
    public Map<String,Object> getUsers(UsersCondition condition){
        PageInfo<Users> pageInfo = userService.getUsersBypage(condition);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }





    @RequestMapping("/upUsers")
    @ResponseBody
    public String upUsers(Users users){
        int pemt= userService.upUsers(users);
        return "{\"result\":"+pemt+"}";
    }



    @RequestMapping("/delUsers")
    @ResponseBody
    public String delUsers(Integer id){
        //调用业务
        int temp=userService.deleteUsers(id);
        return "{\"result\":"+temp+"}";
    }


/**
     *
     * 批量删除的控制器
*/



    @RequestMapping("/delMoreUsers")
    @ResponseBody
    public  String delMoreUsers(String ids){
        //将字符串转化为整数数组
        String [] arys=ids.split(",");
        Integer[] id=new Integer[arys.length];
        for(int i=0;i<arys.length;i++){
            id[i]=Integer.parseInt(arys[i]);
        }
        //调用业务
        int temp=userService.deleteMoreUsers(id);
        return "{\"result\":"+temp+"}";
    }

    /**
     * 实现登陆名字查询功能
     */

    @RequestMapping("/checkUsers")
    @ResponseBody
    public String checkUsers(String username){
         int temp =userService.checkUname(username);
         return "{\"result\":"+temp+"}";

    }


    /**
     *
     *实现注册功能
     * */
     @RequestMapping("/regUsers")
    public String regUsers(Users users){
         int temp=userService.addUser(users);
         if(temp>0){

             return "logins";
         }else {
             return "error";
         }
     }

    /**
     *实现登入功能
     */
    @RequestMapping("logins")
    public String login(String username, String password, HttpSession session, Model model){
        Users users=userService.login(username,password,1);
        if(users==null){
            model.addAttribute("info","用户密码错误！");
            return "logins";
        }else {
            session.setAttribute("user",users);
            session.setMaxInactiveInterval(30);
             return "admin" ;
        }

    }





}
