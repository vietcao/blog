<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="util.MothHashMap"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="model.User"%>
<%@page import="sun.font.EAttribute"%>
<%@page import="model.Post"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/stylesheet/main.css">
<link rel="stylesheet" type="text/css" href="/stylesheet/post.css">
<link rel="stylesheet" type="text/css" href="/stylesheet/noti.css">
<style type="text/css">
	#nick_user{
		position: relative;
		font-size: 25px;
	}
	#profile_content{
		position: relative;
		left: 5%;
	}
	
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script> document.cookie ="loadPost=false; path=/"; </script>
<script src="/javascript/jquery-1.10.2.js"></script>
<script src="/javascript/getCookie.js"></script>
<script src="/javascript/getInfoFriendRequest.js"></script>
<script src="/javascript/new.js"></script>
<script src="/javascript/editUser.js"></script>
<script src="/javascript/like.js"></script>
<script type="text/javascript">
	function test(x){
		
		var nt= document.getElementById("new_post_element");
   	    $(x).insertAfter(nt);
	}
	
	function newPost(){
		var xmlhttp, data;
		var new_post, post, id, content, day, month,year, nick, username, user_id, success;
		
		xmlhttp = new  XMLHttpRequest();	
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			document.getElementById("txt_post").value="";
			input=xmlhttp.responseXML;
			
			nick = document.getElementById("nick_user").innerHTML;
			username = getCookie('username');
			sid = getCookie('id');
			success = input.documentElement.getElementsByTagName("success"); 
			if (success[0].childNodes[0].nodeValue == 1){
				

				new_post = input.documentElement.getElementsByTagName("post");
				pid = new_post[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				content = new_post[0].getElementsByTagName("content")[0].childNodes[0].nodeValue;
				day = new_post[0].getElementsByTagName("day")[0].childNodes[0].nodeValue;
				month = new_post[0].getElementsByTagName("month")[0].childNodes[0].nodeValue;
				year = new_post[0].getElementsByTagName("year")[0].childNodes[0].nodeValue;
				numberoflike = new_post[0].getElementsByTagName("like")[0].childNodes[0].nodeValue;
				
				post = '<br/><div class="main_element"><div class = "userwraper"><a href="/user/';
				post = post + username;
				post = post + "?id=";
				post = post + sid;
				post = post + '"><div class="nick">';
				post = post + nick;
				post = post + '</div></a>';
				post = post + '</div><div class="timepost">';
				post = post + 'Posted at: '+ month+' ' + day + ' ' + year;
				post = post + '</div><div class ="numberoflike" id="p_'+pid+'"><img class="likeicon" src="/likeIcon.png"> ';
				post = post + numberoflike;
				post = post + ' people like this..</div><br/><div class = "contentwraper">';
				post = post + content;
				post = post + '</div><div class = "postfunction"><a role="button" href="#" onclick="likePost(';
				post = post + pid;
				post = post + ')"> + Like </a>';
				post = post + '<a href="/user/post?id=';
				post = post + pid;
				post = post + '"> + Comment';
				post = post + '<div style="position: absolute; right: 2%; display: inline;"> <a href = "/user/post/edit?id='+pid+'> edit</a> <a role="button" href="#" onclick="delPost('+pid+')"> delete</a> </div>';
	
				post = post + '</a></div></div><br/>';
				
				test(post);
			}else{
				alert("Something wrong here. Just try again !");
			}
			}
		}
	
	data = document.getElementById("txt_post").value;
	
	xmlhttp.open("POST", "/user/post/new", true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send("data="+data);
	}


</script>
<title>Your Page</title>
</head>
<body>
	<% User user = (User)request.getAttribute("user"); Date birth1= user.getBirth();
	 	if(birth1 == null){
	 		birth1 = new Date(01,01,01);
	 	}
	%>
	<div id="utilitytab">
		<h2 style="width: 30%; left: 20%;top:0px;position: absolute;" >Your Blog ! Stay touch with everyone </h2>
		<div id="friend_div"><a id="homebtn"href="/user/index"><input type="button" value="Home"></a></div>
		<div id="friend_div"><input type= "button" id="friendbtn" onclick="showNoti('f')" value="+ F"></div>
		<div id="friend_div"><input type="button" id="newbtn" onclick = "showNoti('n')" value="New"></div>
		<div id="friendarea" style="position: relative; top:7px; left: 58%; width: 20%; background-color: white; z-index: 1"></div>
	</div>
	<div id="profile">
		<div id="profile_content">
		<a class="nick" id="nick_user" href="/user/<%=user.getUsername() %>"><%= user.getNick() %></a><br/>
		<a href="#" style="color: black; text-decoration: underline;" onclick="editUser()">Edit Profile</a><br/>
		Birth day: <br/> 
		<div id="birth_user"><div id="day" style="display: inline"><%= birth1.getDate() %></div> - <div id="month" style="display: inline"><%=birth1.getMonth()+ 1%></div> - <div id="year" style="display: inline"><%=birth1.getYear()+1900%></div></div>
		About: <br/> 
		<div id="about_user"><%= user.getAbout() %></div>

		</div>
	</div>
	
	<div id="main">
		<br/>
		<div class="main_element" id="new_post_element">
			<textarea style="position: relative; width: 95%;" id="txt_post" rows="6" ></textarea><br/>
			<input style="position:relative;  left: 85%;"type="button" name="post" value="New post" onclick="newPost()">
		</div><br/>
		<% ArrayList<Post> arr_post = (ArrayList<Post>)request.getAttribute("arr_post");
			for(Post e : arr_post){ %>
				<% Timestamp date = e.getTime_post();%>
				<div class="main_element"> 
					<div class = "userwraper">
						<a href="/user/<%=user.getUsername()%>?id=<%=user.getId() %>"><div class="nick"><%= user.getNick() %></div></a>
					</div>
					<div class="timepost">
						 Posted at: <%=MothHashMap.mothHM[date.getMonth()]%> <%=String.valueOf(date.getDate())%> <%= String.valueOf(date.getYear()+1900) %>						
					</div>
					<div class ="numberoflike" >
						<img class="likeicon" src="/likeIcon.png"> <div id="p_<%=e.getId()%>" style="display: inline;"><%=e.getNumber_of_like()%></div> people like this..
					</div><br/>
					<div class = "contentwraper"><%=  e.getContent()  %> </div>
					<div class = "postfunction">
						<a role="button" href="#" onclick="likePost(<%=e.getId()%>)"> + Like</a>
						<a href = "/user/post?id=<%=e.getId()%>"> + Comment</a>
						<div style="position: absolute; right: 2%; display: inline;">
						<a href = "/user/post/edit?id=<%=e.getId() %>"> edit</a>
						<a role="button" href="#" onclick="delPost(<%=e.getId()%>)"> delete</a>
						</div>
					</div>
				</div>
				<br/>
		<% } %>

		
	</div>
	<div id="listfriend">
		<div style="position: relative; left: 2%;">
		Your List Friend
		<br/>	
		<% ArrayList<User> arr_user = (ArrayList<User>)request.getAttribute("arr_user"); %>
		<% for(User e : arr_user){ %>
			<div id="<%=e.getId()%>">
				<a class="nick" href="/user/<%= e.getUsername()%>?id=<%=e.getId() %>"><%= e.getNick() %> </a>
				<input  style="position: absolute; right: 2%;"type="button" name="deny" value="Delete" onclick="delFriend(<%=e.getId() %>)">				
			</div><br/>
		<%} %>
		</div>
	</div>
	
</body>
</html>