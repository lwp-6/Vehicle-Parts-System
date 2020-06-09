function login() {  //登录
    var uname = document.getElementById("ii1").value;
    var pd = document.getElementById("pp1").value;
    if(uname=="")
    {
        alert("用户名不能为空");
    }
    else if(pd=="")
    {
        alert("密码不能为空");
    }
    else
    {
        $.getJSON("/home/ulogin", {username:uname, password:pd}, function(result)
        {
            judge(result);
        });
    }
}
function judge(result){ //登录判断
    var state = result.state;
    state.toString();
    if(state == "wrong")
    {
        alert("密码错误");
    }
    else if(state == "buy_system")
    {
        window.location.href = "/home/buy_system";
    }
    else
    {
        window.location.href = "/home/sold_system";
    }
}
function updatepassword() //改密码
{
    var password1 = document.getElementById("password1").value;
    var password2 = document.getElementById("password2").value;
    if(password1 == "")
    {
        alert("输入原密码");
    }
    else if(password2 == "")
    {
        alert("输入新密码");
    }
    else
    {
        $.getJSON("/home/updatepassword", {password1:password1, password2:password2}, function(result)
        {
            var state = result.state;
            if(state == "faile")
            {
                alert("修改失败");
            }
            else if(state == "success")
            {
                alert("修改成功");
                window.history.go(-1);
            }
            else
            {
                alert("原密码错误");
            }
        });
    }
}