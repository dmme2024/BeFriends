
document.querySelector('#userinfo-btn').addEventListener('click', function() {
    		$("#mengban").css("display",'block');

    		$.ajax({            
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8011/user/first/checkSelf" ,//url
                xhrFields: { withCredentials: true },
				crossDomain:true,
                async:false,
                cache:false,
                processData:false,
                contentType:false,
                success: function (result) {
                    console.log(result);
                    if(result!=null){
                        $('#userinfo-username').text(result.username);
                        if (result.address!=null) {
                            $('#userinfo-address').val(result.address);
                        }
                        if (result.email!=null) {
                            $('#userinfo-email').val(result.email);
                        }
                        if (result.phoneNumber!=null) {
                            $('#userinfo-phonenumber').val(result.phoneNumber);
                        }
                        if (result.truename!=null) {
                            $('#userinfo-truename').val(result.truename);
                        }
                        if (result.introduce!=null) {
                            $('#userinfo-introduce').text(result.introduce);
                        }
                        if (result.userPic!=null) {
                            d = new Date();
                            $('#UploadPicImg').removeAttr("src");
                            $('#UploadPicImg').attr('src',result.userPic+"?"+d.getSeconds());
                        }
                        
                        
                    }
                },
                error : function() {
                    alert("异常！");
                }
            });
		});

document.querySelector('.close').addEventListener('click', function() {
    		$("#mengban").css("display",'none');
		});

//上传图片按钮
/*document.querySelector('#UploadPicImg').addEventListener('click',function(){
	$('#UploadPic').click();
});*/

document.querySelector('#userinfo-submit').addEventListener('click',function(){
    var formData = new FormData($('#userinfo-form')[0]);
    $.ajax({            
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "http://localhost:8011/user/first/updateInfo" ,//url
                xhrFields: { withCredentials: true },
                crossDomain:true,
                contentType:false,
                processData:false,//这个很有必要，不然不行
                data: formData,
                success: function (result) {
                    console.log(result);
                    if(result!=null){
                        $('#userinfo-username').text(result.username);
                        if (result.address!=null) {
                            $('#userinfo-address').val(result.address);
                        }
                        if (result.email!=null) {
                            $('#userinfo-email').val(result.email);
                        }
                        if (result.phoneNumber!=null) {
                            $('#userinfo-phonenumber').val(result.phoneNumber);
                        }
                        if (result.truename!=null) {
                            $('#userinfo-truename').val(result.truename);
                        }
                        if (result.introduce!=null) {
                            $('#userinfo-introduce').text(result.introduce);
                        }
                        if (result.userPic!=null) {
                            $('#UploadPicImg').attr('src',result.userPic);
                        }
                        
                        
                    }
                },
                error : function() {
                    alert("异常！");
                }
        });
});