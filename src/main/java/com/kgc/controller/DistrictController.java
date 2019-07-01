package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.District;
import com.kgc.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class DistrictController {

    @Autowired
    private IDistrictService districtService;



    @RequestMapping("getDistrict")
    @ResponseBody
    public Map<String,Object> getDistrict(Integer page, Integer rows){
        PageInfo<District> pageInfo = districtService.getDistrictBypage(page,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }


    @RequestMapping("addDistrict")
    @ResponseBody
    public String addDistrict(District district){
        int temp = districtService.addDistrict(district);
        return "{\"result\":"+temp+"}";
    }


    @RequestMapping("upDistrict")
    @ResponseBody
    public String upDistrict(District district){

        int temp = districtService.upDistrict(district);
        return  "{\"result\":"+temp+"}";
    }



    @RequestMapping("delDistrict")
    @ResponseBody
    public String delDistrict(Integer id){
        //调用业务
        int temp=districtService.deleteDistrict(id);
        return "{\"result\":"+temp+"}";
    }

    /**
     *
     * 批量删除的控制器
     */
    @RequestMapping("delMoreDistrict")
    @ResponseBody
    public  String delMoreDistrict(String ids){
        //将字符串转化为整数数组
        String [] arys=ids.split(",");
        Integer[] id=new Integer[arys.length];
        for(int i=0;i<arys.length;i++){
            id[i]=Integer.parseInt(arys[i]);
        }
        //调用业务
        int temp=districtService.deleteMoreDistrict(id);
        return "{\"result\":"+temp+"}";
    }
}
