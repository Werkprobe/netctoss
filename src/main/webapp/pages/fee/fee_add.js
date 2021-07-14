$(function(){
	 load_page();
	$('input[type=radio]').change(function(){
		var radFeeType=$(this).val();
		if(radFeeType==1){
			$("#basiszeit").attr("readOnly",true).addClass("readonly").val("");
			$("#stunden_satz").attr("readOnly",true).addClass("readonly").val("");
			$("#basiszins_satz").attr("readOnly",false).removeClass("readonly");
		}else if(radFeeType==3){
			$("#basiszeit").attr("readOnly",true).addClass("readonly").val("");
			$("#basiszins_satz").attr("readOnly",true).addClass("readonly").val("");
			$("#stunden_satz").attr("readOnly",false).removeClass("readonly");
		}else{
			$("#basiszeit").attr("readOnly",false).removeClass("readonly");
			$("#basiszins_satz").attr("readOnly",false).removeClass("readonly");
			$("#stunden_satz").attr("readOnly",false).removeClass("readonly");
		}
	});
	$('#btn_save').click(function(){
		var name=$("#tarif_Name").val().trim();
		var costtype=$('input[type=radio]:checked').val();
		var base_duration=$.trim($("#basiszeit").val());
		var base_cost=$.trim($("#basiszins_satz").val());
		var unit_cost=$.trim($("#stunden_satz").val());
		var descr=$.trim($("#area_tarif_desc").val());
		var status=0;
		if(""==name || name.length>20){
			$('.tarif_name').attr('style','color:#f00');
			return;
		}else{
			$('.tarif_name').attr('style','color:#000');
		}
		if('1'==costtype){
			if(isNumber(base_cost) && (0<=parseFloat(base_cost) && 99999.99>=parseFloat(base_cost))){
				$('.valid_basesatz').attr('style','color:#000');
			}else{
				$(".valid_basesatz").attr('style','color:#f00');
				return;
			}
		}else if('3'==costtype){
			if(isNumber(unit_cost) && (0<=parseFloat(unit_cost) && 99999.99>=parseFloat(unit_cost))){
				$('.valid_studentsatz').attr('style','color:#000');
			}else{
				$(".valid_studentsatz").attr('style','color:#f00');
				return;
			}
		}else{
			if(isIntNum(base_duration) && (1<=parseInt(base_duration) && 600>=parseInt(base_duration))){
				$('.valid_zeit').attr('style','color:#000');
			}else{
				$(".valid_zeit").attr('style','color:#f00');
				return;
			}
			if(isNumber(base_cost) && (0<=parseFloat(base_cost) && 99999.99>=parseFloat(base_cost))){
				$('.valid_basesatz').attr('style','color:#000');
			}else{
				$(".valid_basesatz").attr('style','color:#f00');
				return;
			}
			if(isNumber(unit_cost) && (0<=parseFloat(unit_cost) && 99999.99>=parseFloat(unit_cost))){
				$('.valid_studentsatz').attr('style','color:#000');
			}else{
				$(".valid_studentsatz").attr('style','color:#f00');
				return;
			}
		}
		$.ajax({
			'url':$path()+'/const/addCostInfo.do',
			'type':'post',
			'data':{'name':name,'costtype':costtype,'base_duration':base_duration,'base_cost':base_cost,'unit_cost':unit_cost,'descr':descr,'status':status,'allow':'Geschäft'},
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
	$('#btn_cancel').click(function(){
		window.location.href=$path()+"/pages/fee/fee_list.html";
	});
	$('#header a').click(function(){
		login_out();
	});
});