var idcockie = getCookie('id');
setInterval(function () {getNew()}, 3000);
var noti_txt, old_txt, old_noti;

function getNew(){
	var xmlhttp;
	var arr_post, input, post, new_noti, txt;
	var i, id, content, userid, nick, simple_content;
	
	xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		txt="";
		noti_txt = "";
		input=xmlhttp.responseXML;
		arr_post = input.documentElement.getElementsByTagName("post");
		
		if(arr_post.length == 0 ){
			noti = document.getElementById("newbtn");
			noti.setAttribute("value", "New");
			noti.setAttribute("style", "color: black;");
		}else{	
			noti = document.getElementById("newbtn");
			noti.setAttribute("value", "+ "+arr_post.length);
			noti.setAttribute("style", "color: red;");
		}
		for(i = 0; i < arr_post.length; i++){
			sid = arr_post[i].getElementsByTagName("uid")[0].childNodes[0].nodeValue;
			username =  arr_post[i].getElementsByTagName("username")[0].childNodes[0].nodeValue;
			nick =  arr_post[i].getElementsByTagName("nick")[0].childNodes[0].nodeValue;
			
			content = arr_post[i].getElementsByTagName("content")[0].childNodes[0].nodeValue;
			pid = arr_post[i].getElementsByTagName("pid")[0].childNodes[0].nodeValue;
			month =  arr_post[i].getElementsByTagName("month")[0].childNodes[0].nodeValue;
			date =  arr_post[i].getElementsByTagName("date")[0].childNodes[0].nodeValue;
			year =  arr_post[i].getElementsByTagName("year")[0].childNodes[0].nodeValue;
			numberoflike = arr_post[i].getElementsByTagName("numberoflike")[0].childNodes[0].nodeValue;
			
			//alert(pid);
			post = '<br/><div class="main_element"><div class = "userwraper"><a href="/user/';
			post = post + username;
			post = post + "?id=";
			post = post + sid;
			post = post + '"><div class="nick">';
			post = post + nick;
			post = post + '</div></a>';
			post = post + '</div><div class="timepost">';
			post = post + 'Posted at: '+ month+' ' + date + ' ' + year;
			post = post + '</div><div class ="numberoflike"><img class="likeicon" src="/likeIcon.png"> ';
			post = post + '<div style="display: inline;" id="p_'+pid+'"> '+numberoflike +' </div>';
			post = post + ' people like this..</div><br/><div class = "contentwraper">';
			post = post + content;
			post = post + '</div><div class = "postfunction"><a role="button" href="#" onclick="likePost(';
			post = post + pid;
			post = post + ')"> + Like </a>';
			post = post + '<a href="/user/post?id=';
			post = post + pid;
			post = post + '"> + Comment';
			if(idcockie == sid ){
				post = post + '<div style="position: absolute; right: 2%; display: inline;"> <a href = "/user/post/edit?id='+pid+'"> edit</a> <a role="button" href="#" onclick="delPost('+pid+')"> delete</a> </div>';
			}
			post = post + '</a></div></div><br/>';
			
			txt = txt + post;
			
			simple_content = content.substr(0,20);
			if(sid == idcockie){
				new_noti = '<div id="new_vow"> You just have new thing in your post</div>';
				new_noti = new_noti + '<div id="new_content">'+ simple_content +'.....</div>';
			}else{
				new_noti = '<div id="new_vow">New thing in <div class="nick" style="display: inline">'+nick+"</div>'s post </div>";
				new_noti =  new_noti + '<div id="new_content"><a href="/user/post?id='+pid+'">'+ simple_content +'....</a></div>';
			}
			noti_txt = noti_txt + new_noti;
		}
		
		var x = document.getElementById("main").innerHTML;
		var username=getCookie("loadPost");
		if(txt != old_txt && username ==""){
			old_txt = txt;
			txt = txt + x;
			document.getElementById("main").innerHTML = txt;
		}
	
	}
	xmlhttp.open("POST", "/user/function/new", true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send();
}