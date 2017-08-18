function checkPhoneAndPassword() {
    var phone = $("#name").val();
    if (phone == null || phone == "") {
        // alert("用户名不能为空");
        $("#userNameId").text("用户名不能为空");
        return false;
    }
    $("#userNameId").text(null);
    var password = $("#password").val();
    if (password == null || password == "") {
        // alert("密码不能为空");
        $("#passwordId").text("密码不能为空");
        return false;
    }
    $("#passwordId").text(null);
}