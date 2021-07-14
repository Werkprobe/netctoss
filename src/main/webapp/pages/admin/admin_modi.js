$(function(){
	function page_load(){
		var paramStr=getPageParamur(window.location.href)
		paramJson=JSON.parse(paramStr);
		$.ajax({
			'url':$path()+'/admin/findAdminRoleByid.do',
			'type':'post',
			'data':{'adminId':paramJson.id,'allow':'Admin'},
			'dataType':'json',
			'success':function(data){
				if(20==data.status){
					$('input[type=hidden]').val(data.data.id);
					$('#adminName').val(data.data.name);
					$('#telefon').val(data.data.telephone);
					$('#amdinCode').val(data.data.admin_code);	
					$('#email').val(data.data.email);
					var adminRoleIdList=data.data.role_id.split(',');
					var roleList=data.data.roleList;
					$(".input_info_scroll ul:first").empty();
					for(var i=0;i<roleList.length;i++){
						var bool=false;
						for(var j=0;j<adminRoleIdList.length;j++){
							if(adminRoleIdList[j]==roleList[i].id){
								bool=true;
							}							
						}
						if(bool){
							$(".input_info_scroll ul:first").append('<li><input type="checkbox" name="role" checked="checked" value="'+roleList[i].id+'"/>'+roleList[i].name+'</li>');
						}else{
							$(".input_info_scroll ul:first").append('<li><input type="checkbox" name="role" value="'+roleList[i].id+'"/>'+roleList[i].name+'</li>');
						}
					}
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
	$('#btn_save').click(function() {
		var id=$('input:hidden').val();
		var adminName=$('#adminName').val().trim();
		if(""==adminName){
			$('#save_result_info').removeClass('save_success').addClass('save_fail');
			$('#save_result_info').css('display','block').text('Name ist nicht leer');
			setTimeout(function(){$('#save_result_info').css('display','none')}, 3000);
			return;
		}
		var telefon=$('#telefon').val();
		if(!getRexTele(telefon)){
			$('#save_result_info').removeClass('save_success').addClass('save_fail');
			$('#save_result_info').css('display','block').text('Es passt nicht auf das Format von Telefon.');
			setTimeout(function(){$('#save_result_info').css('display','none')}, 3000);
			return;
		}
		var email=$('#email').val();
		if(!getRexEmail(email)){
			$('#save_result_info').removeClass('save_success').addClass('save_fail');
			$('#save_result_info').css('display','block').text('Es passt nicht auf das Format von E-Mail.');
			setTimeout(function(){$('#save_result_info').css('display','none')}, 3000);
			return;
		}
		var role="";
		$('input[type="checkbox"]:checked').each(function(i){
			role+=$(this).val()+",";
		});
		if(""==role){
			$('#save_result_info').removeClass('save_success').addClass('save_fail');
			$('#save_result_info').css('display','block').text('die Berechtigungen muss mindestens Ein wählen.');
			setTimeout(function(){$('#save_result_info').css('display','none')}, 3000);
			return;
		}
		role=role.substring(0,role.lastIndexOf(','));
		$.ajax({
			'url':$path()+'/admin/modifyAdminRoleInfo.do',
			'type':'post',
			'data':{'id':id,'adminName':adminName,'telefon':telefon,'email':email,'roleInfo':role,'allow':'Admin'},
			'dataType':'json',
			'success':function(data){
				if("20"==data.status){
					$('#save_result_info').removeClass('save_fail').addClass('save_success');
				}else if("30"==data.status){
					$('#save_result_info').removeClass('save_success').addClass('save_fail');
				}else if("102"==data.status){
					window.location.href=data.data;
				}
				$('#save_result_info').css('display','block').text(data.msg); 
				setTimeout(function(){$('#save_result_info').css('display','none')}, 3000);
			},
			'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
		});
		
	});
	$("#abrechen").click(function(){
		window.location.href=$path()+"/pages/admin/admin_list.html";
	});
	$('#header a').click(function(){
		login_out();
	});
	page_load();
})