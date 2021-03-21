$(document).ready(function () {
    $("#searchIn").bind('keypress',function (event) {
        if (event.keyCode == 13){
            $('#judgeS').text("1");
            console.log($('judgeS').text());
            //$(".pagebtn").css('display',"inline-block");
            var i =1;
            while (i<8){
                $("#page"+i).css('display',"inline-block");
                i++;
            }
            var data0 = {"keyword":$('#searchIn').val()};
            $.ajax({
                type: "GET",//方法类型
                dataType: "text",//预期服务器返回的数据类型
                url: "http://localhost:8011/user/first/searchPageNum",//url
                crossDomain: true,
                data:data0,
                xhrFields: {withCredentials: true},
                success: function (result) {
                    console.log("pageNum:"+result);
                    num=++result;
                    if (num==2){
                        $(".pagebtn").css('display',"none");
                        num=9;
                    }else {
                        $("#pageLast").css("visibility","hidden");
                    }
                    while (num<8){
                        $("#page"+num).css('display',"none");
                        num++;
                    }

                },
                error: function () {
                    alert("异常！");
                }
            });
            $('#page1').click();
        }
    });
});