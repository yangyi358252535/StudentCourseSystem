$(document).ready(function(){
								
	///// TRANSFORM CHECKBOX /////							
	$('input:checkbox').uniform();
	
	///// LOGIN FORM SUBMIT /////
	$('#login').submit(function(){
	
		if($('#username').val() == '' && $('#password').val() == '') {
			$('.nousername').fadeIn();
			$('.nopassword').hide();
			return false;	
		}
		if($('#username').val() != '' && $('#password').val() == '') {
			$('.nopassword').fadeIn().find('.userlogged h4, .userlogged a span').text($('#username').val());
			$('.nousername,.username').hide();
			return false;;
		}
	});
	
	///// ADD PLACEHOLDER /////
	$('#username').attr('placeholder','用户名');
	$('#password').attr('placeholder','密码');
});
