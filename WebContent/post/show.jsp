<%@page import="model.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Post"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="util.MothHashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/stylesheet/main.css">
<link rel="stylesheet" type="text/css" href="/stylesheet/post.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	Post e = new Post();
	ArrayList<Comment> arr_com = new ArrayList<Comment>();
	e = (Post)request.getAttribute("post");
	arr_com = (ArrayList<Comment>)request.getAttribute("arr_com");
%>
	<div id="utilitytab">
		<h2 style="width: 30%; left: 20%;top:0px;position: absolute;" >Your Blog ! Stay touch with everyone </h2>
	</div>
	<div id="profile"></div>
	<div id="listfriend"></div>
	<div id="main">
		<br/>
		<div class="main_element">		
		<% Timestamp date = e.getTime_post();%>
			<div class="timepost">
				 Posted at: <%=MothHashMap.mothHM[date.getMonth()]%> <%=String.valueOf(date.getDate())%> <%= String.valueOf(date.getYear()+1900) %>						
			</div>
			<div class ="numberoflike">
				<img class="likeicon" src="/likeIcon.png"> <%=e.getNumber_of_like()%> people like this..
			</div><br/>
			<div class = "contentwraper">
				<%=e.getContent()%>			
		</div><br/>
			
		<%for(Comment com : arr_com) {%>
			<div class="main_element">	
			<div class="comment_element">
				<div style="display: inline;" class="nick"><%=com.getUser().getNick() %> : </div>
				<div class="comment_wraper" style="display: inline;"><%=com.getContent()%></div>
				<div class="comment_info"> Posted at: <%=MothHashMap.mothHM[date.getMonth()]%> <%=String.valueOf(date.getDate())%> <%= String.valueOf(date.getYear()+1900) %>
					- <a href="#" onclick="likeComment(<%=com.getId()%>)">+ Like this</a> - <%=e.getNumber_of_like() %> likes.						
				</div>
			</div>
			</div><br/>
		<%} %>
		<input type="text" name="add_comment" size="50" style="position: relative; width: 70%;">
		<input type="button" name="add_comment_btn" value="add comment">
		</div>
		
		
		
		
	</div>
	
	
	
	

</body>
</html>