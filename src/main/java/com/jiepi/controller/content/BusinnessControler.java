package com.jiepi.controller.content;

import com.jiepi.bean.Business;
import com.jiepi.dto.BusinessDto;
import com.jiepi.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@Controller
@RestController
@RequestMapping("/businesses")
public class BusinnessControler {

    @Autowired
    private BusinessService businessService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Boolean add(BusinessDto businessDto) {
        return businessService.insert(businessDto);
    }

    @RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
    public BusinessDto searchById(@PathVariable Long id) {
        BusinessDto business = businessService.searchById(id);
        return business;
    }
    @RequestMapping(value = "/search/page", method = RequestMethod.POST)
    public List<BusinessDto> searchPageById(@RequestBody BusinessDto businessDto) {
        List<BusinessDto> businessDtos = businessService.searchByPage(businessDto);
        return businessDtos;
    }
}
