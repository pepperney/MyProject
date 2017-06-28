//公共方法
function setCookie(name,value)
{
var Days = 1;
var exp = new Date();
exp.setTime(exp.getTime() + Days*24*60*60*1000);
document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}


function getCookie(name)
{
var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
if(arr=document.cookie.match(reg))
return unescape(arr[2]);
else
return null;
}

function clearCookie(name) {  
    setCookie(name, "", -1);  
}  


function formatDate(tm) {
    var tt = new Date(parseInt(tm)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
    return tt;
}


function numtotime(time){
    //数字转换成时间格式
    var retime = '';
	if(parseInt(time)>0){
		var date = new Date(time); 
		var year = date.getYear() + 1900;
		var month = date.getMonth()+1;
		month = month<10?'0'+month:month;
		var date = date.getDate();
		date = date<10?'0'+date:date;
		return year + '-' + month + '-' + date;
	}else{
		return retime;
	}
}



  








