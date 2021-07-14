var path=""
/**
 * Format überprufen 
 * @param str
 * @returns
 */
function getRex(str){
	var rex=new RegExp("^[a-zA-Z][\\w]{1,30}$");
	return rex.test(str);
}
/**
 * format invailde
 * @param str
 * @returns
 */
function getRexFormat(str){
	var rex=new RegExp("^[A-Za-z]{1}[a-zA-Z0-9]{0,20}$");
	return rex.test(str);
}
/**
 * 
 * @param str
 * @returns
 */
function getRexTele(str){
	var rex=new RegExp("(^(0\\d{2,3}-\\d{7,8})|(1[3584]\\d{9}))$");
	return rex.test(str);
}
/**
 * Is Number
 * @param str
 * @returns
 */
function getRexNum(str){
	var rex=new RegExp("^[0-9]{1,17}$");
	return rex.test(str);
}
/**
 * Format  überprufen für Email
 * @param str
 * @returns
 */
function getRexEmail(str){
	var rex=new RegExp("^[a-zA-Z0-9]+@[a-zA-Z0-9_-]+\\.[a-zA-Z0-9_-]+$");
	return rex.test(str);
}
/**
 * get projectName
 * @returns
 */
function $path(){
	return "/netctoss";
}
/**
 * is Number
 * @param val
 * @returns
 */
function isRealNum(val){
    // isnumber
    if(typeof val !== 'number'){
      return false;
    }
    if(!isNaN(val)){
        return true;
    }else{
        return false;
    }
}
/**
 * is Number
 * @param val
 * @returns
 */
function isNumber(val){
	var rex=new RegExp("^\\d+(\\.\\d){1}$");
   // var regPos = /\d+(\.\d+)?/;//+
   // var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //-
	return rex.test(val);
}
/**
 * is Number
 * @param val
 * @returns
 */
function isIntNum(val){
	var rex=new RegExp("^[\\d]{1,}$");
	return rex.test(val);
   
}
/**
 *  is IPadress
 * @param ipAdress
 * @returns
 */
function isIpAdress(ipAdress){
	var rex=new RegExp("^(\\d{1,3}[\.]{1}\\d{1,3}[\.]{1}\\d{1,3}[\.]{1}\\d{1,3}){1}$");
	return rex.test(ipAdress);
}
/**
 * get UrlParam
 */
function getPageParamur(url){
	var urlStr=url.substr(url.lastIndexOf('?')+1);
	var paramArray=urlStr.split("&");
	var bool=true;
	var paramJson;
	for(var i=0;i<paramArray.length;i++){
		if(bool){
			paramJson='{'
			bool=false;
		}
		var params=paramArray[i].split('=');
		paramJson+='"'+params[0]+'":"'+params[1]+'",';
	}
	if(!bool){
		paramJson=paramJson.substr(0,paramJson.lastIndexOf(","));
		paramJson+='}'
	}
	return paramJson;
}

function showResultDiv(obj,flag) {
    var divResult = document.getElementById(obj);
    if (flag)
        divResult.style.display = "block";
    else
        divResult.style.display = "none";
}
/**
 * load_page 
 * @returns
 */
function load_page(){
	$.ajax({
		'url':$path()+'/loginCheck/loadPage.do',
		'type':'post',
		'data':{'allow':'all'},
		'async':false,
		'dataType':'json',
		'success':function(data){
			if(102==data.status){
				window.location.href=data.data;
			}
		},
		'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
	});
}

function login_out(){
	$.ajax({
		'url':$path()+'/loginCheck//loginOut.do',
		'type':'post',
		'async':false,
		'dataType':'json',
		'success':function(data){
			if(20==data.status || 102==data.status){
				window.location.href=$path()+"/pages/login.html";
			}
		},
		'error':function(){alert("System error. Bitte ein Moment wieder probieren");}
	});	
}
