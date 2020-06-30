package com.ml.test.xxx.request.nb;

import lombok.Data;

/**
 * Created by mal on 2019/7/12.
 */
@Data
public class NbBaseRequest {

    private Integer pageNum;

    private Integer pageSize;

    private String orderBy;

}
