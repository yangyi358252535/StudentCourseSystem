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
                $("#course_main").load("../business/course/toAdd.action", function() {
                        $("#userTitle").html("添加课程信息");
                        hideLoading();
                        $("#coursebar").hide();
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
                clearSession("/business/course");
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
                loadAllIds("/business/course");
                if(dataId.length==0||dataId=="null"){
                        AlertInfo("请选择要修改的课程信息");
                }else if(dataId.length>1){
                        AlertInfo("请您选择单个课程信息");
                }else{
                        showLoading();
                        $("#course_main").load("../business/course/toModify.action",{'course.id':dataId[0]},function(){
                                $("#userTitle").html("编辑课程信息");
                                hideLoading();
                                $("#coursebar").hide();
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
                                'course.name': {
                                        required: true
                                },
                                'course.teacher.id': {
                                    required: true
                                },
                                'course.specialty.id': {
                                    required: true
                                },
                                'course.zhouci.masterid': {
                                    required: true
                                },
                                'course.jieshu.id': {
                                    required: true
                                },
                                'course.weizhi.id': {
                                    required: true
                                },
                                'course.currentStuedentCount':{
                                   required: true,
                                   digits:true,
                                   gt:0
                                }
                        },
                        //设置提示信息
                        messages:{
                          'course.name': {
                                 required: "请填写课程名称"
                          },
                          'course.teacher.id': {
                              required: "请选择教师信息"
                          },
                          'course.specialty.id': {
                              required: "请选择专业信息"
                          },
                          'course.zhouci.masterid': {
                              required: "请选择周数"
                          },
                          'course.jieshu.id': {
                              required:"请选择节数"
                          },
                          'course.weizhi.id': {
                              required: "请选择上课教室"
                          },
                          'course.currentStuedentCount':{
                             required: "请填写上课最大人数",
                             digits:"请填写正确的格式",
                             gt:"请填写正确的格式"
                          }
                        },
                        //指定错误信息位置
                        errorPlacement: function (error, element) {
                         element.parent().find("span").append(error);
                        },
                        //设置验证触发事件
                        focusInvalid: true,
                        submitHandler: function(form) {
                         var result=false;
                         $.ajax({
                          url : '..//business/course/checkExsitProcess.action',
                          type : 'POST',
                          async : false,
                          success : function(data2) {
                           if(data2.message=="1"){
                            AlertInfo("当前的记录已经存在,请重新选择");
                           }else{
                            if(type=="add"){
                                            confirmInformation("你确定要添加课程信息吗？",function(){
                                                    // 提交表单<br>
                                                    var option = {
                                                                    data:{},
                                                                    beforeSubmit : function() {
                                                                            showLoading();
                                                                            return true;
                                                                    },
                                                                    success : function(data44) {
                                                                    	if(data44.message!="1"){
                                                                    		  data={};
                                                                              showMain();
                                                                              setTimeout('AlertInfo("课程信息添加成功")',1800);
                                                                    	}else{
                                                                    		 hideLoading();
                                                                    		AlertInfo("当前添加的课程信息已经重复")
                                                                    	}
                                                                    },error : function (jqXHR, textStatus, errorThrown) {
                                                                            showAlertDialog(errorThrown + " " + textStatus);
                                                                    }
                                                            };
                                                    $('#'+formId).ajaxSubmit(option);
                                            });
                                    }else{
                                            confirmInformation("你确定要修改课程信息吗？",function(){
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
                                                                            setTimeout('AlertInfo("课程信息修改成功")',1800);
                                                                    },error : function (jqXHR, textStatus, errorThrown) {
                                                                            showAlertDialog(errorThrown + " " + textStatus);
                                                                    }
                                                            };
                                                    $('#'+formId).ajaxSubmit(option);
                                            });
                                    }
                           }
                          }
                         });
                        }
                });
        }
        function showMain(){
                clearError('input');
                toDateList('course_main','coursebar','courselist','/business/course',data,'id',generalIdList);
                $("#userTitle").html("课程信息列表");
        }
});
