package com.example.springboottest1.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="user", description="用户")
public class User {

    @ApiModelProperty(value="用户id",name="id",example="1")
    private String id;

    @ApiModelProperty(value="用户名",name="username",example="Apple")
    private String username;

    @ApiModelProperty(value="密码",name="password",example="123")
    private String password;

    @ApiModelProperty(value="用户公司",name="company",example="Apple")
    private String company;

    @ApiModelProperty(value="用户类型",name="kind",example="卖家")
    private String kind;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getCompany(){return company;}
    public void setCompany(String company){this.company = company;}
    public String getKind(){return kind;}
    public void setKind(String kind){this.kind = kind;}
}