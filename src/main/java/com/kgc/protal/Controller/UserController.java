package com.kgc.protal.Controller;

import com.kgc.entity.Users;
import com.kgc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/")
public class UserController {


    @Autowired
    private IUserService userService;



    /**
     * 实现用户的登陆功能
     */
    @RequestMapping("checkUname")
    @ResponseBody
    public String checkUname(String username){
        //调用业务
        int temp=userService.checkUname(username);
        return "{\"result\":"+temp+"}";

    }


    @RequestMapping("regUser")
    public String regUser(Users users) {
        //调用业务层
        int temp = userService.addUser(users);
        if (temp > 0) {

            return "login";//继续登入
        } else {
            return "error";//用户中心的管理页
        }
    }


        @RequestMapping("login")
        public String login(String username, String password, Model model,HttpSession session){
            //调用业务层
            Users user=userService.login(username,password,0);
            if(user==null){
                model.addAttribute("info","用户名密码错误！");
                return "login";//继续登入
            }
            else {

                //只要登入：使用sesseion或者cookie保存登入的信息
                session.setAttribute("user",user);
                session.setMaxInactiveInterval(30);//30秒
                return "redirect:getUserHouse";//用户中心的管理页
            }
    }
}
