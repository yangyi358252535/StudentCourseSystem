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
	$("#input_but").click(function(){
		loadAllIds("/business/score");
		if(dataId.length==0||dataId=="null"){
			setTimeout('AlertInfo("请选择要录入成绩的学生信息")',100);
		}else if(dataId.length>1){
			setTimeout('AlertInfo("请您选择单条学生信息")',100);
		}else{
			var year_str=$("#year_str").val();
			var term_str=$("#term_str").val();
			showLoading();
			$("#student_main").load("../business/score/toInput.action",{'student.id':dataId[0],'year_str':year_str,'term_str':term_str},function(){
				$("#userTitle").html("录入学生成绩");
				hideLoading();
				greneralIds();
				$("#studentbar").hide();
				$('#edit_b').click(function() {
					var result=true;
					var score=null;
					var size=$('#tbody').find("tr").size();
					for(var i=0;i<size;i++){
						score=$("#score"+i).val();
						score=score.trim();
						if (score == "") {
							$("#score" + i).css("background", "#FF77AD");
							AlertInfo("请填写入相应的科目得分或0");
							$("#score" + i).focus();
							result = false;
							break;
						}else if (!(/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(score))|| isNaN(score)) {
							$("#score" + i).css("background", "#FF77AD");
							AlertInfo("请您填写正确的格式");
							$("#score" + i).focus();
							result = false;
							break;
						} else if (score <0||score >100) {
							$("#score" + i).css("background", "#FF77AD");
							AlertInfo("请您正确填写入分数");
							$("#score" + i).focus();
							result = false;
							break;
						} else {
							$("#score" + i).css("background", "white");//:"^[1-9]/d*$",     
						}
					}
					if(result){
						confirmInformation("你确定要提交学生成绩吗？",function(){
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
										setTimeout('AlertInfo("成绩录入成功")',1800);
									},error : function (jqXHR, textStatus, errorThrown) {
										showAlertDialog(errorThrown + " " + textStatus);
									}
								};
							$('#addForm').ajaxSubmit(option); 
						});
					}
				});
				$('#cancel_b').click(function() {
					data={};
					showMain();
				});
			});
		}
		//清空全局Ids
		dataId = [];
	});
	function forSearchCondition(){
		$("#condition1").change(function(){
			$("#input").css("background","white");
			hideInformation();
			$("#input").val("");
			$("#input").attr("disabled",false);
		});
	}
	$('#search_but').click(function(){
		$("#input").css("background","white");
		hideInformation();
		var con1=$('#condition1').val();
		clearSession("/business/score");
		if(con1==0){
			data={};
			showMain();
		}else{
			data={};
			var text=$('#input').val();
			if((con1=="1"&&text=="")||(con1=="2"&&text=="")){
				$("#input").css("background","#FF77AD");
				$("#input").focus();
				setTimeout('AlertInfo("请您填写相应的信息")',100);
			}else{
				if(text==""){
					text="null";
				}
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
		loadAllIds("/business/score");
		if(dataId.length==0||dataId=="null"){
			setTimeout('AlertInfo("请选择要查看的学生信息")',100);
		}else if(dataId.length>1){
			setTimeout('AlertInfo("请您选择单条学生信息")',100);
		}else{
			var year_str=$("#year_str").val();
			var term_str=$("#term_str").val();
			showLoading();
			$("#student_main").load("../business/score/toLook.action",{'student.id':dataId[0],'year_str':year_str,'term_str':term_str},function(){
				$("#userTitle").html("查看学生成绩");
				hideLoading();
				$("#studentbar").hide();
				$('#cancel_b').click(function() {
					data={};
					showMain();
				});
			});
		}
		//清空全局Ids
		dataId = [];
	});
	function greneralIds(){
		var i=0;
		$("#scoreList tbody").find("td").find("input").each(function(){
			$(this).attr("id","score"+i);
			i++;
		});
	}
	function showMain(){
		clearError('input');
		toDateList('student_main','studentbar','studentlist','/business/score',data,'id',generalIdList);
	}
});