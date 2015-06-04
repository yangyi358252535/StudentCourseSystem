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
                $("#student_main").load("../business/student/toAdd.action", function() {
                        $("#userTitle").html("添加学生信息");
                        hideLoading();
                        $("#studentbar").hide();
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
                clearSession("/business/student");
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
                loadAllIds("/business/student");
                if(dataId.length==0||dataId=="null"){
                        AlertInfo("请选择要修改的学生信息");
                }else if(dataId.length>1){
                        AlertInfo("请您选择单个学生信息");
                }else{
                        showLoading();
                        $("#student_main").load("../business/student/toModify.action",{'student.id':dataId[0]},function(){
                                $("#userTitle").html("编辑学生信息");
                                hideLoading();
                                $("#studentbar").hide();
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
                                'student.name': {
                                        required: true
                                },
                                'student.age':{
                                   required: true,
                                   digits:true,
                                   gt:0
                                },
                                'student.tel':{
                                	required: true,
                					cellphone:true
                                 },
                                'student.class1.id': {
                                        required: true
                                }
                        },
                        //设置提示信息
                        messages:{
                           'student.name': {
                                 required: "请填写学生姓名"
                           },
                           'student.age':{
                              required:  "请填写学生年龄",
                              digits:"请填写正确的学生年龄",
                              gt:"请填写正确的学生年龄"
                           },
                           'student.tel':{
                        	   required: "请填写学生联系电话",
           						cellphone:"请输入正确的联系电话"
                            },
                                'student.class1.id': {
                                        required: "请选择班级信息"
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
                                        confirmInformation("你确定要添加学生信息吗？",function(){
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
                                                                        setTimeout('AlertInfo("学生信息添加成功")',1800);
                                                                },error : function (jqXHR, textStatus, errorThrown) {
                                                                        showAlertDialog(errorThrown + " " + textStatus);
                                                                }
                                                        };
                                                $('#'+formId).ajaxSubmit(option);
                                        });
                                }else{
                                        confirmInformation("你确定要修改学生信息吗？",function(){
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
                                                                        setTimeout('AlertInfo("学生信息修改成功")',1800);
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
                toDateList('student_main','studentbar','studentlist','/business/student',data,'id',generalIdList);
                $("#userTitle").html("学生信息列表");
        }
});
