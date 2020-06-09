function stopFunc(e) {
    e.stopPropagation ? e.stopPropagation() : e.cancelBubble = true;
}
function sold_system(){

    document.onclick = function (e) {
        document.getElementById("order_div").style.display = "none";
        document.getElementById("parts_div").style.display = "none";
    }

    document.getElementById("order_div").onclick = function (e) {
        e = e || event; stopFunc(e);
    }
    document.getElementById("showmyorder").onclick = function(e){

        document.getElementById("order_div").style.display = "block";
        e = e || event; stopFunc(e);
        myorder();
    }

    document.getElementById("parts_div").onclick = function (e) {
        e = e || event; stopFunc(e);
    }
    document.getElementById("showmyparts").onclick = function(e){

        document.getElementById("parts_div").style.display = "block";
        e = e || event; stopFunc(e);
        flushmy();
    }

    show_all();

}