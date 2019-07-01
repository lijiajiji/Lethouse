package com.kgc.protal.Controller;

import com.kgc.entity.Type;
import com.kgc.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "TypeController2")
@RequestMapping("/page/")
public class TypeController {

    @Autowired
    private ITypeService typeService;



    @RequestMapping("getTypes")
    @ResponseBody
    public List<Type> getTypes(){
         return  typeService.getAllType();
    }


}
