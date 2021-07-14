$(function(){
	/**
	 * monthFormat Trans
	 * @param bill_month
	 * @returns
	 */
	function monthFormat(month_id){
    	var year_month=month_id.substring(0, 4)+'.';
    	return year_month=year_month+month_id.substring(month_id.length-2);
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
		  '<td>'+data.login_name+'</td>'+
		  '<td>'+data.real_name+'</td>'+
		  '<td>'+data.idcard_no+'</td>'+
		  '<td>'+data.telephone+'</td>'+
		  '<td>'+monthFormat(data.month_id)+'</td>'+
		  '<td>'+data.duration+'Min.</td>'+
	   '</tr>';
		var $tr=$(trElement)
		return $tr;	
	}
	 function page_load(pageNum,bool){
    	$.ajax({
    		'url':$path()+'/report/findReportList.do',
    		'type':'post',
    		'data':{'pageNum':pageNum,'allow':'Report'},
    		'dataType':'json',
    		'success':function(data){
    			if(20==data.status){
					var resportList=data.data.resportList;
					var pageCount=data.data.pageCount;
					$('#datalist').children('tbody').empty();
					for(var i=0;i<resportList.length;i++){
						var row=i+1+pageNum*5;
						$('#datalist').children('tbody').append(addElement(row,resportList[i]));
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
    		'error':function(data){
    			alert("System error. Bitte ein Moment wieder probieren");
    		}
    	}); 
    }
	$('#header a').click(function(){
			login_out();
	});
	 page_load(0,true);
});