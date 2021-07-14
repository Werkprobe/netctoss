$(function(){
	function page_load(){
		$.ajax({
			'url':$path()+'/service/initCostArt.do',
			'type':'get',
			'data':{'allow':'Dienst'},
			'dataType':'json',
			'async':false,
			'success':function(data){
				if(20==data.status){
					$("#cost_typ select").empty();
					var costType=data.data;
					for(var key in costType){
						var $str=$('<option value="'+key+'">'+costType[key]+'</option>');
						$("#cost_typ select").append($str)
					}
					$("#cost_typ select").val(data.data.cost_id);
				}else if("102"==data.status){
					window.location.href=data.data;
				}
			},
			'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
		});
	}
	function findAccount(){
		var idcard_no=$.trim($('#txt_ausweis').val());
		$.ajax({
			'url':$path()+'/service/findAccountByCard.do',
			'type':'post',
			'data':{'idcard_no':idcard_no,'allow':'Dienst'},
			'dataType':'json',
			'success':function(data){
				if(20==data.status){
					var codeInfo=data.data.split(',')
					$('#txt_login_name').val(codeInfo[0]);
					$('#txt_login_name').data('account_id',codeInfo[1]);
					$('#validata_msg').text("");
				}else if(103==data.status){
					$('#txt_ausweis').focus().val("");
					$('#txt_login_name').val("");
					$('#validata_msg').text(data.msg).attr('style','color:#FF0000');
				}else if("102"==data.status){
						window.location.href=data.data;
				}
			},
			'error':function(){
				alert("System error. Bitte ein Moment wieder probieren");
			}
		});
	}
	$('#unix_host').blur(function(){
		var unix_host=$.trim($(this).val());
		if(!isIpAdress(unix_host)){
			$('#validate_IP_msg').text('Bitte, geben Sie effektive IP-Adresse ein?').attr('style','color:#FF0000');
			$('#unix_host').focus();
		}else{
			$('#validate_IP_msg').text('IP-Adress').removeAttr('style');
		}
	});
	$('#os_username').blur(function(){
		var os_username=($(this).val());
		if(!getRexFormat(os_username)){
			$("#validata_osusername_msg").attr('style','color:#FF0000');
			$('#os_username').focus();
		}else{
			$("#validata_osusername_msg").removeAttr('style');
		}
	});
	$('#input_pwd').blur(function(){
		var pwd=$.trim($(this).val());
		if(!getRexFormat(pwd)){
			$("#validata_pwd_msg").attr('style','color:#FF0000');
			$('#input_pwd').focus();
		}else{
			$("#validata_pwd_msg").removeAttr('style');
		}
	});
	$('#input_wpwd').blur(function(){
		var pwd=$.trim($('#input_pwd').val());
		var wpwd=$.trim($(this).val());
		if(pwd!=wpwd){
			$("#validata_wpwd_msg").attr('style','color:#FF0000');
			$('#input_pwd').focus();
		}else{
			$("#validata_wpwd_msg").removeAttr('style');
		}
	});
	$('#btn_abrechen').click(function(){
		window.location.href=$path()+"/pages/service/service_list.html";
	});
	$('#btn_save').click(function(){
		var idcard_no=$.trim($('#txt_ausweis').val());
		var unix_host=$.trim($('#unix_host').val());
		var os_username=$.trim($('#os_username').val());
		var login_passwd=$.trim($('#input_pwd').val());
		var cost_id=$.trim($('#cost_typ select').val());
		var account_id=$('#txt_login_name').data('account_id');
		var wpwd=$('#input_wpwd').val();
		if(""==idcard_no){
			$('#txt_ausweis').focus();
			return;
		}
		if(""==unix_host){
			$('#unix_host').focus();
			$('#validate_IP_msg').attr('style','color:#FF0000');
			return;
		}
		if(""==os_username){
			$('#os_username').focus();
			$("#validata_osusername_msg").attr('style','color:#FF0000');
			return;
		}
		if(""==login_passwd){
			$('#input_pwd').focus();
			$("#validata_pwd_msg").attr('style','color:#FF0000');
			return;
		}
		if(login_passwd!=wpwd){
			$('#input_pwd').val("").focus();
			$('#input_wpwd').val("");
			$("#validata_wpwd_msg").attr('style','color:#FF0000');
			return;
		}
		$.ajax({
			'url':$path()+'/service/addService.do',
			'type':'post',
			'data':{'unix_host':unix_host,'os_username':os_username,'login_passwd':login_passwd,'cost_id':cost_id,'account_id':account_id,'allow':'Dienst'},
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
				setTimeout(function(){$('#operate_result_info').css('display','none')}, 6000);
			},
			'error':function(data){
				
			}
		});
		
	});
	$('#header a').click(function(){
		login_out();
	});
	$('#btn_suchen').bind('click',findAccount);
	$('#txt_ausweis').bind('blur',findAccount);
	page_load();
});