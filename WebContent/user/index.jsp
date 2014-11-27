<%@page import="java.sql.Timestamp"%>
<%@page import="util.MothHashMap"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="model.User"%>
<%@page import="sun.font.EAttribute"%>
<%@page import="model.Post"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<script type="text/javascript"> document.cookie = "loadPost=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/";</script>
<script src="/javascript/jquery-1.10.2.js"></script>
<script src="/javascript/getCookie.js"></script>
<script src="/javascript/loadmorePost.js"></script>
<script src="/javascript/search.js"></script>
<script src="/javascript/getInfoFriendRequest.js"></script>
<script src="/javascript/new.js"></script>
<script src="/javascript/like.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Blog</title>
</head>
<body>
	<% User user = (User)request.getAttribute("user_logined"); %>
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
		<%= user.getBirth() %><br/> 
		About: <br/> 
		 <%= user.getAbout() %>

		</div>
	</div>
	<div id="listfriend">
		<br/>
		<div style="position: absolute; left: 2%;">
		<form name="searchform">
			<input type="text" name="searchkeyword" size="30"><br/>
			<input type="button" name="searchbtn" value="Get some friend" onclick="search()">
		</form>
		<% ArrayList<User> arr_user = (ArrayList<User>)request.getAttribute("arr_user"); %>
		<% if (arr_user == null) {%> null <%} %>
				<% for(User e : arr_user){ %>
			<div>
				<a class="nick" href="/user/<%= e.getUsername()%>?id=<%=e.getId() %>"><%= e.getNick() %> </a>				
			</div><br/>
		<%} %>
		</div>
	</div>
	<div id="main">
		<br/>
		<% ArrayList<Post> arr_post = (ArrayList<Post>)request.getAttribute("arr_post");
			for(Post e : arr_post){ %>
				<% Timestamp date = e.getTime_post();%>
				<div class="main_element"> 
					<div class = "userwraper">
						<a href="/user/<%=e.getUser().getUsername()%>?id=<%=e.getId() %>"><div class="nick"><%= e.getUser().getNick() %></div></a>
					</div>
					<div class="timepost">
						 Posted at: <%=MothHashMap.mothHM[date.getMonth()]%> <%=String.valueOf(date.getDate())%> <%= String.valueOf(date.getYear()+1900) %>						
					</div>
					<div class ="numberoflike" >
						<img class="likeicon" src="/likeIcon.png"> <div style="display: inline;" id="p_<%=e.getId()%>"><%=e.getNumber_of_like()%></div> people like this..
					</div><br/>
					<div class = "contentwraper"><%=  e.getContent()  %> </div>
					<div class = "postfunction">
						<a role="button" href="#" onclick="likePost(<%=e.getId()%>)"> + Like</a>
						<a href = "/user/post?id=<%=e.getId()%>"> + Comment</a>
						<%if(user.getId() == e.getUser().getId()) {%>
						<div style="position: absolute; right: 2%; display: inline;">
						<a href = "/user/post/edit?id=<%=e.getId() %>"> edit</a>
						<a role="button" href="#" onclick="delPost(<%=e.getId()%>)"> delete</a>
						</div>
						<%}%>
					</div>
				</div>
				<br/>
		<% } %>

		
	</div>

</body>
</html>