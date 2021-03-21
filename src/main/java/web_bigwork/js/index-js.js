function pageClick(num){
    var search = $('#judgeS').text();
    if (search == 0)
        search = "";
    else
        search = "/search";

    console.log(search);
    if (num>1)
        $("#pageLast").css("visibility","visible");
    else
        $("#pageLast").css("visibility","hidden");
    $('.active').removeClass("active");
    $('#page'+num).addClass("active");
    var page = $('#page'+num).text();
    var keyword = $('#searchIn').val();
    var Sdata = {"page":page,"keyword":keyword};
    $.ajax({
        type: "GET",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8011/user/first/findAll"+search,//url
        crossDomain: true,
        data:Sdata,
        xhrFields: {withCredentials: true},
        success: function (result) {
            console.log(result);
            var pageNum = result.pageNum;
            /*页数大于7*/
            if (page<pageNum&&page%7==0){

                for (var j=1;j<8;j++){
                    $('#page'+j).text(page+j-1);
                }
            }
            if (page==pageNum){
                $("#pageNext").css("visibility","hidden");
                var i = 1;
                while (i<5){
                    if (result["username"+i]!=null){
                        $('#username'+i+'-a').text(result["username"+i]);
                        $('#username'+i+'-b').text(result["username"+i]);
                        $('#username'+i+'-c').text(result["username"+i]);
                        $('#introduce'+i).text(result["introduce"+i]);
                        $('#truename'+i).text(result["truename"+i]);
                        $('#phoneNumber'+i).text(result["phoneNumber"+i]);
                        $('#email'+i).text(result["email"+i]);
                        $('#address'+i).text(result["address"+i]);
                        if (result["userPic"+i]!=null)
                            $('#userPic'+i).attr('src',result["userPic"+i]+"?"+new Date().getSeconds());
                        else
                            $('#userPic'+i).attr('src',"http://localhost:63342/datebasebigwork/web_bigwork\\用户头像\\wu.png");
                        $('#card-'+i).css("display","flex");

                    }else {
                        $('#card-'+i).css("display","none");
                    }
                    i++;
                }
            }else {
                $("#pageNext").css("visibility","visible");
                var i = 1;
                while (i<5){
                    if (result["username"+i]!=null){
                        $('#username'+i+'-a').text(result["username"+i]);
                        $('#username'+i+'-b').text(result["username"+i]);
                        $('#username'+i+'-c').text(result["username"+i]);
                        $('#introduce'+i).text(result["introduce"+i]);
                        $('#truename'+i).text(result["truename"+i]);
                        $('#phoneNumber'+i).text(result["phoneNumber"+i]);
                        $('#email'+i).text(result["email"+i]);
                        $('#address'+i).text(result["address"+i]);
                        if (result["userPic"+i]!=null)
                            $('#userPic'+i).attr('src',result["userPic"+i]+"?"+new Date().getSeconds());
                        else
                            $('#userPic'+i).attr('src',"http://localhost:63342/datebasebigwork/web_bigwork\\用户头像\\wu.png");
                        $('#card-'+i).css("display","flex");

                    }else {
                        $('#card-'+i).css("display","none");
                    }
                    i++;
                }
            }



        },
        error: function () {
            alert("异常！");
        }
    });
}

$(document).ready(function () {
    document.querySelector('#login-btn').addEventListener('click',function(){
        window.location.replace("login_Register.html");
    });

    document.querySelector('#delete-btn').addEventListener('click',function(){
        $.ajax({
            type: "GET",//方法类型
            dataType: "text",//预期服务器返回的数据类型
            url: "http://localhost:8011/user/first/deleteSelf",//url
            crossDomain: true,
            xhrFields: {withCredentials: true},
            success: function (result) {
                console.log(result);
                if (result != null) {
                    alert(result);
                    window.location.replace("login_Register.html");
                }
            },
            error: function () {
                alert("异常！");
            }
        });
    });

    document.querySelector('#searchBtn').addEventListener('click',function(){
        $('#search').toggle();
        $('#judgeS').text("0");
        var i =1;
        //$(".pagebtn").css('display',"inline-block");
        while (i<8){
            $("#page"+i).css('display',"inline-block");
            i++;
        }
        $.ajax({
            type: "GET",//方法类型
            dataType: "text",//预期服务器返回的数据类型
            url: "http://localhost:8011/user/first/pageNum",//url
            crossDomain: true,
            xhrFields: {withCredentials: true},
            success: function (result) {
                console.log("pageNum:"+result);
                num=++result;
                console.log(num);
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
    });

    document.querySelector('#quit-btn').addEventListener('click',function(){
        $.ajax({
            type: "GET",//方法类型
            dataType: "text",//预期服务器返回的数据类型
            url: "http://localhost:8011/user/first/quit",//url
            crossDomain: true,
            xhrFields: {withCredentials: true},
            success: function (result) {
                console.log(result);
                if (result != null) {
                    alert(result);
                    window.location.replace("login_Register.html");
                }
            },
            error: function () {
                alert("异常！");
            }
        });
    });

    document.querySelector('#pageNext').addEventListener('click',function(){
        var currentP0 = $('.active')[0].id;
        var currentP = currentP0.replace("page","");
        currentP++;
        $('#page'+currentP).click();
    });
    document.querySelector('#pageLast').addEventListener('click',function(){
        var currentP0 = $('.active')[0].id;
        var currentP = currentP0.replace("page","");
        currentP--;
        $('#page'+currentP).click();
    });


        $.ajax({
            type: "GET",//方法类型
            dataType: "text",//预期服务器返回的数据类型
            url: "http://localhost:8011/user/first/session",//url
            crossDomain: true,
            xhrFields: {withCredentials: true},
            success: function (result) {
                console.log(result);
                if (result==0) {
                    alert("您还未登录！请先登录");
                    window.location.replace("login_Register.html");
                }
            },
            error: function () {
                alert("异常！");
            }
        });

    $('#page1').trigger('click');

    $.ajax({
        type: "GET",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: "http://localhost:8011/user/first/pageNum",//url
        crossDomain: true,
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



    });