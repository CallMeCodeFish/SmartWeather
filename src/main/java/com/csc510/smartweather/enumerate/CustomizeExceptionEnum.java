package com.csc510.smartweather.enumerate;

/**
 * @author Heng Yu
 * @date 4/14/20 12:40 PM
 */


public enum CustomizeExceptionEnum {
    NOT_SIGN_IN(4001, "Please sign in before accessing the following contents."),
    NOT_AUTHORIZED(4002, "Sorry, you are not allowed to access the following contents."),
    SYSTEM_ERROR(5001, "Sorry, the server cannot respond to your request!"),
    SERVER_ERROR(5002, "Sorry, there is something wrong with the server! Please try again later."),
    BAD_REQUEST(4003, "Sorry, there is something wrong with your request! Please have a check."),
    NO_LOCATION_INFO(4004, "Sorry, you have to allow us to obtain your location information before you get the sales promotion information");
    private Integer code;
    private String message;

    CustomizeExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
