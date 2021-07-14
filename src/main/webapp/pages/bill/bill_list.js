$(function(){
	//schreiben Jahr und Month 
    function initialYearAndMonth() {
        //in der Nähe von 3 Jahre
        var yearObj = $("#selYears");
        var year = (new Date()).getFullYear();
        for (var i = 0; i <= 2; i++) {
            var opObj = new Option(year - i, year - i);
            yearObj.append($(opObj));
        }
        var monthObj = $("#selMonths");
        var opObj = new Option("all", "");
        monthObj.append($(opObj));
        for (var i = 1; i <= 12; i++) {
        	var opt=i+'';
        	if(i<10){
        		opt='0'+opt;
        	}
        	var opObj = new Option(opt, opt);
            monthObj.append($(opObj));
        }
    }
    function modeTrans(parment_mode){
    	if('1'==parment_mode){
    		return 'Karte'
    	}else if('2'==parment_mode){
    		return 'Ausweisung'
    	}else if('3'==parament_mode){
    		return 'Bar'
    	}
    	return '';
    }
    function stateTrans(pay_state){
    	if('1'==pay_state){
    		return 'bezahlt'
    	}
    	return 'unbezahlt';
    }
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
		  '<td>'+data.real_name+'</td>'+
		  '<td>'+data.idcard_no+'</td>'+
		  '<td>'+data.login_name+'</td>'+
		  '<td>'+data.cost+'</td>'+
		  '<td>'+monthFormat(data.bill_month)+'</td>'+
		  '<td>'+modeTrans(data.parment_mode)+'</td>'+
		  '<td>'+stateTrans(data.pay_state)+'</td>'+
		  '<td class="bill_name"><a href="javascript:;">Detail</a>'+
	      '</td>'+
	   '</tr>';
		var $tr=$(trElement)
		$tr.data('id',data.id)
		$tr.data('login_name',data.login_name);
		$tr.data('idcard_no',data.idcard_no);
		$tr.data('real_name',data.real_name);
		$tr.data('bill_month',data.bill_month);
		$tr.data('cost',data.cost);
		return $tr;	
	}
    function page_load(pageNum,bool){
    	 //get parames
    	var idcard_no=$("#idcard_no").val();
    	var login_name=$("#login_name").val();
    	var real_name=$("#real_name").val();
    	var bill_month=$("#selYears").val();
    	var selMonths=$("#selMonths").val();
    	if('0'!=selMonths){
    		bill_month=bill_month+selMonths;
    	}
    	$.ajax({
    		'url':$path()+'/bill/findBillList.do',
    		'type':'post',
    		'data':{'idcard_no':idcard_no,'login_name':login_name,'real_name':real_name,'bill_month':bill_month,'pageNum':pageNum,'allow':'Rechung'},
    		'dataType':'json',
    		'success':function(data){
    			if(20==data.status){
					var billInfoList=data.data.billDetailInfoList;
					var pageCount=data.data.pageCount;
					$('#datalist').children('tbody').empty();
					for(var i=0;i<billInfoList.length;i++){
						var row=i+1+pageNum*5;
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
	$('.bill_name a').live('click',function(e){
		var $obj=$(e.srcElement|| e.target).parent().parent();
		var id=$obj.data('id');
		var login_name=$obj.data('login_name');
		var idcard_no=$obj.data('idcard_no');
		var real_name=$obj.data('real_name');
		var bill_month=$obj.data('bill_month');
		var cost=$obj.data('cost');
		window.location.href= window.location.href=$path()+"/pages/bill/bill_item.html?id="+id+'&login_name='+login_name+'&idcard_no='+idcard_no+'&real_name='+real_name+'&bill_month='+bill_month+'&cost='+cost;
	})
	$('#header a').click(function(){
			login_out();
	});
	//load Jahr und Month
	initialYearAndMonth();
    page_load(0,true);
});