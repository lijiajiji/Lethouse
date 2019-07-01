package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.Street;
import com.kgc.service.IStreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class StreetController {

    @Autowired
    private IStreeService service;




    @RequestMapping("GetStreetByDid")
    @ResponseBody
    public Map<String ,Object>GetStreetByDid(Integer page,Integer rows,Integer did){


       PageInfo<Street> pageInfo= service.getStreetByDistrict(page,rows,did);
       Map<String,Object>map=new HashMap<>();
       map.put("total",pageInfo.getTotal());
       map.put("rows",pageInfo.getList());
       return map;
    }



}
