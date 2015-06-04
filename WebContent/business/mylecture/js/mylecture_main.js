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
        $("#add_but").click(function() {
                showLoading();
                $("#mylecture_main").load("../business/mylecture/toAdd.action", function() {
                        $("#userTitle").html("添加我的听课");
                        hideLoading();
                        $("#mylecturebar").hide();
                        validate("addForm","add");
                        $("#add_b").click(function(){
                                $("#addForm").submit();
                        });
                        $('#cancel_b').click(function() {
                                data={};
                                showMain();
                });
                });
                //初始化全局ID
                dataId=[];
        });
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
        $('#search_but').click(function(){
        	$("#input").css("background","white");
    		hideInformation();
    		var con1=$('#condition1').val();
    		clearSession("/business/mylecture");
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
        $("#look_but").click(function(){
                loadAllIds("/business/mylecture");
                if(dataId.length==0||dataId=="null"){
                        AlertInfo("请选择要查看的我的听课");
                }else if(dataId.length>1){
                        AlertInfo("请您选择单个我的听课");
                }else{
                        showLoading();
                        $("#mylecture_main").load("../business/mylecture/toLook.action",{'lecture.id':dataId[0]},function(){
                                $("#userTitle").html("查看我的听课记录");
                                hideLoading();
                                $("#mylecturebar").hide();
                                $('#cancel_b').click(function() {
                                        data={};
                                        showMain();
                                });
                        });
                }
                //清空全局Ids
                dataId = [];
        });
        function validate(formId,type){
                $("#"+formId).validate({
                        rules: {
                                'lecture.course.id': {
                                        required: true
                                }
                        },
                        //设置提示听课
                        messages:{
                           'lecture.course.id': {
                                 required: "请要添加的课程信息"
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
                                        confirmInformation("你确定要添加我的听课吗？",function(){
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
                                                                        setTimeout('AlertInfo("我的听课添加成功")',1800);
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
                toDateList('mylecture_main','mylecturebar','mylecturelist','/business/mylecture',data,'id',generalIdList);
                $("#userTitle").html("我的听课列表");
        }
});
