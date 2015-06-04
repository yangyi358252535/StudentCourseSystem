$(document).ready(function() {
        //初始化全局ID
        dataId=[];
        var data={
        };
        $("input").off("blur");
        // 创建后就可以一直使用
        $("input").on("blur", function() {// .unbind("change")
                $(this).css("background", "white");
                hideInformation();
        });
        forSearchCondition();
        showMain();
        function forSearchCondition(){
        	$("#condition2").empty();
    		$("#condition1").change(function(){
    			$("#input").css("background","white");
    			hideInformation();
    			var d=$(this).val();
    			if(d==3){
    				clearError('condition1');
    				$("#condition2").empty();
    				$("#input").val("");
    				$("#condition2").append("<option value='3'>未听课</option>");
    				$("#condition2").append("<option value='4'>合格 </option>");
    				$("#condition2").append("<option value='5'>不合格 </option>");
    				$("#input").prop("disabled",true);
    				$("#condition2").prop("disabled",false);
    			}else{
    				$("#input").val("");
    				$("#condition2").empty();
    				$("#input").prop("disabled",false);
    				$("#condition2").prop("disabled",true);
    			}
    		});

        }
        $("#add_but").click(function(){
            loadAllIds("/supervise/lecture");
            if(dataId.length==0||dataId=="null"){
                    AlertInfo("请选择要评分的课程记录信息");
            }else if(dataId.length>1){
                    AlertInfo("请您选择单个课程记录信息");
            }else{
                    showLoading();
                    $("#lecture_main").load("../supervise/lecture/toScore.action",{'lecture.id':dataId[0]},function(){
                            $("#userTitle").html("听课评分");
                            hideLoading();
                            $("#lecturebar").hide();
                            $('#add_b').click(function() {
                                    $('#addForm').submit();
                            });
                            validate("addForm","add");
                            $('#cancel_b').click(function() {
                                    data={};
                                    showMain();
                            });
                    });
            }
            //清空全局Ids
            dataId = [];
    });
        $('#search_but').click(function(){
        	$("#input").css("background","white");
    		hideInformation();
    		var con1=$('#condition1').val();
    		clearSession("/supervise/lecture");
    		if(con1==0){
    			data={};
    			showMain();
    		}else{
    			data={};
    			var text=$('#input').val();
    			var c2=$('#condition2').val();
    			if((con1=="1"&&text=="")||(con1=="2"&&text=="")){
    				$("#input").css("background","#FF77AD");
    				$("#input").focus();
    				AlertInfo("请您填写相应的信息");
    			}else if(con1=="3"){
    				data['flagString']=c2;
    				showMain();
    			}else{
    				data['flagString']=con1;
    				data['information']=text;
    				showMain();
    			}
    		}
    		//清空全局Ids
    		dataId = [];
        });
        function clearError(id){
                $("#"+id).css("background","white");
        }
        function validate(formId,type){
                $("#"+formId).validate({
                        rules: {
                        	'lecture.score': {
                                required: true,
                                number:true,
                                gt:0,
                                lt:101
                        	}
                        },
                        //设置提示听课
                        messages:{
                        	'lecture.score': {
                                required: "请填写听课的成绩",
                                number:"请填写正确听课的成绩",
                                gt:"听课的成绩必须大于0分",
                                lt:"听课的成绩不能超过100分"
                        	}
                        },
                        //指定错误听课位置
                        errorPlacement: function (error, element) {
                         element.parent().find("span").append(error);
                        },
                        //设置验证触发事件
                        focusInvalid: true,
                        submitHandler: function(form) {
                                if(type=="add"){
                                        confirmInformation("你确定要提交评分信息吗？",function(){
                                                // 提交表单<br>
                                                var option = {
                                                                data:{},
                                                                beforeSubmit : function() {
                                                                        showLoading();
                                                                        return true;
                                                                },
                                                                success : function() {
                                                                        data={};
                                                                        showMain();
                                                                        setTimeout('AlertInfo("成功提交评分")',1800);
                                                                },error : function (jqXHR, textStatus, errorThrown) {
                                                                        showAlertDialog(errorThrown + " " + textStatus);
                                                                }
                                                        };
                                                $('#'+formId).ajaxSubmit(option);
                                        });
                                }
                        }
                });
        }
        function showMain(){
                clearError('input');
                toDateList('lecture_main','lecturebar','lecturelist','/supervise/lecture',data,'id',generalIdList);
                $("#userTitle").html("教师评分列表");
        }
});
