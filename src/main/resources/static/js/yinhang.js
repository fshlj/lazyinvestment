$(document).ready(function () {
    $("#idCardNo").blur(function () {
        var idcard = /(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
        var idCardNo = $("#idCardNo").val();
        if(idCardNo!="") {
            if (idcard.test(idCardNo)) {
                $("#caerdNumsp").text("");
            } else {
                $("#caerdNumsp").text("输入有误！");
            }
        }
    });

    $("#mobile").blur(function () {
        var mobile = $("#mobile").val();
        if(mobile != ""){
            if (mobile != "") {
                var re = /^1[34578]\d{9}$/;
            }
            if (re.test(mobile)) {
                $("#mobilesp").text("");
            } else {
                $("#mobilesp").text("手机号有误!");
            }
        }
    });

    $("#password").blur(function () {
        var password = $("#password").val();
       var pwd = /^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$/;
        if (password != "") {
            if (pwd.test(password)) {
                $("#passwordsp").css("color","black");
            } else {
                $("#password").val("");
                $("#passwordsp").css("color","red");
            }
        }
    });

    $("#confirmPassword").blur(function () {
        var password = $("#password").val();
        var confirmPassword = $("#confirmPassword").val();
        if (password != confirmPassword){
            $("#confirmPassword").val("");
            $("#confirmPasswordsp").text("密码输入不一致!");
        }else {
            $("#confirmPasswordsp").text("");
        }

    });

});
