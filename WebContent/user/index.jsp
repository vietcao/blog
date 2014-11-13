<%@page import="sun.font.EAttribute"%>
<%@page import="model.Post"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="stylesheet/main.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="utilitytab">
		<h2 style="width: 30%; left: 20%;top:0px;position: absolute;" >Your Blog ! Stay touch with everyone </h2>
		<button style="position: absolute; top: 10px;right: 30%;">Contact us !</button>
	</div>
	<div id="profile"></div>
	<div id="listfriend"></div>
	<div id="main">
		<% ArrayList<Post> arr_post = (ArrayList<Post>)request.getAttribute("arr_post");
			for(Post e : arr_post){ %>
				<div class="post"> <%= e.getContent() %> </div>
		<% } %>
		
		
	
	
	
	
	</div>
</body>
</html>