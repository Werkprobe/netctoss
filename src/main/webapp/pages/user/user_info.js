$(function(){
	
	$("#telefonNummer").blur(function(){
		var telefon=$("#telefonNummer").val();
		if(!getRexTele(telefon)){
			$(".telefonValidate").text("Es passt nicht auf das Format von Telefon");
			$(".telefonValidate").css("color","#f00");
		}else{
			$(".telefonValidate").text("Es passt auf das Format von Telefon");
			$(".telefonValidate").css("color","#000");
		}
	});
	$("#userName").blur(function(){
		var userName=$('#userName').val().trim();
		if(""==userName){
			$("span.required").text("Name ist nicht leer");
		}else{
			$("span.required").text("*");
		}
	});
	
	$("#eMail").blur(function(){
		var email=$("#eMail").val();
		if(!getRexEmail(email)){
			$(".eMailValidate").text("Es passt nicht auf das Format von E-Mail");
			$(".eMailValidate").css("color","#f00");
		}else{
			$(".eMailValidate").text("Es passt auf das Format von E-Mail");
			$(".eMailValidate").css("color","#000");
		}
	});
	function page_load(){
		$.ajax({
			'url':$path()+'/user/findAdminInfo.do',
			'type':'post',
			'data':{'allow':'all'},
			'dataType':'json',
			'success':function(data){
				if(20==data.status){
					$('#adminCode').val(data.data.admin_code);
					$('#userName').val(data.data.name);
					$('#telefonNummer').val(data.data.telephone);
					$('#eMail').val(data.data.email);
					$('#roleInfo').val(data.data.role_name)
					$('#createDate').val(data.data.enrolldate);
				}else if(102==data.status){
					window.location.href=data.data;
				}
			},
			'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
		});
	}
	$("#saveUserInfo").click(function() {
		var userName=$('#userName').val().trim();
		var telefonNummer=$('#telefonNummer').val().trim();
		var email=$("#eMail").val().trim();
		if(""==userName){
			$("span.required").text("Name ist nicht leer");
			return;
		}
		if(!getRexTele(telefonNummer)){
			$(".telefonValidate").text("Es passt nicht auf das Format von Telefon");
			$(".telefonValidate").css("color","#f00");
			return;
		}
		if(!getRexEmail(email)){
			$(".eMailValidate").text("Es passt nicht auf das Format von E-Mail");
			$(".eMailValidate").css("color","#f00");
			return;
		}
		$.ajax({
			'url':$path()+'/user/modifyAdminInfo.do',
			'type':'post',
			'data':{"userName":userName,"telefonNummer":telefonNummer,"email":email,'allow':'all'},
			'dataType':'json',
			'success':function(data){
				if(101==data.status){
					$("#save_result_info").removeClass("save_success");
					$("#save_result_info").addClass("save_fail");
				}if(102==data.status){
					window.location.href=data.data;
				}else{
					$("#save_result_info").removeClass("save_fail");
					$("#save_result_info").addClass("save_success");
				}
				$('input[type=password]').val('');
				$("#save_result_info").text(data.msg);
				$("#save_result_info").css("display","block");
				 window.setTimeout("showResultDiv('save_result_info',false)", 3000);
			},
			'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
		});
		
	});
	$("#abrechen").click(function(){
		window.location.href=$path()+"/pages/index.html";
	});
	$('#header a').click(function(){
		login_out();
	});
	page_load();
});