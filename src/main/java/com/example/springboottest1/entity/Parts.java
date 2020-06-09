package com.example.springboottest1.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="parts", description="零件")
public class Parts {
    @ApiModelProperty(value="零件名",name="partsname",example="发动机")
    private String partsname;

    @ApiModelProperty(value="库存",name="quantity",example="2000")
    private int quantity;

    @ApiModelProperty(value="价格",name="price",example="1800")
    private Double price;

    @ApiModelProperty(value="卖家名",name="username",example="Apple")
    private String username;

    @ApiModelProperty(value="零件信息",name="information",example="1.5L发动机")
    private String information;

    @ApiModelProperty(value = "零件id",name="id",example = "1")
    private int id;

    public int getid()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return partsname;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUsername() {
        return username;
    }

    public Double getPrice() {
        return price;
    }
    public String getInformation()
    {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setName(String partsname) {
        this.partsname = partsname;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"partsname\":\"" + partsname + '\"' +
                ", \"quantity\":" + quantity +
                ", \"price\":" + price +
                ", \"username\":\"" + username + '\"' +
                ",\"information\":\"" + information + '\"'+
                '}';
    }
}
