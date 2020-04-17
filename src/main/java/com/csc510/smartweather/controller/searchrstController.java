package com.csc510.smartweather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class searchrstController {
    @ResponseBody
    @RequestMapping("/searchrst")
    public String rstrtn(HttpServletRequest request) {
        String searchrst = request.getParameter("Searchforplace");
        System.out.println(searchrst);
        return searchrst;
    }
}
