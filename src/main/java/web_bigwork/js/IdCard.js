document.querySelector('#IdCardUpBtn').addEventListener('click',function(){

    var formData = new FormData($('#IdCardForm')[0]);
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "http://localhost:8011/user/first/IdCard" ,//url
        xhrFields: { withCredentials: true },
        crossDomain:true,
        contentType:false,
        processData:false,//这个很有必要，不然不行
        data: formData,
        success: function (result) {
            console.log(result);
            if(result!=null){
                var mess = "认证成功！\n";
                for (var key in result){
                    mess += key + " : ";
                    mess += result[key]+"\n";
                }
                alert(mess);
            }
        },
        error : function() {
            alert("异常！");
        }
    });
});

document.querySelector('#IdCard-btn').addEventListener('click', function() {
    $("#background-IdCard").css("display",'block');
});

document.querySelector('.closeI').addEventListener('click', function() {
    $("#background-IdCard").css("display",'none');
});
/*document.querySelector("#IdCardUpImg").addEventListener('click',function (){
    $('#IdCardUp').click();
});*/
