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
                $("#clazz_main").load("../system/clazz/toAdd.action", function() {
                        $("#userTitle").html("添加班级信息");
                        hideLoading();
                        $("#clazzbar").hide();
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
                clearSession("/system/clazz");
                if(con1==0){
                        data={};
                        showMain();
                }else{
                        data={};
                        var text=$('#input').val();
                        if((con1=="1"&&text=="")||(con1=="2"&&text=="")){
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
                loadAllIds("/system/clazz");
                if(dataId.length==0||dataId=="null"){
                        AlertInfo("请选择要修改的班级信息");
                }else if(dataId.length>1){
                        AlertInfo("请您选择单个班级信息");
                }else{
                        showLoading();
                        $("#clazz_main").load("../system/clazz/toModify.action",{'clasz.id':dataId[0]},function(){
                                $("#userTitle").html("编辑班级信息");
                                hideLoading();
                                $("#clazzbar").hide();
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
                                'clasz.name': {
                                        required: true
                                },
                                'clasz.specialty.id': {
                                        required: true
                                }
                        },
                        //设置提示信息
                        messages:{
                           'clasz.name': {
                                  required: "请填写班级名称"
                           },
                           'clasz.specialty.id': {
                                   required: "请选择所属专业"
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
                                        confirmInformation("你确定要添加班级信息吗？",function(){
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
                                                                        setTimeout('AlertInfo("班级信息添加成功")',1800);
                                                                },error : function (jqXHR, textStatus, errorThrown) {
                                                                        showAlertDialog(errorThrown + " " + textStatus);
                                                                }
                                                        };
                                                $('#'+formId).ajaxSubmit(option);
                                        });
                                }else{
                                        confirmInformation("你确定要修改班级信息吗？",function(){
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
                                                                        setTimeout('AlertInfo("班级信息修改成功")',1800);
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
                toDateList('clazz_main','clazzbar','clazzlist','/system/clazz',data,'id',generalIdList);
                $("#userTitle").html("班级信息列表");
        }
});
