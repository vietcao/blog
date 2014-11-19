function addFriendRequest(request_id){
	var success;
	
	xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		x =xmlhttp.responseXML.documentElement.getElementsByTagName("success");
		success = x[0].getElementsByTagName("value")[0].childNodes[0].nodeValue;

		if(success == 0){
			document.getElementById(request_id).setAttribute("value", "! Already Friend ");
		}else{
			document.getElementById(request_id).setAttribute("value", "++ Request was send ");
		}
	}
	xmlhttp.open("POST", "/user/function/addfriend", true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send("query=request&id="+request_id);
}