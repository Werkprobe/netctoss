$(function(){
	//load_page
	load_page();
	/**
	 * Aktuell pwd  Check
	 */
	$("#aktuell_pwd").blur(
			function() {
				var str = $("#aktuell_pwd").val();
				var bool = getRex(str)
				if (!bool) {
					$("div.aktuell_pwd").text("Falsches Format.");
					$("div.aktuell_pwd").css("color","#ff0000");
				} else {
					$("div.aktuell_pwd").text("A-z、a-z、_、0-9 und Max.30");
					$("div.aktuell_pwd").css("color","#000");
				}
	});
	$("#neu_pwd").blur(
			function() {
				var str = $("#neu_pwd").val();
				var bool = getRex(str)
				if (!bool) {
					$("div.neu_pwd").text("Falsches Format.");
					$("div.neu_pwd").css("color","#ff0000");
				} else {
					$("div.neu_pwd").text("A-z、a-z、_、0-9 und Max.30");
					$("div.neu_pwd").css("color","#000");
				}
	});
	$("#confirm_pwd").blur(
			function() {
				var str = $("#confirm_pwd").val();
				var bool = getRex(str)
				if (!bool) {
					$("div.confirm_pwd").text("Falsches Format.");
					$("div.confirm_pwd").css("color","#ff0000");
				} else {
					$("div.confirm_pwd").text("Das Passwort muss gleich sein");
					$("div.confirm_pwd").css("color","#000");
				}
	});
	$("#savePwd").click(function(){
			//get param
			var aktuell_pwd=$("#aktuell_pwd").val();
			var neu_pwd=$('#neu_pwd').val();
			var confirm_pwd=$('#confirm_pwd').val();
			var bool = getRex(aktuell_pwd);
			if(!bool){
				$("div.aktuell_pwd").text("Falsches Format.");
				$("div.aktuell_pwd").css("color","#ff0000");
				return;
			}
			 bool = getRex(neu_pwd);
			 if(!bool){
				 $("div.neu_pwd").text("Falsches Format.");
				 $("div.neu_pwd").css("color","#ff0000");
				 return;
			 }
			 bool = getRex(neu_pwd);
			 if(!bool){
				 $("div.confirm_pwd").text("Falsches Format.");
				 $("div.confirm_pwd").css("color","#ff0000");
				 return;
			 }
			 if(neu_pwd!=confirm_pwd){
				 $("#confirm_pwd").val("");
				 $("div.confirm_pwd").css("color","#ff0000");
				 return;
			 }
			$.ajax({
				'url':$path()+'/user/modifypwd.do',
				'type':'post',
				'data':{"aktuell_pwd":aktuell_pwd,"neu_pwd":neu_pwd,"confirm_pwd":confirm_pwd,'allow':'all'},
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
});
