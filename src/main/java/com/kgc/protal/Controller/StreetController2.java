package com.kgc.protal.Controller;

import com.kgc.entity.Street;
import com.kgc.service.IStreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/page/")
public class StreetController2 {

    @Autowired
    private IStreeService service;




    @RequestMapping("GetStreetByDid2")
    @ResponseBody
    public List<Street>GetStreetByDid2(Integer did){
        return service.getStreetByDistrict(did);


    }


   
}
