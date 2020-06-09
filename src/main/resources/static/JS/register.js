function regist() { //注册
    var username = document.getElementById("u1").value;
    var password = document.getElementById("p1").value;
    var company = document.getElementById("c1").value;
    var kind = document.getElementById("k1").value;
    var password2 = document.getElementById("p2").value;

    if(username=="")
    {
        alert("用户名不能为空");
    }
    else if(kind=="")
    {
        alert("请选择注册类型（买家/卖家）");
    }
    else if (password=="")
    {
        alert("密码不能为空");
    }
    else if(password2=="")
    {
        alert("确认密码不能为空");
    }
    else
    {
        $.getJSON("/home/uregister", {username:username, company:company, kind:kind, password:password, password2:password2}, function(result)
        {
            judge(result);
        });
    }
}
function judge(result){//注册判断

    var state = result.state;
    state.toString();
    if(state == "wrong")
    {
        alert("前后两次密码不同");
    }
    else if(state == "fail")
    {
        alert("注册失败");
    }
    else if(state == "namecp")
    {
        alert("已存在的用户名");
    }
    else
    {
        alert("注册成功");
        window.history.go(-1);
    }
}