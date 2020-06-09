package com.example.springboottest1.controller;

import com.example.springboottest1.entity.Order;
import com.example.springboottest1.entity.Parts;
import com.example.springboottest1.entity.User;
import com.example.springboottest1.service.OrderService;
import com.example.springboottest1.service.PartsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = {"/home"})
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PartsService partsService;

    @ApiOperation(value="买家用户名找订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "String")
    })
    @ResponseBody
    @RequestMapping(value={"/orderSearchbyuser_buy"}, method = {RequestMethod.GET})
    public String orderSearchbyuser_buy(@RequestParam("username") String username){
        List<Order> order = orderService.orderSearchbyuser_buy(username);
        return order.toString();
    }

    @ApiOperation(value="卖家用户名找订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "String")
    })
    @ResponseBody
    @RequestMapping(value={"/orderSearchbyuser_sold"}, method = {RequestMethod.GET})
    public String orderSearchbyuser_sold(@RequestParam("username") String username){
        List<Order> order = orderService.orderSearchbyuser_sold(username);
        return order.toString();
    }

    @ApiOperation(value="添加订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "零件编号", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "quantity", value = "零件数量", required = true, dataType = "int")
    })
    @ResponseBody
    @RequestMapping(value={"/addorder"}, method = {RequestMethod.GET})
    public String addorder(@RequestParam("id") int id,
                        @RequestParam("quantity") int quantity,
                           HttpServletRequest request){
        Parts parts = partsService.partsSearchbyid(id);
        User user = (User)request.getSession().getAttribute("session_user");
        if(quantity > parts.getQuantity()) //判断库存是否充足
        {
            return "{\"state\":\"not enough\"}";
        }
        parts.setQuantity(parts.getQuantity()-quantity); //修改库存量
        partsService.updateparts(id, parts.getName(), parts.getQuantity(), parts.getPrice(), parts.getInformation());
        return "{\"state\":\"" + orderService.addorder(parts.getName(), quantity, parts.getPrice(), user.getUsername(), parts.getUsername()) + "\"}";
    }
}

