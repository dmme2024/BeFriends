document.querySelector('.img__btn').addEventListener('click', function() {
    
    document.querySelector('.dowebok').classList.toggle('s--signup');
});

document.querySelector('#register_b').addEventListener('click',function(){
	 $.ajax({            
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型
                url: "http://localhost:8011/user/first/register" ,//url
                data: $('#register').serialize(),
				crossDomain:true,
                success: function (result) {
                    console.log(result);
                    if (result!=null) {
                        alert(result);
                        document.querySelector('.dowebok').classList.toggle('s--signup');
                    }
                },
                error : function() {
                    alert("异常！");
                }
            });
});

document.querySelector('#login_b').addEventListener('click',function(){
     $.ajax({            
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型
                url: "http://localhost:8011/user/first/login" ,//url
                data: $('#login').serialize(),
                crossDomain:true,
                xhrFields: { withCredentials: true },
                success: function (result) {
                    console.log(result);
                    if (result!=null) {
                        alert(result);
                        if (result=="登录成功！") {
                            window.location.replace("index.html");
                        }
                        
                    }
                },
                error : function() {
                    alert("异常！");
                }
            });
});