package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.House;
import com.kgc.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class HouseController2 {


    @Autowired
    private IHouseService houseService;


    @RequestMapping("getHouseByNoPass")
    @ResponseBody
    public Map<String,Object> getHouseByNoPass(Integer page,Integer rows){
        PageInfo<House>pageInfo=houseService.getUsersHouseByPage(page,rows,0);
        Map<String,Object>map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;


    }


    @RequestMapping("getHouseByYesPass")
    @ResponseBody
    public Map<String,Object> getHouseByYesPass(Integer page,Integer rows){
        PageInfo<House>pageInfo=houseService.getUsersHouseByPage(page,rows,1);
        Map<String,Object>map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }




    @RequestMapping("passHouse")
    @ResponseBody
    public Map<String,Object> passsHouse(String id){
        int temp=houseService.passHouse(id);
        Map<String,Object>map = new HashMap<>();
        map.put("result",temp);
        return map;
    }
}
