var idcockie = getCookie('id');
var txt ="";

setInterval(function () {getInfoFriendRequest()}, 5000);
function getInfoFriendRequest(){
	var xmlhttp;
	var i, id, username, nick;
	var  noti, request;
	xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		txt ="";
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
			request = request + '<a href="/user/'+ username+'?id='+id+'">'+nick+'</a>';
			request = request + '<input type="submit" class="denyfriendbtn" value=" Deny " onclick="addFriendDeny('+id+')" id="f_deny'+id+'">';
			request = request + '<input type="submit" class="acceptfriendbtn" value=" Accept " onclick="addFriendAccept('+id+')" id="f_accept'+id+'">';
			request = request + '</div>';
			
			txt = txt + request;
		}
	}
	xmlhttp.open("POST", "/user/function/addfriend", true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send("query=getinfo");
}

var enable= 1;
function showFriendNoti(){
	var notiarea = document.getElementById("friendarea");
	
	if( enable == 1 ){
		notiarea.innerHTML = txt;
		enable = 0;
	}else{
		notiarea.innerHTML="";
		enable = 1;
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