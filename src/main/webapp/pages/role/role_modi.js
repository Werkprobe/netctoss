$(function(){
	function page_load(){
		var paramStr=getPageParamur(window.location.href)
		paramJson=JSON.parse(paramStr);
		$.ajax({
			'url':$path()+'/rolemangement/findRoleDetail.do',
			'type':'post',
			'data':{'roleId':paramJson.id,'allow':'Role'},
			'dataType':'json',
			'success':function(data){
				if(20==data.status){
					$('input[type=hidden]').val(data.data.roleId);
					$('#role_name').val(data.data.roleName);
					var roleGewalt=data.data.roleBesitz;
					var roleBesitz=roleGewalt.split(',');
					for(var i=0;i<roleBesitz.length;i++){
						$("input[name='role_gewalt']").each(function(){
							if($(this).val()==roleBesitz[i]){
								$(this).attr("checked","checked");
							}
						});
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
	
	$('#modifyRole').click(function() {
		var rolleName=$('#role_name').val();
		var gewalt="";
		var bool=getRex(rolleName);
		if(!bool){
			$('#save_result_info').removeClass('save_success').addClass('save_fail');
			$('#save_result_info').css('display','block').text('Rollenname ist falsch.');
			setTimeout(function(){$('#save_result_info').css('display','none')}, 3000);
			return;
		}
		$('input[type="checkbox"]:checked').each(function(i){
			gewalt+=$(this).val()+",";
		});
		if(""==gewalt){
			$('#save_result_info').removeClass('save_success').addClass('save_fail');
			$('#save_result_info').css('display','block').text('die Berechtigungen muss mindestens Ein wählen.');
			setTimeout(function(){$('#save_result_info').css('display','none')}, 3000);
			return;
		}
		gewalt=gewalt.substring(0,gewalt.lastIndexOf(','));
		var rolleId=$('input:hidden').val();
		$.ajax({
			'url':$path()+'/rolemangement/modifyRGInfo.do',
			'data':{'roleId':rolleId,'roleName':rolleName,'gewalt':gewalt,'allow':'Role'},
			'type':'post',
			'async':false,
			'dataType':'json',
			'success':function(data){
				if(102==data.status){
					window.location.href=data.data;
					return;
				}else if(30==data.status){
					$('#save_result_info').removeClass('save_success').addClass('save_fail');
				}else{
					$('#save_result_info').removeClass('save_fail').addClass('save_success');
				}
				$('#save_result_info').css('display','block').text(data.msg); 
				setTimeout(function(){$('#save_result_info').css('display','none')}, 3000);
			},
			'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
		});
	});
	$("#abrechen").click(function(){
		window.location.href=$path()+"/pages/role/role_list.html";
	});
	$('#header a').click(function(){
		login_out();
	});
	page_load();
});