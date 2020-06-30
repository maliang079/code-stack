package com.ml.test.base.exception;

/**
 * Created by mal on 2019/5/29.
 */
public class Exceptions {

    public static void business(int code, Object ... args) {
        throw new BusinessException(code, args);
    }

    public static void rollback(String message) {
        throw new RollbackException(message);
    }

}
