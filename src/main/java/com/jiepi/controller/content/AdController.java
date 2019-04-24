package com.jiepi.controller.content;

import com.jiepi.bean.Ad;
import com.jiepi.constant.PageCodeEnum;
import com.jiepi.dto.AdDto;
import com.jiepi.service.AdService;
import com.jiepi.util.FileUtiles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/ad")
public class AdController {
    @Autowired
    private AdService adService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Ad add(AdDto adDto) {
        boolean b = adService.add(adDto);
        Ad ad = new Ad();
        BeanUtils.copyProperties(adDto, ad);
        return ad;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public int remove(@RequestParam String id) {
        return adService.remove(id);
    }

    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public AdDto find(@RequestParam String id){
        AdDto adDto = adService.find(id);
        return adDto;
    }
}
