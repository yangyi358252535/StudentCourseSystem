$(document).ready(function(){
	$( "#edit_b_con" ).click(function(){
		$("#modifyPass").submit();
	});
	$("#modifyPass").validate({						  
		rules: {
			'oldPass': {
				required: true
			},
			'newPass': {
				required: true,
				rangelength:[8,14],
				notEqualTo:"#oldPass"
			},
			'newPassAgain': {
				required: true,
				equalTo:"#newPass"
			}
		},
		//设置提示信息
		messages:{
			'oldPass': {
				required: "请您输入旧密码"
			},
			'newPass': {
				required: "请您输入新密码",
				rangelength:'新密码长度在{0}-{1}个字符之间',
				notEqualTo: '新密码不能与旧密码相同'
			},
			'newPassAgain': {
				required: "请您再次输入新密码",
				equalTo:'两次输入密码不相同'
			}
		},
		//指定错误信息位置
		errorPlacement: function (error, element) { 
  			element.parent().find("span").append(error);
		},
		//设置验证触发事件
		focusInvalid: true,   
		submitHandler: function(form) {
			$("#oldPass").parent().find("span").html("");
			var oldP=$("#oldPass").val();
			var data={
				'oldPass':oldP
			};
			var auth=$("#auth").val();
			var url="";
			var url1="";
			if(auth==0){
				url='../system/user/checkOldPass.action';
			}else if(auth==1){
				url='../system/teacher/checkOldPass.action';
			}else if(auth==2){
				url='../system/student/checkOldPass.action';
			}
			$.ajax({
				url : url,
				data : data,
				type : 'POST',
				async:false,
				success : function(data1) {
					if(data1.message=="1"){
						$( "#old" ).html("旧密码错误");
					}else{
						// 提交表单<br>
						var n=$( "#newPass" ).val();
						var param={
								'newPass':	n
							};
						if(auth==0){
							url1='../system/user/changePass.action';
						}else if(auth==1){
							url1='../system/teacher/changePass.action';
						}else if(auth==2){
							url1='../system/student/changePass.action';
						}
						$.ajax({
							url : url1,
							data : param,
							type : 'POST',
							success : function(data2) {
								$( "#oldPass" ).val("");
								$( "#newPass" ).val("");
								$( "#newPassAgain" ).val("");
								setTimeout('AlertInfo("账户密码修改成功")',1500);
							}
						});
					}
				}
			});
		}
	});
});
