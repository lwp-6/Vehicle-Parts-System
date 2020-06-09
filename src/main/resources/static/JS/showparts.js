function showp() //根据零件名显示零件信息
{
    var s = document.getElementById("input_partsname").value;
    if(s == "")
    {
        alert("请输入要查询的零件名");
    }
    else
    {
        $.getJSON("/home/partsSearch", {partsname:s}, function(result)
        {
            showparts_table(result);
        });
    }
}

function show_all() //在表格显示11个零件信息
{

    var s = document.getElementById("input_partsname").value;
    if(s=="")
    {
        $.getJSON("/home/partsSearchall", function(result)
        {
            for(var i in result)
            {
                var value = result[i].name;
                delete result[i].name;
                result[i].partsname = value;
            }
            var table = document.getElementById("output");
            var rowNum=table.rows.length;
            for (i=1;i<rowNum;i++)  //先清空当前表格
            {
                table.deleteRow(i);
                rowNum=rowNum-1;
                i=i-1;
            }
            for(var i in result)  //添加信息
            {
                var tr = document.createElement("tr");
                var th_id = document.createElement("th");
                th_id.innerHTML = result[i].id;
                var th_partsname = document.createElement("th");
                th_partsname.innerHTML = result[i].partsname;
                var th_quantity = document.createElement("th");
                th_quantity.innerHTML = result[i].quantity;
                var th_price = document.createElement("th");
                th_price.innerHTML = result[i].price;
                var th_informatino = document.createElement("th");
                th_informatino.innerHTML = result[i].information;
                var th_username = document.createElement("th");
                th_username.innerHTML = result[i].username;

                tr.appendChild(th_id);
                tr.appendChild(th_partsname);
                tr.appendChild(th_quantity);
                tr.appendChild(th_price);
                tr.appendChild(th_informatino);
                tr.appendChild(th_username);
                table.appendChild(tr);
                if(i == 10)
                {
                    break;
                }
            }
        });
    }
    else
    {
        showp();
    }
}
function showparts_table(parts_info) { //在表格中显示零件信息
    var table = document.getElementById("output");
    var rowNum=table.rows.length;
    for (i=1;i<rowNum;i++)
    {
        table.deleteRow(i);
        rowNum=rowNum-1;
        i=i-1;
    }
    for(var i in parts_info)
    {
        var tr = document.createElement("tr");
        var th_id = document.createElement("th");
        th_id.innerHTML = parts_info[i].id;
        var th_partsname = document.createElement("th");
        th_partsname.innerHTML = parts_info[i].partsname;
        var th_quantity = document.createElement("th");
        th_quantity.innerHTML = parts_info[i].quantity;
        var th_price = document.createElement("th");
        th_price.innerHTML = parts_info[i].price;
        var th_informatino = document.createElement("th");
        th_informatino.innerHTML = parts_info[i].information;
        var th_username = document.createElement("th");
        th_username.innerHTML = parts_info[i].username;

        tr.appendChild(th_id);
        tr.appendChild(th_partsname);
        tr.appendChild(th_quantity);
        tr.appendChild(th_price);
        tr.appendChild(th_informatino);
        tr.appendChild(th_username);
        table.appendChild(tr);
    }


}
function flushmy(){ //获取username的parts信息
    var username = document.getElementById("un").value;
    $.getJSON("/home/partsSearch_username", {username:username}, function(result)
    {
        showparts_table_user(result);
    });
}
function showparts_table_user(parts_info) { //在表格显示username的parts信息
    var table = document.getElementById("myparts");
    var rowNum=table.rows.length;
    for (i=1;i<rowNum;i++)
    {
        table.deleteRow(i);
        rowNum=rowNum-1;
        i=i-1;
    }
    for(var i in parts_info)
    {
        var tr = document.createElement("tr");
        var th_id=document.createElement("th");
        th_id.innerHTML = parts_info[i].id;
        var th_partsname = document.createElement("th");
        th_partsname.innerHTML = parts_info[i].partsname;
        var th_quantity = document.createElement("th");
        th_quantity.innerHTML = parts_info[i].quantity;
        var th_price = document.createElement("th");
        th_price.innerHTML = parts_info[i].price;
        var th_informatino = document.createElement("th");
        th_informatino.innerHTML = parts_info[i].information;

        tr.appendChild(th_id);
        tr.appendChild(th_partsname);
        tr.appendChild(th_quantity);
        tr.appendChild(th_price);
        tr.appendChild(th_informatino);
        table.appendChild(tr);
    }
}
function myorder() { //卖家的订单信息
    var username = document.getElementById("un").value;
    $.getJSON("/home/orderSearchbyuser_sold", {username:username}, function(result)
    {
        showorder_sold(result);
    });
    flushmy();  //刷新并显示
}
function showorder_sold(result) { //在表格显示卖家订单信息
    var table = document.getElementById("myorder");
    var rowNum=table.rows.length;
    for (i=1;i<rowNum;i++)
    {
        table.deleteRow(i);
        rowNum=rowNum-1;
        i=i-1;
    }
    for(var i in result)
    {
        var tr = document.createElement("tr");
        var th_id = document.createElement("th");
        th_id.innerHTML = result[i].id;
        var th_partsname = document.createElement("th");
        th_partsname.innerHTML = result[i].partsname;
        var th_quantity = document.createElement("th");
        th_quantity.innerHTML = result[i].quantity;
        var th_user_buy = document.createElement("th");
        th_user_buy.innerHTML = result[i].user_buy;
        var th_total = document.createElement("th");
        th_total.innerHTML = result[i].price * result[i].quantity;

        tr.appendChild(th_id);
        tr.appendChild(th_partsname);
        tr.appendChild(th_quantity);
        tr.appendChild(th_user_buy);
        tr.appendChild(th_total);
        table.appendChild(tr);
    }
}
function updateparts() {  //修改零件信息
    var id = document.getElementById("update_id").value;
    var partsname = document.getElementById("update_name").value;
    var quantity = document.getElementById("update_quantity").value;
    var price = document.getElementById("update_price").value;
    var information = document.getElementById("update_information").value;
    var username = document.getElementById("un").value;
    if(id=="" || partsname=="" || quantity=="" || price=="" || price=="" || information =="")
    {
        alert("请填写修改后的完整信息");
    }
    $.getJSON("/home/updateparts", {id:id, partsname:partsname, quantity:quantity, price:price, information:information, username:username}, function (result) {
            if (result.state == "1") {
                alert("更新成功");
            }
            else if(result.state == "fail")
            {
                alert("没有权限修改！");
            }
            else {
                alert("更新失败");
            }
        }
    )
}
function addorder()  //添加订单
{
    var id = document.getElementById("order_id").value;
    var quantity = document.getElementById("parts_quantity").value;
    if(id == "" || quantity == "")
    {
        alert("请填写购买信息");
    }

    $.getJSON("/home/addorder", {id:id, quantity:quantity}, function (result) {
        if(result.state=="1"){
            alert("提交订单成功");
        }
        else if(result.state == "not enough")
        {
            alert("库存不足");
        }
        else
        {
            alert("提交订单失败");
        }
    })

}
function showorder_buy(){
    flushorder_buy();
}
function flushorder_buy(){  //刷新订单
    var username = document.getElementById("un").value;
    var table = document.getElementById("myorder");
    var rowNum=table.rows.length;
    for (i=1;i<rowNum;i++)
    {
        table.deleteRow(i);
        rowNum=rowNum-1;
        i=i-1;
    }
    $.getJSON("/home//orderSearchbyuser_buy", {username:username}, function (result) {
        var table = document.getElementById("myorder");
        for(var i in result)
        {
            var tr = document.createElement("tr");
            var th_id = document.createElement("th");
            th_id.innerHTML = result[i].id;
            var th_partsname = document.createElement("th");
            th_partsname.innerHTML = result[i].partsname;
            var th_quantity = document.createElement("th");
            th_quantity.innerHTML = result[i].quantity;
            var th_user_sold = document.createElement("th");
            th_user_sold.innerHTML = result[i].user_sold;
            var th_total = document.createElement("th");
            th_total.innerHTML = result[i].price * result[i].quantity;

            tr.appendChild(th_id);
            tr.appendChild(th_partsname);
            tr.appendChild(th_quantity);
            tr.appendChild(th_user_sold);
            tr.appendChild(th_total);
            table.appendChild(tr);
        }
    })
}
function addparts() { //添加零件
    var partsname = document.getElementById("add_partsname").value;
    var quantity = document.getElementById("add_quantity").value;
    var price = document.getElementById("add_price").value;
    var information = document.getElementById("add_information").value;
    var username = document.getElementById("un").value;
    if(partsname=="" || quantity=="" || price=="" || price=="" || information =="")
    {
        alert("请填写增加零件的完整信息");
    }
    $.getJSON("/home/addparts", {partsname:partsname, quantity:quantity, price:price, information:information, username:username}, function (result) {
            if (result.state == "success") {

                alert("增加成功");
                document.getElementById("add_partsname").value = "";
                document.getElementById("add_quantity").value = "";
                document.getElementById("add_price").value = "";
                document.getElementById("add_information").value = "";
                //清空输入框

            }
            else {
                alert("增加失败");
            }
        }
    )
}
function deleteparts(){  //删除零件
    var partsid = document.getElementById("partsid").value;
    var username = document.getElementById("un").value;
    if(partsid == "")
    {
        alert("请填写要删除的零件编号");
    }
    else
    {
        $.getJSON("/home/deleteparts", {partsid:partsid, username:username}, function (result) {
            if(result.state == "wrong")
            {
                alert("没有权限删除");
            }
            else if(result.state == "fail")
            {
                alert("删除失败");
            }
            else
            {
                document.getElementById("partsid").value = "";
                alert("删除成功");
            }
        });
    }
}