package com.itheima.reggie_takeout.utils;

/**
 * 自定义业务异常
 * @author purpoas
 * @date 2022/4/19 14:18
 */

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
