$(function(){
	load_page();
	$('#addRole').click(function(){
		var rolleName=$('#rolleName').val();
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
		$.ajax({
			'url':$path()+'/rolemangement/addRoleInfo.do',
			'data':{'rolleName':rolleName,'gewalt':gewalt,'allow':'Role'},
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
});