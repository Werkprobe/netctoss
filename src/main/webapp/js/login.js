/**
 * login opertion
 */
$(function(){
	/**
	 * userName  Check
	 */
	$("#admin_code").blur(
			function() {
				var str = $("#admin_code").val();
				var bool = getRex(str)
				if (!bool) {
					$("span.error_name").text("Falsches Format.")
					$("span.errorMsg").text("Die eingegebenen userName stimmen nicht überein.");
					$("#admin_code").focus();
				} else {
					$("span.error_name").empty()
					$("span.errorMsg").empty()
				}
			});
	/**
	 * password  Check
	 */
	$("#pwd").blur(
			function() {
				var str = $("#pwd").val();
				var bool = getRex(str)
				if (!bool) {
					$("span.error_pwd").text("Falsches Format.")
					$("span.errorMsg").text("Die eingegebenen Passwörter stimmen nicht überein.");
					$("#pwd").focus();
				} else {
					$("span.error_pwd").empty();
					$("span.errorMsg").empty();
				}
			});
	/**
	 * Code wechseln
	 */
	$("#wechseln").click(function(){
		$("#img1").attr("src",$path()+"/user/getCode.do?"+Math.random());
	})
	/**
	 * Anmeldung
	 */
	$("#login_Btn").click(function(){
		var userName=$("#admin_code").val();
		var pwd=$("#pwd").val();
		var checkCode=$("#checkCode").val();
		$("span.errorMsg").empty()
		//Format kontrollieren
		if(!getRex(userName)){
			$("#admin_code").focus();
			$("span.errorMsg").text("Die eingegebenen userName stimmen nicht überein.");
			return;
		}
		if(!getRex(pwd)){
			$("#pwd").focus();
			$("span.errorMsg").text("Die eingegebenen Passwörter stimmen nicht überein.");
			return;
		}
		if(""==checkCode || null==checkCode){
			$("#checkCode").focus();
			$("span.error_cpatcha").text("nicth leer")
			$("span.errorMsg").text("Die eingegebenen Checkcode stimmen nicht überein.");
			return;
		}else{
			$("span.error_cpatcha").empty();
		}
		//login Action
		$.ajax({
			'url':$path()+'/user/tologin.do',
			'type':"post",
			'data':{'admin_code':userName,'pwd':pwd,'checkCode':checkCode},
			'dataType':'json',
			'success':function(data){
				if(data.status==102){
					$("#img1").attr("src",$path()+"/user/getCode.do?"+Math.random());	
					$("#checkCode").val("");
					$("#checkCode").focus();
					$("span.errorMsg").text("Die eingegebenen Checkcode stimmen nicht überein.");
				}else if(data.status==10 || data.status==101){
					$("#img1").attr("src",$path()+"/user/getCode.do?"+Math.random());	
					$("span.errorMsg").text("UserName orde passwörter ist falsch.");
				}else{
					//get admin_code
					// var admin_code=data.data.admin_code;
					window.location.href=$path()+"/pages/index.html";
				}
				
			},
			'error':function(){
				alert("System error. Bitte ein Moment wieder probieren");
			}
		});
	});
});
