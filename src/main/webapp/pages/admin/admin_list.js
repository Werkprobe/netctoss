$(function(){
	function addTabElement(row,data){
		var trElement='<tr>'+
		 '<td><input type="checkbox" /></td>'+
		  '<td>'+row+'<span style="display:none">'+data.id+'</span></td>'+
		  '<td>'+data.name+'</td>'+
		 '<td>'+data.admin_code+'</td>'+
		 '<td>'+data.telephone+'</td>'+
		 '<td>'+data.email+'</td>'+
		 '<td>'+data.enrolldate+'</td>'+
		 '<td class="role_all"><a class="summary" >'+data.role_name.split(',')[0]+'...'+
			 '<div class="detail_info" >'+
			 		data.role_name
	     	  +'</div>'+
     	  '</td>'+
		  '<td>'+
				'<input type="button" value="Ändern"   class="btn_modify"/>'+
				'<input type="button" value="Löschen"  class="btn_delete" />'+
		  '</td>'+
	   '</tr>';    
		return trElement;	
	}
	/**
	 * page Init
	 * @param num
	 * @returns
	 */
	function page_load(pageNum,bool){
		//get param
		var privilegeId=$('#selModules').val();
		var roleName=$('#roleName').val();
		$.ajax({
			'url':$path()+'/admin/adminRoleInfo.do',
			'type':'post',
			'data':{'privilegeId':privilegeId,'roleName':roleName,'pageNum':pageNum,'allow':'Admin'},
			'dataType':'json',
			'async':false,
			'success':function(data){
				if(20==data.status){
					var pageCount=data.data.pageCount;
					$('#datalist').children('tbody').empty();
					for(var i=0;i<data.data.adminList.length;i++){
						var row=i+1+pageNum*10;
						$('#datalist').children('tbody').append(addTabElement(row,data.data.adminList[i]));
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
					$('#datalist').children('tbody').append('<tr><td colspan="9" style="color:#f00;font-weight:bold;">'+data.msg+'</td></tr>');
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
	 * a click find für page
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
	
    $('tr .role_all').live(
    		{'mousemove':function(e){
    					   var obj=$(e.target);
    				       obj.children('div').show();
    				      },
    		  'mouseout':function(e){
    				        var obj=$(e.target);
    				        obj.children('div').hide();
    	                  }
            });
  /**
   * del adminInfo
   */
    $('.btn_delete').live('click',function(e){
    	 var bool = window.confirm("Möchten Sie diesen Admin wirklich löschen?");
    	 if(bool){
		    	var btn_delete=$(e.target).parent();
		    	var adminId=btn_delete.siblings().eq(1).children('span').text();
		    	$.ajax({
					'url':$path()+'/admin/delAdminInfoById.do',
					'type':'post',
					'data':{'id':adminId,'allow':'Admin'},
					'dataType':'json',
					'success':function(data){
						if(102==data.status){
							window.location.href=data.data;
						}else if(20==data.status){
							$('#operate_result_info').removeClass('operate_fail').addClass('operate_success');
							$('#operate_result_info span ').text(data.msg);
						}else{
							$('#operate_result_info').removeClass('operate_success').addClass('operate_fail');
							$('#operate_result_info span ').text('der Admin wird nicht gelöschen kann.');
						}
						$('#operate_result_info').css('display','block')
						var curPageNum=$('#pages .current_page').text();
						page_load(parseInt(curPageNum)-1,true);
						$('#pages a:contains('+curPageNum+')').addClass('current_page').siblings().removeClass();
					},
					'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
				});
    	    }
    });
	/**
	 * a click rolemodify
	 */
	$('.btn_modify').live('click',function(e){
		var btn_modify=$(e.target).parent();
		var adminId=btn_modify.siblings().eq(1).children('span').text();
		$.ajax({
			'url':$path()+'/admin/checkPage.do',
			'type':'post',
			'data':{'url':'admin_modi.html','allow':'Admin'},
			'dataType':'json',
			'success':function(data){
				if("102"==data.status){
					window.location.href=data.data;
				}else{
					window.location.href=data.data+'?id='+adminId;
				}
			},
			'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
		});
	});
	$('#all_checkbox').click(function(){
		 var inputArray = document.getElementById("datalist").getElementsByTagName("input");
		 for (var i = 1; i < inputArray.length; i++) {
             if (inputArray[i].type == "checkbox") {
                 inputArray[i].checked = $('#all_checkbox').attr('checked');
             }
         }
	});
	$('#btn_addAdmin').click(function(){
		window.location.href='admin_add.html';
	});
	$('#btn_change_password').click(function() {
		var id="";
		//get checked id 
		var obj=$('tbody input[type="checkbox"]:checked');
		if(0==obj.length){
			$('#operate_result_info').removeClass('operate_success').addClass('operate_fail');
			$('#operate_result_info span').text("Bitte, minstens eine Admin wählen");
			$('#operate_result_info').css('display','block'); 
			return;
		}
		obj.each(function(i){
			id+=$(this).parent().next().children('span').text()+',';
		});
		id=id.substring(0,id.lastIndexOf(','));
		$.ajax({
			'url':$path()+'/admin/modifyAdminPassword.do',
			'type':'post',
			'data':{'id':id,'allow':'Admin'},
			'dataType':'json',
			'success':function(data){
				if(102==data.status){
					window.location.href=data.data;
				}if(20==data.status){
					$('#operate_result_info').removeClass('operate_fail').addClass('operate_success');
				}else if(30==data.status){
					$('#operate_result_info').removeClass('operate_success').addClass('operate_fail');
				}
				$('#operate_result_info span').text(data.msg);
				$('#operate_result_info').css('display','block'); 
			},
			'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
		});
	});
	
	$('#search').click(function(){
		page_load(0,true);
	});
	$('#header a').click(function(){
		login_out();
	});
	page_load(0,true);
});