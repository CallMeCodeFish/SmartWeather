package com.csc510.smartweather.advice;

import com.csc510.smartweather.enumerate.CustomizeExceptionEnum;
import com.csc510.smartweather.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Heng Yu
 * @date 4/14/20 2:26 PM
 */

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model) {
        if (e instanceof CustomizeException) {
            model.addAttribute("message", e.getMessage());
        } else {
            model.addAttribute("message", CustomizeExceptionEnum.SYSTEM_ERROR.getMessage());
        }
        return new ModelAndView("error");
    }
}
