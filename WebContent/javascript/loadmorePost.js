
	function loadmorePost(){
		var xmlhttp;
		var txt, input,i,post,arr_post, id;
		var user, sid, username, nick;
		var content,pid,  time, month, date, year, numberoflike;
		xmlhttp = new XMLHttpRequest();
		id = getCookie('id');
		xmlhttp.onreadystatechange=function(){
			txt ="";
			input=xmlhttp.responseXML;
			
			arr_post = input.documentElement.getElementsByTagName("post");
			for(i=0; i< arr_post.length; i++){
				
				sid = arr_post[i].getElementsByTagName("uid")[0].childNodes[0].nodeValue;
				username =  arr_post[i].getElementsByTagName("username")[0].childNodes[0].nodeValue;
				nick =  arr_post[i].getElementsByTagName("nick")[0].childNodes[0].nodeValue;
				
				content = arr_post[i].getElementsByTagName("content")[0].childNodes[0].nodeValue;
				pid = arr_post[i].getElementsByTagName("pid")[0].childNodes[0].nodeValue;
				month =  arr_post[i].getElementsByTagName("month")[0].childNodes[0].nodeValue;
				date =  arr_post[i].getElementsByTagName("date")[0].childNodes[0].nodeValue;
				year =  arr_post[i].getElementsByTagName("year")[0].childNodes[0].nodeValue;
				numberoflike = arr_post[i].getElementsByTagName("numberoflike")[0].childNodes[0].nodeValue;
				
				post = '<div class="main_element"><div class = "userwraper"><a href="/user/';
				post = post + username;
				post = post + "?id=";
				post = post + sid;
				post = post + '"><div class="nick">';
				post = post + nick;
				post = post + '</div></a>';
				post = post + '</div><div class="timepost">';
				post = post + 'Posted at: '+ month+' ' + date + ' ' + year;
				post = post + '</div><div class ="numberoflike" ><img class="likeicon" src="/likeIcon.png">';
				post = post + '<div style="display: inline;" id="p_'+pid+'"> '+numberoflike +' </div>';
				post = post + 'people like this..</div><br/><div class = "contentwraper">';
				post = post + content;
				post = post + '</div><div class = "postfunction"><a role="button" href="#" onclick="likePost(';
				post = post + pid;
				post = post + ')"> + Like </a>';
				post = post + '<a href="/user/post?id=';
				post = post + pid;
				post = post + '"> + Comment';
				if(id == sid ){
					post = post + '<div style="position: absolute; right: 2%; display: inline;"> <a href = "/user/post/edit?id='+pid+'"> edit</a> <a role="button" href="#" onclick="delPost('+pid+')"> delete</a> </div>';
				}
				post = post + '</a></div></div><br/>';
				
				txt = txt + post;
				
			}
			if(txt == "") txt = '<br/><div style="text-align: center; color:#5F9EA0;">No more post to display</div>';
			var x = document.getElementById("main").innerHTML;
			txt = x+ txt;
			document.getElementById("main").innerHTML = txt;
		}
		xmlhttp.open("GET","/user/loadmorePost",true);
		xmlhttp.send();
	}


	window.onscroll = scroll;
	
	function scroll(){
			var username=getCookie("loadPost");
			if (username==""){
				if($(window).scrollTop() + $(window).height() == $(document).height() ) {
					loadmorePost();
				}
			}
	
	}
	