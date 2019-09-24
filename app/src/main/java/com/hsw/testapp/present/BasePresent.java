package com.hsw.testapp.present;

/**
 * @author heshuai
 * created on: 2019-09-24 14:12
 * description:
 */
public abstract class BasePresent {
    /**
     * 用于页面结束 取消rx注册
     */
    public abstract void dispose();

    /**
     * 用于防止重复调用接口
     */
    boolean isLoading;
}
