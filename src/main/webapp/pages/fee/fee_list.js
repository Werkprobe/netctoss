$(function(){
	function addTabElement(row,data,startime,status){
		var trElement='<tr>'+
		  '<td>'+row+'<span style="display:none">'+data.id+'</span></td>'+
		  '<td>'+data.name+'</td>'+
		 '<td>'+data.base_duration+'</td>'+
		 '<td>'+data.base_cost+'</td>'+
		 '<td>'+data.unit_cost+'</td>'+
		 '<td>'+data.creatime+'</td>'+
		 '<td>'+startime+'</td>'+
		 '<td>'+status+'</td>';
		if(data.status==1){
			trElement+='<td></td>';
		}else{
			trElement+='<td>'+
						'<input type="button" value="Start"  class="btn_start"/>'+
						'<input type="button" value="Ändern"   class="btn_modify"/>'+
						'<input type="button" value="Löschen"  class="btn_delete" />'+
						'</td>'
		}
		trElement+='</tr>';    
		return trElement;	
	}
	function page_load(pageIndex,bool){
		//get param
		var sortField=$('#sortField').val();
		var sortType=$('#sortType').val();
		$.ajax({
			'url':$path()+'/const/findCostListInfo.do',
			'type':'post',
			'data':{'orderField':sortField,'orderType':sortType,'pageIndex':pageIndex,'allow':'Geschäft'},
			'dataType':'json',
			'async':false,
			'success':function(data){
				if(20==data.status){
					var pageCount=data.data.pageCount;
					$('#datalist').children('tbody').empty();
					for(var i=0;i<data.data.costList.length;i++){
						var row=i+1+pageIndex*5;
						var startime=data.data.costList[i].startime==null?'':data.data.costList[i].startime;
						var status=data.data.costList[i].status==1?'Start':'Unterbrechen';
						$('#datalist').children('tbody').append(addTabElement(row,data.data.costList[i],startime,status));
					}
					if(bool){
						$('#pages').empty().append("<a href='javascript:;' id='repage'><</a>");
						var isnotclass=true;
						for(var i=1;i<=pageCount;i++){
							if(isnotclass){
								isnotclass=false;
								$('#pages').append("<a href='javascript:;' class='current_page'>"+i+"</a>")
							}else{
								$('#pages').append("<a href='javascript:;' >"+i+"</a>")
							}	
						}
						$('#pages').append("<a href='javascript:;' id='nextpage' >></a>");
					}
				}else if(103==data.status){
					$('#datalist').children('tbody').empty();
					$('#datalist').children('tbody').append('<tr><td colspan="9" style="color:#f00;font-weight:bold;">'+data.msg+'</td></tr>')
				}else if("102"==data.status){
					window.location.href=data.data;
				}
			},
			'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
		});
	};
	$('#btn_cost').click(function(){
		var btn_class=$('#btn_cost').attr("class");
		$('#sortField').val('base_cost');
		$('#btn_time').removeClass().addClass('sort_asc');
		if('sort_asc'==btn_class){
			$('#btn_cost').removeClass('sort_asc').addClass('sort_desc');
			$('#sortType').val('DESC');
		}else{
			$('#btn_cost').removeClass('sort_desc').addClass('sort_asc');
			$('#sortType').val('ASC');
		}
		page_load(0,true);
	});
	$('#btn_time').click(function(){
		var btn_class=$('#btn_time').attr("class");
		$('#sortField').val('base_duration');
		$('#btn_cost').removeClass().addClass('sort_asc');
		if('sort_asc'==btn_class){
			$('#btn_time').removeClass('sort_asc').addClass('sort_desc');
			$('#sortType').val('DESC');
		}else{
			$('#btn_time').removeClass('sort_desc').addClass('sort_asc');
			$('#sortType').val('ASC');
		}
		page_load(0,true);
	});
	
	/**
	 * a click 
	 */
	$('#pages a').live('click',function(e){
		  var obj=$(e.target);
		  var pageNum=obj.text();
		  if(isNaN(pageNum)){
			  var curPageNum=$('#pages .current_page').text();
			  if('repage'==obj.attr("id")){
				      curPageNum=parseInt(curPageNum)-1;
				      if(curPageNum<=0){
				    	  return false;
				      }
				   obj=$('#pages .current_page').prev();
			  }else{
				  var lastNum=$('#nextpage').prev().text();
				  curPageNum=parseInt(curPageNum)+1;
				  if(curPageNum>lastNum){
					  return false;
				  }
				  obj=$('#pages .current_page').next();
			  }
			  pageNum=curPageNum;
		  }
		  obj.siblings().removeClass();
		  obj.attr('class','current_page');
		  page_load(parseInt(pageNum)-1,false);
	});
	$('.btn_start').live("click",function(e){
		e.stopPropagation();
		 var bool = window.confirm("Bestimmen Sie,dass Sie das Tarifpaket starten müssen? nach dem Start nicht geändert und gelöscht wird.");
		 if(bool){
			 var btn_start=$(e.target).parent();
			 var id=btn_start.siblings().eq(0).children('span').text();
			 $.ajax({
				 'url':$path()+'/const/modifyStatusInfo.do',
				 'type':'post',
				 'data':{'id':id,'status':'1','allow':'Geschäft'},
				 'dataType':'json',
				 'async':false,
				 'success':function(data){
						if(20==data.status){
							$('#operate_result_info').removeClass('operate_fail').addClass('operate_success');						
						}else if(103==data.status){
							$('#operate_result_info').removeClass('operate_success').addClass('operate_fail');	
						}
						$("#operate_result_info").css('display', 'block').children('span').text(data.msg);
						setTimeout(function(){ $("#operate_result_info").css('display', 'none')},3000);
						var curPageNum=$('#pages .current_page').text();
						page_load(parseInt(curPageNum)-1,false);
						//$('#pages a:contains('+curPageNum+')').addClass('current_page').siblings().removeClass();	
					},
					'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
				});
		 }
	});
	$('.btn_delete').live("click",function(e){
		e.stopPropagation();
		var bool = window.confirm("Möchten Sie dieses Tarifpaket wirklich löschen？");
		if(bool){
			//get Tarifpaket id
			var obj_del=$(e.target).parent();
			var id=obj_del.siblings().eq(0).children('span').text();
			$.ajax({
				'url':$path()+'/const/delCostInfo.do',
				'type':'post',
				'data':{'id':id,'allow':'Geschäft'},
				'dataType':'json',
				'async':false,
				'success':function(data){
					if(20==data.status){
						$('#operate_result_info').removeClass('operate_fail').addClass('operate_success');						
					}else if(103==data.status){
						$('#operate_result_info').removeClass('operate_success').addClass('operate_fail');	
					}
					$("#operate_result_info").css('display', 'block').children('span').text(data.msg);
					setTimeout(function(){ $("#operate_result_info").css('display', 'none')},3000);
					//var curPageNum=$('#pages .current_page').text();
					page_load(0,true);
					//$('#pages a:contains('+curPageNum+')').addClass('current_page').siblings().removeClass();
					
				},
				'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
			});
			
		}
	});
	$('.btn_modify').live('click',function(e){
		 e.stopPropagation();
		 var btn_start=$(e.target).parent();
		 var id=btn_start.siblings().eq(0).children('span').text();
		 window.location.href=$path()+"/pages/fee/fee_modi.html?id="+id;
	});
	$('tbody tr').live('click',function(){
		 var tageObj=$(this);
		 var id=tageObj.children().eq(0).children('span').text();
		 window.location.href=$path()+"/pages/fee/fee_detail.html?id="+id;
	});
	$('#header a').click(function(){
		login_out();
	});
	page_load(0,true);
});
