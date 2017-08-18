function checkBalance() {
    var userYue = parseInt($("#yueId").text());
    var amt = $("#amt").val();
    if (amt < 50) {
        alert("提现额度不能小于50元");
        return false;
    }
    if (userYue < amt) {
        alert("余额不足");
        return false;
    }
    return true
}