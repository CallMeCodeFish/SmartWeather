package com.csc510.smartweather.exception;

import com.csc510.smartweather.enumerate.CustomizeExceptionEnum;

/**
 * @author Heng Yu
 * @date 4/14/20 12:32 PM
 */


public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizeException(CustomizeExceptionEnum cee) {
        this.message = cee.getMessage();
        this.code = cee.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
