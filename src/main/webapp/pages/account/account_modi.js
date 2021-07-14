$(function(){
	/**
	 * page Init
	 * @param num
	 * @returns
	 */
	function page_load(){
		var paramStr=getPageParamur(window.location.href)
		paramJson=JSON.parse(paramStr);
		$.ajax({
			'url':$path()+'/account/findAccountDetail.do',
			'type':'post',
			'data':{'id':paramJson.id,'allow':'Konto'},
			'dataType':'json',
			'success':function(data){
				if(20==data.status){
					$('input[type=hidden]').val(data.data.id);
					$('#real_name').val(data.data.real_name);
					$('#idcard_no').val(data.data.idcard_no);
					$('#login_name').val(data.data.login_name);	
					$('#login_passwd').val(data.data.login_passwd);
					$('#wiederpasswort').val(data.data.login_passwd);
					var gender=data.data.gender
					var obj=$('input[type=radio]');
					for(var i=0;i<obj.length;i++){
						if(gender==obj.eq(i).val()){
							obj.eq(i).attr('checked','checked');
						}
					}
					$('#telephone').val(data.data.telephone);
					$('#mailaddress').val(data.data.mailaddress);
					$('#zipcode').val(data.data.zipcode);
				}else if("30"==data.status){
					alert("die Daten ist leer.");
					window.location.href=data.msg;
				}else if("102"==data.status){
					window.location.href=data.data;
				}
			},
			'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
		});
	}
	$('#btn_update').click(function(){
		var id=$.trim($('input[type=hidden]').val());
		var real_name=$.trim($('#real_name').val());
		var login_passwd=$.trim($('#login_passwd').val());
		var wiederpasswort=$.trim($('#wiederpasswort').val());
		var telephone=$.trim($('#telephone').val());
		var mailaddress=$.trim($('#mailaddress').val());
		var zipcode=$.trim($('#zipcode').val());
		var gender=$('input[type="radio"]:checked').val();
		if(""==real_name || real_name.length>20){
			$('.real_name').attr('style','color:#f00;');
			return;
		}else{
			$('.real_name').attr('style','color:#000;');
		}
		if(""==login_passwd || login_passwd.length>30){
			$('.login_passwd').attr('style','color:#f00;');
			return;
		}else{
			$('.login_passwd').attr('style','color:#000;');
		}
		if(login_passwd!=wiederpasswort){
			$('.wiederpsd').attr('style','color:#f00;');
			return;
		}else{
			$('.wiederpsd').attr('style','color:#000;');
		}
		if(!getRexTele(telephone)){
			$('.telefon_format_msg').attr('style','color:#f00;');
			return;
		}else{
			$('.telefon_format_msg').attr('style','color:#000;');
		}
		$.ajax({
			'url':$path()+'/account/modifyAccountInfo.do',
			'type':'post',
			'data':{'id':id,'real_name':real_name,'login_passwd':login_passwd,'telephone':telephone,'mailaddress':mailaddress,'zipcode':zipcode,'gender':gender,'allow':'Konto'},
			'dataType':'json',
			'success':function(data){
				if("20"==data.status){
					$('#operate_result_info').removeClass('operate_fail').addClass('operate_success');
				}else if("30"==data.status){
					$('#operate_result_info').removeClass('operate_success').addClass('operate_fail');
				}else if("102"==data.status){
					window.location.href=data.data;
				}
				$("#operate_result_info").css('display', 'block').children('span').text(data.msg); 
				setTimeout(function(){ $("#operate_result_info").css('display', 'none')},3000);
			},
			'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
		});
		
	});
	$("#abrechen").click(function(){
		window.location.href=$path()+"/pages/account/account_list.html";
	});
	$('#header a').click(function(){
		login_out();
	});
	page_load();
});