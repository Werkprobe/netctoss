$(function(){
	$(".password_page").click(function() {
		var admin_code=$("#amdin_code").val();
		window.location.href=$path()+"/pages/user/user_modi_pwd.html?admin_code="+admin_code;
	});
	load_page();
});