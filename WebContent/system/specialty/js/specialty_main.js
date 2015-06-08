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
                $("#specialty_main").load("../system/specialty/toAdd.action", function() {
                        $("#userTitle").html("添加专业信息");
                        hideLoading();
                        $("#specialtybar").hide();
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
                $("#condition1").change(function(){
                        $("#input").css("background","white");
                        hideInformation();
                         $("#input").val("");
                });

        }
        $('#search_but').click(function(){
                $("#input").css("background","white");
                hideInformation();
                var con1=$('#condition1').val();
                clearSession("/system/specialty");
                if(con1==0){
                        data={};
                        showMain();
                }else{
                        data={};
                        var text=$('#input').val();
                        if((con1=="1"&&text=="")){
                                $("#input").css("background","#FF77AD");
                                $("#input").focus();
                                AlertInfo("请您填写相应的信息");
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
//              hideInformation('effect');
        }
        $("#edit_but").click(function(){
                loadAllIds("/system/specialty");
                if(dataId.length==0||dataId=="null"){
                        AlertInfo("请选择要修改的专业信息");
                }else if(dataId.length>1){
                        AlertInfo("请您选择单个专业信息");
                }else{
                        showLoading();
                        $("#specialty_main").load("../system/specialty/toModify.action",{'specialty.id':dataId[0]},function(){
                                $("#userTitle").html("编辑专业信息");
                                hideLoading();
                                $("#specialtybar").hide();
                                $('#edit_b').click(function() {
                                        $('#editForm').submit();
                                });
                                validate("editForm","modify");
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
                                'specialty.name': {
                                        required: true
                                },
                                'specialty.institute.id':{
                                	 required: true
                                }
                        },
                        //设置提示信息
                        messages:{
                           'specialty.name': {
                                 required: "请填写专业名称"
                           },
                           'specialty.institute.id':{
                          	 required: "请选择专业所属学院"
                          }
                        },
                        //指定错误信息位置
                        errorPlacement: function (error, element) {
                         element.parent().find("span").append(error);
                        },
                        //设置验证触发事件
                        focusInvalid: true,
                        submitHandler: function(form) {
                                if(type=="add"){
                                        confirmInformation("你确定要添加专业信息吗？",function(){
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
                                                                        setTimeout('AlertInfo("专业信息添加成功")',1800);
                                                                },error : function (jqXHR, textStatus, errorThrown) {
                                                                        showAlertDialog(errorThrown + " " + textStatus);
                                                                }
                                                        };
                                                $('#'+formId).ajaxSubmit(option);
                                        });
                                }else{
                                        confirmInformation("你确定要修改专业信息吗？",function(){
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
                                                                        setTimeout('AlertInfo("专业信息修改成功")',1800);
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
                toDateList('specialty_main','specialtybar','specialtylist','/system/specialty',data,'id',generalIdList);
                $("#userTitle").html("专业信息列表");
        }
});
