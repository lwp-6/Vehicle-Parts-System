package com.example.springboottest1.controller;

import com.example.springboottest1.entity.User;
import com.example.springboottest1.service.UserLoginService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/home"})
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @ApiOperation(value="跳转到注册页面resgister.html")
    @RequestMapping(value = {"/registerpage"}, method = {RequestMethod.GET})
    public String registerpage(){
        return "register";
    }

    @ApiOperation(value="跳转到不登录直接进入的界面t_system.html")
    @RequestMapping(value={"/t_system"}, method = {RequestMethod.GET})
    public String t_systempage(){
        return "t_system";
    }

    @ApiOperation(value="跳转到买家页面buy_system.html")
    @RequestMapping(value={"/buy_system"}, method = {RequestMethod.GET})
    public String b_system(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("session_user");
        if(user.getKind().equals("买家"))
        {
            return "buy_system";
        }
        return "index";
    }
    @ApiOperation(value="跳转到卖家页面sold_system.html")
    @RequestMapping(value={"/sold_system"}, method = {RequestMethod.GET})
    public String s_system(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("session_user");
        if(user.getKind().equals("卖家"))
        {return "sold_system";
        }
        return "0";
    }

    @ApiOperation(value="跳转到修改密码页面updatepassword.html")
    @RequestMapping(value={"/update_system"}, method = {RequestMethod.GET})
    public String update_system()
    {
        return "updatepassword";
    }

    @ApiOperation(value="用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "String", example = "Apple"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String", example = "123")
    })
    @RequestMapping(value={"/ulogin"}, method = {RequestMethod.GET})
    @ResponseBody
    public String userLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpServletRequest request){


        User user = userLoginService.userLogin(username,password);

        if(user != null)
        {
            //登录成功
            String k = user.getKind();
            request.getSession().setAttribute("session_user",user);

            if(k.equals("卖家"))
            {
                return "{\"state\":\"sold_system\"}";
            }
            else if(k.equals("买家"))
            {
                return "{\"state\":\"buy_system\"}";
            }
        }
        return "{\"state\":\"wrong\"}";
    }

    @ApiOperation(value = "注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "company", value = "公司名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "kind", value = "用户类型", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password2", value = "确认密码", required = true, dataType = "String"),
    })
    @ResponseBody
    @RequestMapping(value = {"/uregister"}, method = {RequestMethod.GET})
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("company") String company,
                          @RequestParam("kind") String kind,
                          @RequestParam("password") String password,
                          @RequestParam("password2") String password2

                            )
    {

        if(!password.equals(password2))
        {
            return "{\"state\":\"wrong\"}";
        }
        else {
            User u = userLoginService.userSearch(username);
            if(u != null)
            {
                return "{\"state\":\"namecp\"}";
            }
            int res = userLoginService.adduser(username,password,company, kind);
            if(res == 0)
            {
                return "{\"state\":\"fail\"}";
            }
            else {
                return "{\"state\":\"success\"}";
            }
        }

    }

    @ApiOperation(value = "修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "password1", value = "原密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password2", value = "新密码", required = true, dataType = "String"),
    })
    @ResponseBody
    @RequestMapping(value={"/updatepassword"}, method = {RequestMethod.GET})
    public String updatepassword(
            @RequestParam("password1")String password1,
            @RequestParam("password2")String password2,
            HttpServletRequest request)
    {

        User user = (User)request.getSession().getAttribute("session_user");
        String username = user.getUsername();
        if(user.getPassword().equals(password1))
        {
            int i = userLoginService.updatepassword(username, password2);
            if(i == 0)
            {
                return "{\"state\":\"fail\"}";
            }
            else {
                User nuser = userLoginService.userSearch(username);
                request.getSession().setAttribute("session_user", nuser);
                return "{\"state\":\"success\"}";
            }
        }
        else
        {
            return "{\"state\":\"wrong\"}";
        }
    }

}
