

$(document).ready(function () {
    createCode();
});
//用户名唯一验证
$(document).ready(function () {
        $("#username").blur(function () {
            var name = $("#username").val();
            if (name != ""){
                var f = /^[a-zA-Z][a-zA-Z0-9]{3,15}$/;
            }
                $.ajax({
                    type: "post",
                    url: "usernameOnly.action?username=" + name,
                    success: function (data) {
                        if (data == "false") {
                            $("#namesp").text("");
                            if (f.test(name)) {
                                $("#namesp").text("");
                            } else {
                                $("#namesp").text("用户名有误");
                            }
                            $("#button").removeAttr("disabled"); //移除disabled属性
                        }
                        if (data == "true") {
                            $("#namesp").text("用户名已注册！");
                            $("#button").attr("disabled", "true");//添加disabled属性

                        }
                    }
                });

        });
});
//手机号唯一验证
$(document).ready(function () {

        $("#phone").blur(function () {
            var phone = $("#phone").val();
            if(phone != ""){
                var re = /^1[34578]\d{9}$/;
            }
            $.ajax({
                type: "post",
                url: "phoneOnly.action?phone=" + phone,
                success: function (data) {
                    if (data == "false") {
                        $("#phonesp").text("");
                        if (re.test(phone)) {
                            $("#phonesp").text("");
                        } else {
                            $("#phonesp").text("手机号有误");
                        }
                        $("#button").removeAttr("disabled"); //移除disabled属性
                    }
                    if (data == "true") {
                        $("#phonesp").text("手机号已注册！");
                        $("#button").attr("disabled", "true");//添加disabled属性
                    }
                }
            });
        });
});
//图形验证码
var code;
function createCode() {
    code = "";
    var codeLength = 6; //验证码的长度
    var checkCode = document.getElementById("checkCode");
    var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
    for (var i = 0; i < codeLength; i++) {
        var charNum = Math.floor(Math.random() * 52);
        code += codeChars[charNum];
    }
    if (checkCode) {
        checkCode.className = "code";
        checkCode.innerHTML = code;
    }
}
function validateCode() {
    var inputCode = $("#checkCod").val();
    if (inputCode.length <= 0) {

        $("#sp").text("请输入验证码！");
        $("#button").attr("disabled", "true");//添加disabled属性

    }
    else if (inputCode.toUpperCase() != code.toUpperCase()) {
        $("#button").attr("disabled", "true");//添加disabled属性
        $("#sp").text("验证码输入有误！");
        createCode();
    }
    else {
        $("#button").removeAttr("disabled"); //移除disabled属性
        $("#sp").text("验证码正确！")
    }
}

var countdown = 60;
function settime(obj) {
    if (countdown == 0) {
        obj.removeAttribute("disabled");
        obj.value = "获取验证码";
        countdown = 60;
        return;
    } else {
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
    $("#yanzheng").click(function () {
        var phone = $("#phone").val();
        $.get("/hwj/validate?phone=" + phone);
    });
});


