$(function(){
	function page_load(){
		var paramStr=getPageParamur(window.location.href)
		paramJson=JSON.parse(paramStr);
		$.ajax({
			'url':$path()+'/service/loadServiceModi.do',
			'type':'post',
			'data':{'id':paramJson.id,'allow':'Dienst'},
			'dataType':'json',
			'success':function(data){
				if(20==data.status){
					$('#account_id').val(data.data.account_id);
					$('#account_id').data("id",data.data.id);
					$('#login_name').val(data.data.login_name);
					$('#unix_host').val(data.data.unix_host);	
					$('#email').val(data.data.email);
					var costList=data.data.costList;
					$("#cost_typ select").empty();
					for(var key in costList){
						var $str=$('<option value="'+key+'">'+costList[key]+'</option>');
						$("#cost_typ select").append($str)
					}
					$("#cost_typ select").val(data.data.cost_id);
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
	$("#btn_abrechen").click(function(){
		window.location.href=$path()+"/pages/service/service_list.html";
	});
	$("#btn_save").click(function(){
		var cost_id=$("#cost_typ select").val();
		var id=	$('#account_id').data("id");
		var os_username=$('#login_name').val();
		var unix_host=$('#unix_host').val();
		$.ajax({
			'url':$path()+'/service//modifyCostTyp.do',
			'type':'post',
			'data':{'cost_id':cost_id,'id':id,'os_username':os_username,'unix_host':unix_host,'allow':'Dienst'},
			'dataType':'json',
			'success':function(data){
				if("20"==data.status){
					$('#operate_result_info').removeClass('operate_fail').addClass('operate_success');
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