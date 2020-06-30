package com.ml.test.base.exception;

/**
 * Created by mal on 2019/5/29.
 */
public class BusinessException extends BaseException {

    private int code;

    private Object[] args;

    public BusinessException(int code, Object ... agrs) {
        this.code = code;
        this.args = args;
    }

    public int getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

}
