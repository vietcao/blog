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
<script type="text/javascript"> document.cookie = "loadPost=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/";</script>
<script src="/javascript/jquery-1.10.2.js"></script>
<script src="/javascript/loadmorePost.js"></script>
<script src="/javascript/search.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Blog</title>
</head>
<body>
	<% User user = (User)request.getAttribute("user_logined"); %>
	<div id="utilitytab">
		<h2 style="width: 30%; left: 20%;top:0px;position: absolute;" >Your Blog ! Stay touch with everyone </h2>
		<a href="/user/index"><input type="button" style="position: absolute; top: 10px;right: 30%;" value="Home"></a>
	</div>
	<div id="profile">
		
		<%= user.getNick() %><br/>
		<%= user.getUsername() %><br/>
		<%= user.getBirth() %><br/>
		<%= user.getAbout() %>
	</div>
	<div id="main">
		<% ArrayList<Post> arr_post = (ArrayList<Post>)request.getAttribute("arr_post");
			for(Post e : arr_post){ %>
				<% Date date = e.getTime_post();%>
				<div class="main_element"> 
					<div class = "userwraper">
						<a href="/user/<%=e.getUser().getUsername()%>?id=<%=e.getId() %>"><div class="nick"><%= e.getUser().getNick() %></div></a>
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
						<a href = "/post/comment/new?id=<%=e.getId()%>"> + Comment</a>
					</div>
				</div>
				<br/>
		<% } %>

		
	</div>
	<div id="listfriend">
		<br/>
		<form name="searchform">
			<input type="text" name="searchkeyword" size="30"><br/>
			<input type="button" name="searchbtn" value="Get some friend" onclick="search()">
		</form>
		<% ArrayList<User> arr_user = (ArrayList<User>)request.getAttribute("arr_user"); %>
		<% if (arr_user == null) {%> null <%} %>
				<% for(User e : arr_user){ %>
			<div>
				<a href="/user/<%= e.getUsername()%>?id=<%=e.getId() %>"><%= e.getNick() %> </a>				
			</div><br/>
		<%} %>
	</div>
</body>
</html>