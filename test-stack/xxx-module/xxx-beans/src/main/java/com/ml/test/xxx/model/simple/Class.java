package com.ml.test.xxx.model.simple;

import java.io.Serializable;

/**
 * Created by mal on 2019/5/23.
 */
public class Class implements Serializable {


    private Long id;

    private String className;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
