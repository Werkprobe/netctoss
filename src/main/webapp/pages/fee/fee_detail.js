$(function(){
	function page_load(){
		var paramStr=getPageParamur(window.location.href)
		paramJson=JSON.parse(paramStr);
		$.ajax({
			'url':$path()+'/const/findCostDetail.do',
			'type':'post',
			'data':{'id':paramJson.id,'allow':'Geschäft'},
			'dataType':'json',
			'success':function(data){
				if(20==data.status){
					$("#tarif_Name").val(data.data.name)
					var costStatus=data.data.status;
					var obj=$("#cost_status option");
					for(var i=0;i<obj.length;i++){
						if(obj.eq(i).val()==costStatus){
							obj.eq(i).attr("selected","selected");
							break;
						}
					}
					var radFeeType=data.data.costtype;
					$(":radio").each(function(){
					    var valType=$(this).val();
					    if(valType==radFeeType){
					    	$(this).attr("checked","checked");
					    }
					});
					$("#basiszeit").val(data.data.base_duration);
					$("#basiszins_satz").val(data.data.base_cost);
					$("#stunden_satz").val(data.data.unit_cost);
					$("#startime").val(data.data.startime);
					$("#creatime").val(data.data.creatime);
					$("#area_tarif_desc").val(data.data.descr);
				}else if("30"==data.status){
					alert("Die Daten sind leer.");
					window.location.href=data.msg;
				}else if("102"==data.status){
					window.location.href=data.data;
				}
			},
			'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
		});
	}
	$("#btn_back").click(function(){
		window.location.href=$path()+"/pages/fee/fee_list.html";
	});
	$('#header a').click(function(){
		login_out();
	});
	 page_load();
});