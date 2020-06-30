package com.ml.test.base.response;

/**
 * Created by mal on 2019/5/22.
 */
public class ApiResponse<T> {

    public static final int SUCCESS = 200;

    public static final int FAIL = 500;

    /**
     * 200:表示请求正常结束
     * 500:表示程序执行发生异常
     * 其它:表示业务行check
     * */
    private Integer code = SUCCESS;

    private String msg = "";

    private T payload;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

}
