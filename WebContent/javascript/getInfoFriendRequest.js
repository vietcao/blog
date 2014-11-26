
var idcockie = getCookie('id');
var fri_txt ="";

setInterval(function () {getInfoFriendRequest()}, 5000);
function getInfoFriendRequest(){
	var xmlhttp;
	var i, id, username, nick;
	var  noti, request;
	xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		fri_txt ="";
		input=xmlhttp.responseXML;
		arr_request = input.documentElement.getElementsByTagName("user");
		if(arr_request.length == 0 ){
			noti = document.getElementById("friendbtn");
			noti.setAttribute("value", "+ F");
			noti.setAttribute("style", "color: black;");
		}else{	
			noti = document.getElementById("friendbtn");
			noti.setAttribute("value", "+ "+arr_request.length);
			noti.setAttribute("style", "color: red;");
		}
		
		for(i=0; i< arr_request.length; i++){
			id = arr_request[i].getElementsByTagName("id")[0].childNodes[0].nodeValue; 
			username = arr_request[i].getElementsByTagName("username")[0].childNodes[0].nodeValue;
			nick = arr_request[i].getElementsByTagName("nick")[0].childNodes[0].nodeValue;
			request = '<div id="friend_element">'; 
			request = request + '<a href="/user/'+ username+'?id='+id+'"><div class ="nick" style="display: inline; font-size: 20px;">'+nick+'</div></a>';
			request = request + '<input type="submit" class="denyfriendbtn" value=" Deny " onclick="addFriendDeny('+id+')" id="f_deny'+id+'">';
			request = request + '<input type="submit" class="acceptfriendbtn" value=" Accept " onclick="addFriendAccept('+id+')" id="f_accept'+id+'">';
			request = request + '</div>';
			
			fri_txt = fri_txt + request;
		}
	}
	xmlhttp.open("POST", "/user/function/addfriend", true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send("query=getinfo");
}

var enable= 0;
function showNoti(flag){
	var notiarea = document.getElementById("friendarea");
	
	if( enable == 0 && flag=='f'){
		notiarea.innerHTML = fri_txt;
		
		enable = 1;
		return;
	}
	if( enable == 0 && flag=='n'){
		notiarea.innerHTML = noti_txt;
		var xmlhttp;
		xmlhttp = new XMLHttpRequest();
		xmlhttp.open("POST", "/user/function/timepull", true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send();
		enable = 2;
		return;
	}
	if( enable == 1 && flag=='f'){
		notiarea.innerHTML ="";
		
		enable = 0;
		return;
	}
	if( enable == 1 && flag=='n'){
		notiarea.innerHTML = noti_txt;
		var xmlhttp;
		xmlhttp = new XMLHttpRequest();
		xmlhttp.open("POST", "/user/function/timepull", true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send();
		enable = 2;
		return;
	}
	if( enable == 2 && flag=='f'){
		notiarea.innerHTML = fri_txt;
		
		enable = 1;
		return;
	}
	if( enable == 2 && flag=='n'){
		notiarea.innerHTML = "";
		
		enable = 3;
		return;
	}
	if( enable == 3 && flag=='f'){
		notiarea.innerHTML = fri_txt;
		
		enable = 1;
		return;
	}
	if( enable == 3 && flag=='n'){
		notiarea.innerHTML = noti_txt;
		var xmlhttp;
		xmlhttp = new XMLHttpRequest();
		xmlhttp.open("POST", "/user/function/timepull", true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send();
		enable = 2;
		return;
	}
}

function addFriendAccept(idfriend){
		var result, message;
		var accept = document.getElementById('f_accept'+ idfriend);
		accept.setAttribute("value", "sending..");
		
		xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange= function(){
			input=xmlhttp.responseXML;
			message = input.documentElement.getElementsByTagName("message");
			result = message[0].getElementsByTagName("result")[0].childNodes[0].nodeValue; 
			if( result == '1') accept.setAttribute("value", "success");
			else accept.setAttribute("value", "no longer");
			
		};
		
		xmlhttp.open("POST", "/user/function/addfriend", true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send("query=accept&id="+idfriend);
}

function addFriendDeny(idfriend){
	var result, message;
	var deny = document.getElementById('f_deny'+ idfriend);
	deny.setAttribute("value", "sending..");
	
	xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange= function(){
		input=xmlhttp.responseXML;
		message = input.documentElement.getElementsByTagName("message");
		result = message[0].getElementsByTagName("result")[0].childNodes[0].nodeValue; 
		if( result == '1') deny.setAttribute("value", "success");
		else deny.setAttribute("value", "no longer");
		
	};
	
	xmlhttp.open("POST", "/user/function/addfriend", true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send("query=deny&id="+idfriend);
}