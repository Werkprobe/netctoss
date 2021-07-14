$(function(){
	/**
	 * add table Element
	 * @param row
	 * @param userName
	 * @param role
	 * @returns
	 */
	function addElement(row,roleId,userName,role){
		var trElement='<tr>'+
						  '<td>'+row+'<span style="display:none">'+roleId+'</span></td>'+
						  '<td>'+userName+'</td>'+
						 '<td>'+role+'</td>'+
						  '<td>'+
            					'<input type="button" value="Ändern" class="btn_modify"/>'+
            					'<input type="button" value="Löschen"  class="btn_delete" />'+
            			  '</td>'+
            		   '</tr>';    
		return trElement;	
	}
	/**
	 * spring addRole
	 */
	$('#addRole').click(function(){
		$.ajax({
			'url':$path()+'/rolemangement/checkEingung.do',
			'data':{'url':'role_add.html','allow':'Role'},
			'type':'post',
			'async':false,
			'dataType':'json',
			'success':function(data){
				window.location.href=data.data; 
			},
			'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
		});
	});
	
	
	/**
	 * page Init
	 * @param num
	 * @returns
	 */
	function page_load(num,bool){
		$.ajax({
			'url':$path()+'/rolemangement/roleList.do',
			'type':'post',
			'data':{'pageNum':num,'allow':'Role'},
			'dataType':'json',
			'async':false,
			'success':function(data){
				if(20==data.status){
					var roleList=data.data.roleList;
					var pageCount=data.data.pageCount;
					$('#datalist').children('tbody').empty();
					for(var i=0;i<roleList.length;i++){
						var row=i+1+num*10;
						$('#datalist').children('tbody').append(addElement(row,roleList[i].roleMd5,roleList[i].roleName,roleList[i].roleBesitz));
					}
					if(bool){
						$('#pages').empty().append("<a href='javascript:;' id='repage'><</a>");
						var isnotclass=true;
						for(var i=1;i<=pageCount;i++){
							if(isnotclass){
								isnotclass=false;
								$('#pages').append("<a href='javascript:;' class='current_page'>"+i+"</a>");
							}else{
								$('#pages').append("<a href='javascript:;' >"+i+"</a>");
							}	
						}
						$('#pages').append("<a href='javascript:;' id='nextpage' >></a>");
					}
				}else if(103==data.status){
					$('#datalist').children('tbody').empty();
					$('#datalist').children('tbody').append('<tr><td colspan="4" style="color:#f00;font-weight:bold;">Das ist Leere</td></tr>');
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
	
	/**
	 * a click anmelden
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
	 * a click rolemodify
	 */
	$('.btn_modify').live('click',function(e){
		var btn_modify=$(e.target).parent();
		var roleId=btn_modify.siblings().eq(0).children('span').text();
		$.ajax({
			'url':$path()+'/rolemangement/checkEingung.do',
			'type':'post',
			'data':{'url':'role_modi.html','allow':'Role'},
			'dataType':'json',
			'success':function(data){
				if("102"==data.status){
					window.location.href=data.data;
				}else{
					window.location.href=data.data+'?id='+roleId;
				}
			},
			'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
		});
	});
	$('.btn_delete').live('click',function(e){
			var bool = window.confirm("Möchten Sie diese Rolle wirklich löschen？");
			if(bool){
				var btn_delete=$(e.target).parent();
				var roleId=btn_delete.siblings().eq(0).children('span').text();
				roleId=roleId.substring(roleId.lastIndexOf('_')+1);
				$.ajax({
					'url':$path()+'/rolemangement/delRoleInfo.do',
					'type':'post',
					'data':{'roleId':roleId,'allow':'Role'},
					'dataType':'json',
					'success':function(data){
						if("102"==data.status){
							window.location.href=data.data;
						}else if(30==data.status){
							$('#operate_result_info').removeClass('operate_success').addClass('operate_fail');
							$('#operate_result_info').css('display','block').children('span').text(data.msg);
						}else{
							$('#operate_result_info').removeClass('operate_fail').addClass('operate_success');
							$('#operate_result_info').css('display','block').children('span').text(data.msg);
						}
						var curPageNum=$('#pages .current_page').text();
						page_load(parseInt(curPageNum)-1,true);
						$('#pages a:contains('+curPageNum+')').addClass('current_page').siblings().removeClass();
					},
					'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
				});
			}
	});
	$('#header a').click(function(){
		login_out();
	});
	page_load(0,true);
});