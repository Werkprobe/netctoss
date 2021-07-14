$(function(){
	/**
	 * conversion status
	 * @param status
	 * @returns
	 */
	function stausTrans(status){
		if(1==status){
			return 'Start';
		}else if(2==status){
			return 'Unterbrechen'
		}else{
			return 'Löschung'
		}
	}
	/**
	 * btn status
	 * @param status
	 * @returns
	 */
	function btn_modify(status){
		var str='';
		if(1==status){
			return str='<input type="button" value="Stop"  class="btn_operation btn_pause"/>'+
					'<input type="button" value="Ändern"  class="btn_modify"/>'+
					'<input type="button" value="Löschen"  class="btn_delete" />';
		}else if(2==status){
			return '<input type="button" value="Start"  class="btn_operation btn_start"/>'+
			'<input type="button" value="Ändern"  class="btn_modify"/>'+
			'<input type="button" value="Löschen"  class="btn_delete" />';
		}else{
			return '';
		}
	}
	/**
	 * add table element
	 * @param row
	 * @param data
	 * @param lastLoginTime
	 * @returns tr
	 */
	function addElement(row,data){
		var trElement='<tr>'+
		  '<td>'+row+'</td>'+
		  '<td>'+data.account_id+'</td>'+
		  '<td>'+data.idcard_no+'</td>'+
		  '<td>'+data.real_name+'</td>'+
		  '<td>'+data.login_name+'</td>'+
		  '<td>'+stausTrans(data.status)+'</td>'+
		  '<td>'+data.unix_host+'</td>'+
		  '<td class="service_name"><a class="summary" >'+data.name.substring(0, 7)+'...'+
			 '<div class="detail_info" >'+
			 		data.name
	     	  +'</div>'+
	      '</td>'+
		  '<td>'+btn_modify(data.status)+'</td>'+
	   '</tr>';
		var $tr=$(trElement)
		$tr.data("status",data.status);
		$tr.data("id",data.id)
		return $tr;	
	}
	/**
	 * page load
	 * @param pageNum
	 * @param bool
	 * @returns
	 */
	function page_load(pageNum,bool){
		var osUsername=$('#osUsername').val();
		var serviceIp=$('#serviceIp').val();
		var status=$('#chk_sel_status').val();
		var ausWeise=$('#ausWeise').val();
		$.ajax({
			'url':$path()+'/service/findServiceList.do',
			'type':'post',
			'data':{'osUsername':osUsername,'serviceIp':serviceIp,'status':status,'ausWeise':ausWeise,'pageNum':pageNum,'allow':'Dienst'},
			'dataType':'json',
			'async':false,
			'success':function(data){
				if(20==data.status){
					var serviceDetailInfoList=data.data.serviceDetailInfoList;
					var pageCount=data.data.pageCount;
					$('#datalist').children('tbody').empty();
					for(var i=0;i<serviceDetailInfoList.length;i++){
						var row=i+1+pageNum*5;
						$('#datalist').children('tbody').append(addElement(row,serviceDetailInfoList[i]));
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
				}else if("103"==data.status){
					$('#datalist').children('tbody').empty();
					$('#datalist').children('tbody').append('<tr><td colspan="9" style="color:#f00;font-weight:bold;">'+data.msg+'</td></tr>');
					$('#pages').empty().append("<a href='javascript:;' id='repage'><</a>");
					$('#pages').append("<a href='javascript:;' class='current_page' >"+1+"</a>");
					$('#pages').append("<a href='javascript:;' id='nextpage' >></a>");
				}else if("102"==data.status){
					window.location.href=data.data;
				}
			},
			'error':function(data){
				alert("System error. Bitte ein Moment wieder probieren");
			}	
		});
	}
	/**
	 * click page Btn
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
	/**
	 * Btn search
	 */
	$('#search').click(function(){
		page_load(0,true);
	});
	/**
	 * show Detailinfo cost
	 */
	$('#datalist td[class=service_name]').live({
		'mousemove':function(e){
		   var obj=$(e.target);
	       obj.children('div').show();
	      },
	      'mouseout':function(e){
	        var obj=$(e.target);
	        obj.children('div').hide();
	        }
    });
	
	/**
	 * a click btn_statu
	 */
	$('.btn_operation').live('click',function(e){
		var $btn_operation=$(e.target);
		var status=1;
		var bool=true;
		if($btn_operation.hasClass('btn_pause')){
			status=2;
			bool=window.confirm("Möchten Sie diese Service wirklich Pause？");
		}else{
			bool=window.confirm("Möchten Sie diese Service wirklich Start？");
		}
		if(bool){
			var id=$btn_operation.parent().parent().data("id");
			$.ajax({
				'url':$path()+'/service/modifyStatusInfo.do',
				'type':'post',
				'data':{'id':id,'status':status,'allow':'Dienst'},
				'dataType':'json',
				'success':function(data){
					if(20==data.status){
						if($btn_operation.hasClass('btn_pause')){
							$btn_operation.removeClass('btn_pause').addClass('btn_start');
						}else{
							$btn_operation.removeClass('btn_start').addClass('btn_pause');
						}
						$('#operate_result_info').removeClass('operate_fail').addClass('operate_success');						
					}else if(103==data.status){
						$('#operate_result_info').removeClass('operate_success').addClass('operate_fail');	
					}
					$("#operate_result_info").css('display', 'block').children('span').text(data.msg);
					setTimeout(function(){ $("#operate_result_info").css('display', 'none')},3000);
					var curPageNum=$('#pages .current_page').text();
					page_load(parseInt(curPageNum)-1,false);
				},
				'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
			});
		}
	});
	$('.btn_delete').live('click',function(e){
		var bool = window.confirm("Möchten Sie dieses Service wirklich löschen？");
		if(bool){
			var $btn_delete=$(e.target).parent().parent();
			var id=$btn_delete.data("id");
			$.ajax({
				'url':$path()+'/service/modifyStatusInfo.do',
				'type':'post',
				'data':{'id':id,'status':3,'allow':'Dienst'},
				'dataType':'json',
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
				},
				'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
			});
		}
	});
	$('.btn_modify').live('click',function(e){
		e.stopPropagation();
		var $btn_modify=$(e.target).parent().parent();
		var id=$btn_modify.data("id");
		window.location.href= window.location.href=$path()+"/pages/service/service_modi.html?id="+id;
	});
	$('#header a').click(function(){
		login_out();
	});
	page_load(0,true);
});