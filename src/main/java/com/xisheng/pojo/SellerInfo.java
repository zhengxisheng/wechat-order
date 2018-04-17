package com.xisheng.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by fanlinglong on 2018/4/15.
 */
@Entity
@Data
public class SellerInfo {

    @Id
    private String id;

    private String username;

    private String password;

}
