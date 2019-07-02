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

             return "login";
         }else {
             return "error";
         }
     }

    /**
     *实现登入功能
     */
    @RequestMapping("login")
    public String login(String inputCode,String username, String password, HttpSession session, Model model){
        //比较验证码
        //获取手机验证码
        String code=session.getAttribute("code").toString();
        if(code.equals(inputCode)){
            //调用业务
            Users user=userService.login(username,password,1);
            if(user==null) {
                model.addAttribute("info","用户名密码错误!");
                return "login";  //继续登入
            }
            else {
                //只要登入:使用session或者cookie保存登入的信息
                session.setAttribute("user",user);
                session.setMaxInactiveInterval(600); //30秒
                return "redirect:getUserHouse";  //用户中心的管理页
            }
        }else{
            model.addAttribute("info","验证码错语!眼神不好使就医");
            return "login";  //用户中心的管理页
        }

    }

    }






