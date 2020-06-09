function stopFunc(e) {
    e.stopPropagation ? e.stopPropagation() : e.cancelBubble = true;
}
function buy_system(){

    document.onclick = function (e) {
        document.getElementById("buy_div").style.display = "none";
        document.getElementById("order_div").style.display = "none";
    }

    document.getElementById("buy_div").onclick = function (e) {
        e = e || event; stopFunc(e);
    }
    document.getElementById("showb").onclick = function (e) {
        document.getElementById("buy_div").style.display = "block";
        e = e || event; stopFunc(e);
    }

    document.getElementById("order_div").onclick = function (e) {
        e = e || event; stopFunc(e);
    }
    document.getElementById("showorder").onclick = function(e){

        document.getElementById("order_div").style.display = "block";
        e = e || event; stopFunc(e);
        flushorder_buy();
    }
    show_all();
}