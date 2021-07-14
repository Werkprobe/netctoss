$(function(){
	 load_page();
	 $('#btn_save').click(function(){
		var real_name=$.trim($('#real_name').val());
		var idcard_no=$.trim($('#idcard_no').val());
		var login_name=$.trim($('#login_name').val());
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
		if(""==idcard_no || !getRexNum(idcard_no)){
			$('.idcard_no').attr('style','color:#f00;');
			return;
		}else{
			$('.idcard_no').attr('style','color:#000;');
		}
		if(""==login_name || login_name.length>30){
			$('.login_name').attr('style','color:#f00;');
			return;
		}else{
			$('.login_name').attr('style','color:#000;');
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
			'url':$path()+'/account/addAccount.do',
			'type':'post',
			'data':{'idcard_no':idcard_no,'login_name':login_name,'real_name':real_name,'login_passwd':login_passwd,'telephone':telephone,'mailaddress':mailaddress,'zipcode':zipcode,'gender':gender,'allow':'Konto'},
			'dataType':'json',
			'success':function(data){
				if(102==data.status){
					window.location.href=data.data;
					return;
				}else if(30==data.status){
					$('#operate_result_info').removeClass('operate_success').addClass('operate_fail');
				}else{
					$('#operate_result_info').removeClass('operate_fail').addClass('operate_success');
				}
				$('#operate_result_info').css('display','block').children('span').text(data.msg);
				setTimeout(function(){$('#operate_result_info').css('display','none')}, 3000);
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
});