package com.ml.json.interceptor.builder;

import com.ml.json.interceptor.JsonInterceptor;
import com.google.common.base.Preconditions;

import org.apache.flume.Context;
import org.apache.flume.interceptor.Interceptor;

public class JsonBuilder implements Interceptor.Builder {

    private String jsonFormat = null;
    private String jsonProp = null;

    @Override
    public Interceptor build() {
        return new JsonInterceptor(jsonFormat, jsonProp);
    }

    @Override
    public void configure(Context context) {
        jsonFormat = context.getString(JsonInterceptor.Constants.CONFIG_JSON_FORMAT);
        Preconditions.checkState(jsonFormat != null && !jsonFormat.isEmpty(),
                "JsonInterceptor jsonFormat specified is empty");
        Preconditions.checkNotNull(jsonFormat,
                "JsonInterceptor requires a jsonFormat to be specified");

        jsonProp = context.getString(JsonInterceptor.Constants.CONFIG_JSON_PROP_NAME);
        Preconditions.checkState(jsonProp != null && !jsonProp.isEmpty(),
                "JsonInterceptor jsonProp specified is empty");
        Preconditions.checkNotNull(jsonProp,
                "JsonInterceptor requires a jsonProp to be specified");
    }

}
