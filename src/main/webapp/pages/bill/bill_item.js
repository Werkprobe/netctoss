$(function(){
	//Trans Month Fromat
	function monthFormat(bill_month){
    	var year_month=bill_month.substring(0, 4)+'.';
    	return year_month=year_month+bill_month.substring(bill_month.length-2)
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
		  '<td>'+data.os_username+'</td>'+
		  '<td>'+data.unix_host+'</td>'+
		  '<td>'+data.account_id+'</td>'+
		  '<td>'+data.sofar_duration+'</td>'+
		  '<td>'+data.cost+'</td>'+
		  '<td>'+data.name+'</td>'+
	   '</tr>';
		var $tr=$(trElement)
		return $tr;	
	}
	 function page_load(pageNum,bool){
    	 //get parames
		 var paramStr=getPageParamur(window.location.href)
		 var paramJson=JSON.parse(paramStr);
		 $("#idcard_no").text(paramJson.idcard_no);
    	 $("#login_name").text(paramJson.login_name);
    	 $("#real_name").text(paramJson.real_name);
    	 $("#bill_month").text(monthFormat(paramJson.bill_month));
    	 $("#cost").text(paramJson.cost);
    	$.ajax({
    		'url':$path()+'/bill/findBillDetailMonth.do',
    		'type':'post',
    		'data':{'bill_id':paramJson.id,'month_id':paramJson.bill_month,'pageNum':pageNum,'allow':'Rechung'},
    		'dataType':'json',
    		'success':function(data){
    			if(20==data.status){
					var billInfoList=data.data.billDetailInfoList;
					var pageCount=data.data.pageCount;
					$('#datalist').children('tbody').empty();
					for(var i=0;i<billInfoList.length;i++){
						var row=i+1+pageNum*10;
						$('#datalist').children('tbody').append(addElement(row,billInfoList[i]));
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
					$('#datalist').children('tbody').append('<tr><td colspan="7" style="color:#f00;font-weight:bold;">'+data.msg+'</td></tr>');
					$('#pages').empty().append("<a href='javascript:;' id='repage'><</a>");
					$('#pages').append("<a href='javascript:;' class='current_page' >"+1+"</a>");
					$('#pages').append("<a href='javascript:;' id='nextpage' >></a>");
				}else if("102"==data.status){
					window.location.href=data.data;
				}
    		},
    		'error':function(){
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
		$('#header a').click(function(){
			login_out();
		});
	 page_load(0,true)
});