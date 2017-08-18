function checkChongZhiBalance() {
    var cardYue = parseInt($("#cardYueId").text());
    var amt = $("#amt").val();
    if (amt <= 0) {
        alert("充值额度不能小于0元");
        return false;
    }
    if (cardYue < amt) {
        alert("银行卡余额不足");
        return false;
    }
    return true

}
