package com.jiepi.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.awt.SunHints;

@Controller
@RequestMapping(value = "/index")
public class IndexController {
   @RequestMapping
   public String init(){
       return  "/system/index";
   }
}
