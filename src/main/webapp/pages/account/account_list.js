$(function(){
	/**
	 * add table element
	 * @param row
	 * @param data
	 * @param lastLoginTime
	 * @returns tr
	 */
	function addElement(row,data,lastLoginTime){
		var trElement='<tr>'+
		  '<td>'+row+'<span style="display:none">'+data.id+'</span><span style="display:none">'+data.status+'</span></td>'+
		  '<td>'+data.real_name+'</td>'+
		  '<td>'+data.idcard_no+'</td>'+
		  '<td>'+data.login_name+'</td>'+
		  '<td>'+stausTrans(data.status)+'</td>'+
	  	  '<td>'+data.create_date+'</td>'+
		  '<td>'+lastLoginTime+'</td>'+
		  '<td>'+
			btn_modify(data.status)+
		  '</td>'+
	   '</tr>';    
		return trElement;	
	}
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
			return str='<input type="button" value="Stop"   class="btn_operation btn_pause"/>'+
					'<input type="button" value="Ändern"   class="btn_modify"/>'+
					'<input type="button" value="Löschen" class="btn_delete" />';
		}else if(2==status){
			return '<input type="button" value="Start"  class="btn_operation btn_start"/>'+
			'<input type="button" value="Ändern"  class="btn_modify"/>'+
			'<input type="button" value="Löschen"  class="btn_delete" />';
		}else{
			return '';
		}
	}
	/**
	 * page Init
	 * @param num
	 * @returns
	 */
	function page_load(num,bool){
		var idCart=$('#txt_card_no').val();
		var realName=$('#real_name').val();
		var loginName=$('#login_name').val();
		var status=$('#chk_sel_status').val();
		$.ajax({
			'url':$path()+'/account/findAccount.do',
			'type':'post',
			'data':{'pageNum':num,'idCart':idCart,'realName':realName,'loginName':loginName,'status':status,'allow':'Konto'},
			'dataType':'json',
			'async':false,
			'success':function(data){
				if(20==data.status){
					var accountList=data.data.accountList;
					var pageCount=data.data.pageCount;
					$('#datalist').children('tbody').empty();
					for(var i=0;i<accountList.length;i++){
						var row=i+1+num*5;
						var lastLoginTime=accountList[i].last_login_time==null || accountList[i].last_login_time==''?'':accountList.last_login_time;
						$('#datalist').children('tbody').append(addElement(row,accountList[i],lastLoginTime));
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
					$('#datalist').children('tbody').append('<tr><td colspan="8" style="color:#f00;font-weight:bold;">Das ist Leer</td></tr>');
					$('#pages').empty().append("<a href='javascript:;' id='repage'><</a>");
					$('#pages').append("<a href='javascript:;' class='current_page' >"+1+"</a>");
					$('#pages').append("<a href='javascript:;' id='nextpage' >></a>");
				}else if("102"==data.status){
					window.location.href=data.data;
				}
			},
			'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
		});
	}
	$('#search').click(function(){
		page_load(0,true);
	});
	
	/**
	 * click page
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
	 * a click btn_statu
	 */
	$('.btn_operation').live('click',function(e){
		var btn_operation=$(e.target);
		var status=1;
		if(btn_operation.hasClass('btn_pause')){
			status=2;
		}
		var id=btn_operation.parent().siblings().eq(0).children('span').eq(0).text();
		$.ajax({
			'url':$path()+'/account/modifyStatusInfo.do',
			'type':'post',
			'data':{'id':id,'status':status,'allow':'Konto'},
			'dataType':'json',
			'success':function(data){
				if(20==data.status){
					if(btn_operation.hasClass('btn_pause')){
						btn_operation.removeClass('btn_pause').addClass('btn_start');
					}else{
						btn_operation.removeClass('btn_start').addClass('btn_pause');
					}
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
	});
	$('.btn_delete').live('click',function(e){
		var bool = window.confirm("Möchten Sie diese Account wirklich löschen？");
		if(bool){
			var btn_delete=$(e.target).parent();
			var id=btn_delete.siblings().eq(0).children('span').eq(0).text();
			$.ajax({
				'url':$path()+'/account/modifyStatusInfo.do',
				'type':'post',
				'data':{'id':id,'status':3,'allow':'Konto'},
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
		btn_modify=$(e.target).parent();
		var id=btn_modify.siblings().eq(0).children('span').eq(0).text();
		window.location.href= window.location.href=$path()+"/pages/account/account_modi.html?id="+id;
	});
	$('#header a').click(function(){
		login_out();
	});
	page_load(0,true);
});