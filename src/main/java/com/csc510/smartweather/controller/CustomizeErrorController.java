package com.csc510.smartweather.controller;

import com.csc510.smartweather.enumerate.CustomizeExceptionEnum;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Heng Yu
 * @date 4/14/20 3:19 PM
 */

@Controller
public class CustomizeErrorController implements ErrorController {
    @GetMapping("/error")
    public String handle(HttpServletRequest request, Model model) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode < 500) {
            model.addAttribute("message", CustomizeExceptionEnum.BAD_REQUEST.getMessage());
        } else {
            model.addAttribute("message", CustomizeExceptionEnum.SERVER_ERROR.getMessage());
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "error";
    }
}
