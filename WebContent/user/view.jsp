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
<script src="/javascript/getCookie.js"></script>
<script src="/javascript/getInfoFriendRequest.js"></script>
<script src="/javascript/new.js"></script>
<title>User Page</title>
</head>
<body>
	<% 
	 User user = (User)request.getAttribute("user"); Date birth1= user.getBirth();
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
		Birth day: <br/> 
		<div id="birth_user"><div id="day" style="display: inline"><%= birth1.getDate() %></div> - <div id="month" style="display: inline"><%=birth1.getMonth()+ 1%></div> - <div id="year" style="display: inline"><%=birth1.getYear()+1900%></div></div>
		About: <br/> 
		<div id="about_user"><%= user.getAbout() %></div>

		</div>
	</div>
	
	<div id="main">
		<br/>
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
					<div class ="numberoflike">
						<img class="likeicon" src="/likeIcon.png"> <%=e.getNumber_of_like()%> people like this..
					</div><br/>
					<div class = "contentwraper"><%=  e.getContent()  %> </div>
					<div class = "postfunction">
						<a role="button" href="#" onclick="alert(<%=e.getId()%>)"> + Like</a>
						<a href = "/user/post?id=<%=e.getId()%>"> + Comment</a>
					</div>
				</div>
				<br/>
		<% } %>

		
	</div>
	<div id="listfriend">
		<div style="position: relative; left: 2%;">
		<%=user.getNick()%>'s Friends
		<br/>	
		<% ArrayList<User> arr_user = (ArrayList<User>)request.getAttribute("arr_user"); %>
		<% for(User e : arr_user){ %>
			<div id="<%=e.getId()%>">
				<a class="nick" href="/user/<%= e.getUsername()%>?id=<%=e.getId() %>"><%= e.getNick() %> </a>			
			</div><br/>
		<%} %>
		</div>
	</div>
</body>
</html>