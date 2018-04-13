package com.xisheng.form;

import lombok.Data;

/**
 * Created by fanlinglong on 2018/4/13.
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
