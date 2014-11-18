
function search(){
		//set cookie to disable load more post
		document.cookie ="loadPost=false; path=/";
		var searchkeyword = searchform.searchkeyword.value;
		var xmlhttp;
		var x,txt,i, xx;
		
		xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange=function(){

		  	if (xmlhttp.readyState==4 && xmlhttp.status==200) {
				
		  		txt = "<br/>";
		   		x =xmlhttp.responseXML.documentElement.getElementsByTagName("user");
		   		for(i= 0; i< x.length; i++){
		   			xx="";
		   			txt= txt + '<div class="main_element">';
		   			xx = xx + '<a href="/user/function/addfriend?id='+x[i].getElementsByTagName("id")[0].childNodes[0].nodeValue+'"><input type="submit" class="addfriendbtn" name="add_friend" value=" + Add Friend "></a>';
		   			xx = xx +  '<a href="/user/'+ x[i].getElementsByTagName("username")[0].childNodes[0].nodeValue+'?id='+x[i].getElementsByTagName("id")[0].childNodes[0].nodeValue+'">'+x[i].getElementsByTagName("nick")[0].childNodes[0].nodeValue+'</a>';
					xx = xx + " - "+ x[i].getElementsByTagName("birth")[0].childNodes[0].nodeValue + "<br/>";
					xx = xx + x[i].getElementsByTagName("about")[0]	.childNodes[0].nodeValue;
					
		   			txt = txt + xx;
					txt = txt +"</div>"+"<br/>";

		   		}
				document.getElementById("main").innerHTML=txt;
				}
		  }
		xmlhttp.open("GET","/user/function/search?searchkeyword="+searchkeyword,true);
		xmlhttp.send();
}