package com.itheima.reggie_takeout.utils;

/**
 * 基于ThreadLocal封装的工具类，用于保存和获取当前登陆用户的id
 *
 * @author purpoas
 * @date 2022/4/18 16:52
 */
public class BaseContext {
    private static final ThreadLocal<Long> threadLocal= new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

}
