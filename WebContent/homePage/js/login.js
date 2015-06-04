$(document).ready(function() {
	$("#login_but1").click(function() {
		var role=$("#role").val();
		var user = $("#username").val().replace(/[ ]/g,"").replace(/[\r\n]/g,"");
		var pass = $("#password").val().replace(/[ ]/g,"").replace(/[\r\n]/g,"");
		//去掉回车换行
		var data = {
			'username' : user,
			'password' : pass
		};
		if(user==""){
			$("#login_inf").text("用户名不能为空");
			$("#login_inf").show();
		}else if(pass==""){
			$("#login_inf").text("密码不能为空");
			$("#login_inf").show();
		}else{
			$("#login_inf").text("");
			$("#login_inf").hide();
			$("#login_but1").html("登录中...");
			$("#login_but1").attr("disabled",true);
			var url="";
			if(role==0){
				url='system/manager/toLogin.action';
			}else if(role==2){
				url='system/teacher/toLogin.action';
			}else{
				url='system/supervise/toLogin.action';
			}
			$.ajax({
				url : url,
				data : data,
				type : 'POST',// html
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(data1) {
					if (data1.message == "-1") {
						$("#login_inf").html("用户名或密码错误");
						$("#login_inf").show();
						$("#login_but1").html("登录");
						$("#login_but1").prop("disabled",false);
					} else if (data1.message == "success") {
						$("#login_inf").html("");
						window.location.replace("homePage/index.jsp");
						$("#login_but1").val("登录");
						$("#login_but1").prop("disabled",false);
					}else{
						$("#login_inf").html(data1.message);
						$("#login_inf").show();
						$("#login_but1").html("登录");
						$("#login_but1").prop("disabled",false);
					}
				},
				complete : function() {
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#login_but1").html("登录");
					$("#login_but1").attr("disabled",false);
					alert(errorThrown + " " + textStatus);
				}
			});
		}
	});
});