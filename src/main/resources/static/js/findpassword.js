function reloadValidCode(o) {
    o.src = "/rcy/getImgCheck?timed=" + new Date().getMilliseconds();
}
function checkCode() {
    var code = $("#code").val();
    $.post("checkCode", {"code": code},
        function (data) {
            if (data == "1") {

            } else {
            }
        }, "json");
}

function updateCode(val) {
    var src = $(val).attr("src");
    var src = $(val).attr("src", src + "?time=" + new Date().getTime());
}