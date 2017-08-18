var countdown = 60;
function settime(obj) {
    if (countdown == 0) {
        obj.removeAttribute("disabled");
        obj.value = "获取验证码";
        countdown = 60;
        return;
    }
    else {
        obj.setAttribute("disabled", true);
        obj.value = "重新发送(" + countdown + ")";
        countdown--;
    }
    setTimeout(function () {
            settime(obj)
        }
        , 1000)
}
$(document).ready(function () {
    $("#phoneCheckId").click(function () {
        var phone = $("#mobile").val();
        if (phone != null && phone != "") {
            $.get("/rcy/sendPhoneCheckCode?phoneCheck=" + phone);
        }
        return false;
    });
});

function ckPsw() {
    var password1 = $("#password1").val();
    var password2 = $("#password2").val();
    if (password1.length < 6 || password2.length < 6) {
        $("#pwdSp").text("密码长度不能小于6位");
        return false;
    }
    if (password1 != password2) {
        $("#pwdSp").text("两次密码不一致");
        return false;
    }
    return true;
}
//验证手机号是否存在
function checkPhone() {
    var phone = $("#mobile").val();
    $.post("checkPhone", {"phone": phone},
        function (data) {
            if (data == "1") {
                $("#phoneCheck").text("手机号正确")
                $("#updateBtn").removeAttr("disabled");
            } else {
                $("#phoneCheck").text("手机号不存在")
                $("#updateBtn").attr("disabled", true);
                $(o)[0].focus();
            }
        }, "json");
}

