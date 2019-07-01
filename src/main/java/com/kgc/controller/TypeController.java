package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.Type;
import com.kgc.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class TypeController {

    @Autowired
    private ITypeService typeService;



    @RequestMapping("getType")
    @ResponseBody
    public Map<String,Object> getType(Integer page, Integer rows){
        PageInfo<Type> pageInfo = typeService.getTypeBypage(page, rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }


    @RequestMapping("addType")
    @ResponseBody
    public String addType(Type type){
        int temp = typeService.addType(type);
        return "{\"result\":"+temp+"}";
    }


    @RequestMapping("upType")
    @ResponseBody
    public String upType(Type type){

        int temp = typeService.upType(type);
        return  "{\"result\":"+temp+"}";
    }



    @RequestMapping("delType")
    @ResponseBody
    public String delType(Integer id){
        //调用业务
        int temp=typeService.deleteType(id);
        return "{\"result\":"+temp+"}";
    }

    /**
     *
     * 批量删除的控制器
     */
    @RequestMapping("delMoreType")
    @ResponseBody
    public  String delMoreType(String ids){
        //将字符串转化为整数数组
        String [] arys=ids.split(",");
        Integer[] id=new Integer[arys.length];
        for(int i=0;i<arys.length;i++){
            id[i]=Integer.parseInt(arys[i]);
        }
        //调用业务
        int temp=typeService.deleteMoreType(id);
        return "{\"result\":"+temp+"}";
    }
}
