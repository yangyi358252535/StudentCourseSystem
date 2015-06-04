$(document).ready(function() {
	$("#leftmenu ul li a ").each(function() {
		$(this).click(function() {
			if ($(this).parent().find("ul").is(":hidden")) {
				$("#leftmenu ul li ul").each(function() {
					$(this).slideUp();
				});
				$(this).parent().find("ul").slideDown();
			}
		});
	});
	$("#leftmenu ul li ul li a ").click(function() {
		$(this).parent().parent().parent().parent().parent().find("ul li").each(function(){
			 $(this).attr("class","");
		});
		$(this).parent().parent().parent().parent().parent().find("ul li ul li").each(function(){
			 $(this).attr("class","");
		});
		 $(this).parent().attr("class","current");
		 $(this).parent().parent().parent().attr("class","current");
		var url = $(this).attr("name");
		clearSession("/system/manager");
		$("#main").load(url, function() {
		});
		// 清空翻页Id数组和AllID数组
		arrayId = [];
		dataId = [];
	});
	if($(document).width() < 640) {
		$('.togglemenu').addClass('togglemenu_collapsed');
		if($('.vernav').length > 0) {
			
			$('.iconmenu').addClass('menucoll');
			$('body').addClass('withmenucoll');
			$('.centercontent').css({marginLeft: '56px'});
			if($('.iconmenu').length == 0) {
				$('.togglemenu').removeClass('togglemenu_collapsed');
			} else {
				$('.iconmenu > ul > li > a').each(function(){
					var label = $(this).text();
					$('<li><span>'+label+'</span></li>')
						.insertBefore($(this).parent().find('ul li:first-child'));
				});		
			}

		} else {
			
			$('.iconmenu').addClass('menucoll2');
			$('body').addClass('withmenucoll2');
			$('.centercontent').css({marginLeft: '36px'});
			
			$('.iconmenu > ul > li > a').each(function(){
				var label = $(this).text();
				$('<li><span>'+label+'</span></li>')
					.insertBefore($(this).parent().find('ul li:first-child'));
			});		
		}
	}
	$('.userinfo').click(function(){ 
		if(!$(this).hasClass('active')) {
			$('.userinfodrop').show();
			$(this).addClass('active');
		} else {
			$('.userinfodrop').hide();
			$(this).removeClass('active');
		}
		return false;
	});
	$(document).click(function(event) {
		var ud = $('.userinfodrop');
		var nb = $('.noticontent');
		
		//hide user drop menu when clicked outside of this element
		if(!$(event.target).is('.userinfodrop') 
			&& !$(event.target).is('.userdata') 
			&& ud.is(':visible')) {
				ud.hide();
				$('.userinfo').removeClass('active');
		}
		//hide notification box when clicked outside of this element
		if(!$(event.target).is('.noticontent') && nb.is(':visible')) {
			nb.remove();
			$('.notification').removeClass('active');
		}
	});
	$('.togglemenu').click(function(){
		if(!$(this).hasClass('togglemenu_collapsed')) {
			
			//if($('.iconmenu').hasClass('vernav')) {
			if($('.vernav').length > 0) {
				if($('.vernav').hasClass('iconmenu')) {
					$('body').addClass('withmenucoll');
					$('.iconmenu').addClass('menucoll');
				} else {
					$('body').addClass('withmenucoll');
					$('.vernav').addClass('menucoll').find('ul').hide();
				}
			} else if($('.vernav2').length > 0) {
			//} else {
				$('body').addClass('withmenucoll2');
				$('.iconmenu').addClass('menucoll2');
			}
			
			$(this).addClass('togglemenu_collapsed');
			
			$('.iconmenu > ul > li > a').each(function(){
				var label = $(this).text();
				$('<li><span>'+label+'</span></li>')
					.insertBefore($(this).parent().find('ul li:first-child'));
			});
		} else {
			
			//if($('.iconmenu').hasClass('vernav')) {
			if($('.vernav').length > 0) { 
				if($('.vernav').hasClass('iconmenu')) {
					$('body').removeClass('withmenucoll');
					$('.iconmenu').removeClass('menucoll');
				} else {
					$('body').removeClass('withmenucoll');
					$('.vernav').removeClass('menucoll').find('ul').show();
				}
			} else if($('.vernav2').length > 0) {	
			//} else {
				$('body').removeClass('withmenucoll2');
				$('.iconmenu').removeClass('menucoll2');
			}
			$(this).removeClass('togglemenu_collapsed');	
			
			$('.iconmenu ul ul li:first-child').remove();
		}
	});
	$('.menucoll > ul > li, .menucoll2 > ul > li').live('mouseenter mouseleave',function(e){
		if(e.type == 'mouseenter') {
			$(this).addClass('hover');
			$(this).find('ul').show();	
		} else {
			$(this).removeClass('hover').find('ul').hide();	
		}
	});
	
	
	///// HORIZONTAL NAVIGATION (AJAX/INLINE DATA) /////	
	
	$('.hornav a').click(function(){
		
		//this is only applicable when window size below 450px
		if($(this).parents('.more').length == 0)
			$('.hornav li.more ul').hide();
		
		//remove current menu
		$('.hornav li').each(function(){
			$(this).removeClass('current');
		});
		
		$(this).parent().addClass('current');	// set as current menu
		
		var url = $(this).attr('href');
		if($(url).length > 0) {
			$('.contentwrapper .subcontent').hide();
			$(url).show();
		} else {
			$.post(url, function(data){
				$('#contentwrapper').html(data);
				$('.stdtable input:checkbox').uniform();	//restyling checkbox
			});
		}
		return false;
	});
	$("#logout").click(function() {
		$.ajax({
			url : '../system/manager/logout.action',
			type : 'POST',// html
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(data1) {
				window.location.replace("../login.jsp");
			},
			complete : function() {
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(errorThrown + " " + textStatus);
			}
		});
	});
	$("#profile").click(function() {
		$("#myModal").load("../homePage/configeration.jsp", function() {
			openMyModel(700, 450, "myModal");
		});
	});
});
