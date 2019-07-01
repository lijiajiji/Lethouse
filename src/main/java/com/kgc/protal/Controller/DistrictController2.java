package com.kgc.protal.Controller;

import com.kgc.entity.District;
import com.kgc.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/page/")
public class DistrictController2 {

    @Autowired
    private IDistrictService districtService;



    @RequestMapping("getDistricts")
    @ResponseBody
    public List<District> getDistricts(){
        return  districtService.getAllDistrict();
    }

}
