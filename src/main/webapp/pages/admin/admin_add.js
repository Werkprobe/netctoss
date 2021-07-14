$(function(){
	function page_load(){
		//get param
		//var privilegeId=$('#selModules').val();
		//var roleName=$('#roleName').val();
		$.ajax({
			'url':$path()+'/admin/getRoleInfoList.do',
			'type':'post',
			'data':{'allow':'Admin'},
			'dataType':'json',
			'async':false,
			'success':function(data){
				if(20==data.status){
					$('.input_info_scroll ul').empty();
					var roleList=data.data;
					for(var i=0;i<roleList.length;i++){
						$('.input_info_scroll ul').append('<li><input type="checkbox" value="'+roleList[i].id+'" name="chk_role" />'+roleList[i].name+'</li>');
					}
				}else if("102"==data.status){
					window.location.href=data.data;
				}
			},
			'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
		});
	};
	$("#abrechen").click(function(){
		window.location.href=$path()+"/pages/admin/admin_list.html";
	});
	$("#btn_save").click(function(){
		var adminName=$('#admin_name').val().trim();
		if(""==adminName || adminName.length>20){
			$('.admin_name').attr('style','color:#f00;display:block;');
			return;
		}else{
			$('.admin_name').attr('style','color:#000;display:none;');
		}
		var adminCode=$('#admin_code').val().trim();
		var bool=getRex(adminCode)
		if(!bool){
			$('.admin_code').attr('style','color:#f00');
			return;
		}else{
			$('.admin_code').attr('style','color:#000');
		}
		var pwd=$('#pwd').val().trim();
		var comfirm_pwd=$('#comfirm_pwd').val().trim();
		bool=getRex(pwd);
		if(!bool){
			$('.pwd').attr('style','color:#f00');
			return;
		}else{
			$('.pwd').attr('style','color:#000');
		}
		bool=getRex(comfirm_pwd);
		if(!bool){
			$('.comfirm_pwd').attr('style','color:#f00');
			$('.comfirm_pwd').text('Max.30 und A-z、a-z oder 0-9 zusammensetzen.');
			return;
		}else{
			$('.comfirm_pwd').attr('style','color:#000');
			$('.comfirm_pwd').text('Zwei Passwörter mussen Gleiche Sein.');
		}
		if(pwd!=comfirm_pwd){
			$('.comfirm_pwd').attr('style','color:#f00');
			$('.comfirm_pwd').text('Zwei Passwörter mussen Gleiche Sein.');
			return;
		}else{
			$('.comfirm_pwd').attr('style','color:#000');
		}
		var telefon=$('#telefon').val().trim();
		if(!getRexTele(telefon)){
			$('.telefon').attr('style','color:#f00');
			return;
		}else{
			$('.telefon').attr('style','color:#000');
		}
		var email=$('#email').val();
		if(!getRexEmail(email)){
			$('.email').attr('style','color:#f00');
			return;
		}else{
			$('.email').attr('style','color:#000');
		}
		var role="";
		$('input[type="checkbox"]:checked').each(function(i){
			role+=$(this).val()+",";
		});
		if(""==role){
			$('.role').attr('style','color:#f00');
			return;
		}else{
			$('.role').attr('style','color:#000');
		}
		role=role.substring(0,role.lastIndexOf(','));
		$.ajax({
			'url':$path()+'/admin/addAdminRoleList.do',
			'type':'post',
			'data':{'admin_name':adminName,'admin_code':adminCode,'password':pwd,'telephone':telefon,'email':email,'roleIdList':role,'allow':'Admin'},
			'dataType':'json',
			'success':function(data){
				if("20"==data.status){
					$('#operate_result_info').removeClass('operate_fail').addClass('operate_success');
				}else if("30"==data.status){
					$('#operate_result_info').removeClass('operate_success').addClass('operate_fail');
					$('.admin_code').attr('style','color:#f00');
					$('#admin_code').val("");
					
				}else if("102"==data.status){
					window.location.href=data.data;
				}
				$('#operate_result_info').css('display','block')
				$('#operate_result_info span').text(data.msg); 
			},
			'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
		});
	});
	$('#header a').click(function(){
		login_out();
	});
	 page_load();
});