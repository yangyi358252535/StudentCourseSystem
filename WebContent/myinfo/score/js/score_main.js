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
                clearSession("/myinfo/score");
                if(con1==0){
                        data={};
                        showMain();
                }else{
                        data={};
                        var text=$('#input').val();
                        if((con1=="1"&&text=="")||(con1=="2"&&text=="")){
                                $("#input").css("background","#FF77AD");
                                $("#input").focus();
                                AlertInfo("请您填写相应的成绩信息");
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
        function showMain(){
                clearError('input');
                toDateList('score_main','scorebar','scorelist','/myinfo/score',data,'id',generalIdList);
                $("#userTitle").html("我的成绩信息列表");
        }
});
