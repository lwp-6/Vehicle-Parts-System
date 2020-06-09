package com.example.springboottest1.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="order", description="订单")
public class Order {
    @ApiModelProperty(value="订单id",name="id",example="1")
    private int id;

    @ApiModelProperty(value="零件名",name="partsname",example="gps")
    private String partsname;

    @ApiModelProperty(value="购买数量",name="quantity",example="50")
    private int quantity;

    @ApiModelProperty(value="单个零件价格",name="price",example="price")
    private double price;

    @ApiModelProperty(value="买家名",name="user_buy",example="Boy")
    private String user_buy;

    @ApiModelProperty(value="卖家名",name="user_sold",example="Dog")
    private String user_sold;

    public int getQuantity() {
        return quantity;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getPartsname() {
        return partsname;
    }

    public String getUser_buy() {
        return user_buy;
    }

    public String getUser_sold() {
        return user_sold;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPartsname(String partsname) {
        this.partsname = partsname;
    }

    public void setUser_buy(String user_buy) {
        this.user_buy = user_buy;
    }

    public void setUser_sold(String user_sold) {
        this.user_sold = user_sold;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"partsname\":\"" + partsname + '\"' +
                ", \"quantity\":" + quantity +
                ", \"price\":" + price +
                ", \"user_buy\":\"" + user_buy + '\"' +
                ", \"user_sold\":\"" + user_sold + '\"' +
                '}';
    }
}
