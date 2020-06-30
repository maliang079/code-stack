package com.ml.test.xxx.service.parsing;

/**
 * Created by mal on 2019/7/5.
 */
public interface IParser<T> {

    String STARTER = "{";

    String TERMINATOR = "}";

    String SPLITTER = ":";

    T parse(String data);

    boolean isBelong(String data);

}
