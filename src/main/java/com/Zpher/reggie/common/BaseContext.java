package com.Zpher.reggie.common;

/**
 * ClassName: BaseContext
 * Package: com.Zpher.reggie.common
 * Description:基于ThreadLocal封装工具类，用户保存和获取当前登录用户的id
 *
 * @Author WHU-PeterZhang
 * @Create 2024/8/8 20:39
 * @Version 1.0
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
