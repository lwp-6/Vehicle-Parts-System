package com.example.springboottest1.controller;

import com.example.springboottest1.entity.Parts;
import com.example.springboottest1.entity.User;
import com.example.springboottest1.service.PartsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = {"/home"})
public class SearchPartsController {
    @Autowired
    private PartsService partsService;



    @ApiOperation(value="根据用户名搜索零件")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "String")
    })
    @ResponseBody
    @RequestMapping(value={"/partsSearch_username"}, method = {RequestMethod.GET})
    public String partsSearchbyusername(@RequestParam("username") String username,
                                        HttpServletRequest request){
        List<Parts> parts = partsService.partsSearchbyusername(username);
        return parts.toString();
    }

    @ApiOperation(value="根据零件名搜索零件")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "partsname", value = "零件名", required = true, dataType = "String")
    })
    @ResponseBody
    @RequestMapping(value = {"/partsSearch"}, method = {RequestMethod.GET})
    public String partsSearchbyname(@RequestParam("partsname") String partsname,
                            HttpServletRequest request){
        List<Parts> parts = partsService.partsSearchbyname(partsname);
        //return parts.toString();
        //request.getSession().setAttribute("session_parts", "parts.toString");
        return parts.toString();
    }

    @ApiOperation(value="增加零件")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "partsname", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "quantity", value = "数量", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "price", value = "价格", required = true, dataType = "Double"),
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "information", value = "零件信息", required = true, dataType = "String")
    })
    @ResponseBody
    @RequestMapping(value = {"/addparts"}, method = {RequestMethod.GET})
    public String addParts(@RequestParam("partsname") String partsname,
                          @RequestParam("quantity") int quantity,
                          @RequestParam("price") double price,
                          @RequestParam("username") String username,
                           @RequestParam("information") String information
    ) {
        int res = partsService.addparts(partsname, quantity, price, username, information);
        if (res == 0) {
            return "{\"state\":\"fail\"}";
        } else {
            return "{\"state\":\"success\"}";
        }
    }

    @ApiOperation(value="删除零件")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "partsid", value = "零件编号", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "String")
    })
    @ResponseBody
    @RequestMapping(value = {"/deleteparts"}, method = {RequestMethod.GET})
    public String deleteParts(@RequestParam("partsid") int partsid,
                           @RequestParam("username") String username
    ) {
        Parts parts = partsService.partsSearchbyid(partsid);
        if(!parts.getUsername().equals(username))  //判断是否有权限删除
        {
            return "{\"state\":\"wrong\"}";
        }
        int res = partsService.deleteparts(partsid);

        if (res == 0) {
            return "{\"state\":\"fail\"}";
        } else {
            return "{\"state\":\"success\"}";
        }
    }

    @ApiOperation(value="修改零件信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "零件编号", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "partsname", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "quantity", value = "数量", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "price", value = "价格", required = true, dataType = "Double"),
            @ApiImplicitParam(paramType = "query", name = "information", value = "零件信息", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "String")
    })
    @RequestMapping(value={"/updateparts"}, method = {RequestMethod.GET})
    @ResponseBody
    public String updateparts(@RequestParam("id")int id,
                           @RequestParam("partsname") String partsname,
                           @RequestParam("quantity") int quantity,
                           @RequestParam("price") double price,
                           @RequestParam("information") String information,
                              @RequestParam("username") String username
                               ){
        Parts parts = partsService.partsSearchbyid(id);
        if(parts.getUsername().equals(username))  //判断是否有权限修改
        {
            return "{\"state\":\"" + partsService.updateparts(id, partsname, quantity, price, information) + "\"}";
        }
        else
        {
            return "{\"state\":\"fail\"}";
        }
    }

    @ApiOperation(value="搜索所有零件")
    @RequestMapping(value={"/partsSearchall"}, method = {RequestMethod.GET})
    @ResponseBody
    public List<Parts> partsSearchall()
    {
        return partsService.partsSearchall();
    }
}
