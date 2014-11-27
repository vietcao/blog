	function likePost(_id){
		xmlhttp = new  XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState==4 && xmlhttp.status==200) {
				input=xmlhttp.responseXML;
				success = input.documentElement.getElementsByTagName("success"); 
				if (success[0].childNodes[0].nodeValue == 1){
					like = input.documentElement.getElementsByTagName("like");
					number = like[0].getElementsByTagName("number")[0].childNodes[0].nodeValue;
					document.getElementById('p_'+_id).innerHTML = number;
				}else{
					alert("Something wrong here. Try again !");
				}
			}
		}
		
		xmlhttp.open("POST", "/user/post/like", true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send("postid="+_id);
	}