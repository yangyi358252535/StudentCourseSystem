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
    			if(d==2){
    				clearError('condition1');
    				$("#condition2").empty();
    				$("#input").val("");
    				$("#condition2").append("<option value='2015'>2015</option>");
    				$("#condition2").append("<option value='2016'>2016</option>");
    				$("#condition2").append("<option value='2017'>2017</option>");
    				$("#condition2").append("<option value='2018'>2018</option>");
    				$("#condition2").append("<option value='2019'>2019</option>");
    				$("#condition2").append("<option value='2020'>2020</option>");
    				$("#condition2").append("<option value='2021'>2021</option>");
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
    		clearSession("/myinfo/score");
    		if(con1==0){
    			data={};
    			showMain();
    		}else{
    			data={};
    			var text=$('#input').val();
    			var c2=$('#condition2').val();
    			if((con1=="1"&&text=="")){
    				$("#input").css("background","#FF77AD");
    				$("#input").focus();
    				AlertInfo("请您填写相应的信息");
    			}else if(con1=="2"){
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
//              hideInformation('effect');
        }
        function showMain(){
                clearError('input');
                toDateList('score_main','scorebar','scorelist','/myinfo/score',data,'id',generalIdList);
                $("#userTitle").html("我的成绩信息列表");
        }
});
