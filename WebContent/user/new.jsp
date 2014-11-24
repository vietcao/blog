<%@page import="java.util.ArrayList"%>
<%@page import="model.Post"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	response.setContentType("xml");
	ArrayList<Post> arr_post = (ArrayList<Post>)request.getAttribute("arr_post");
%>
 
<new>
	<%for(Post e: arr_post){ %>
		<post>
			<id><%= e.getId() %></id>
			<content><%= e.getContent() %></content>
			<userid><%= e.getUser().getId() %></userid>
			<nick><%= e.getUser().getNick() %></nick>
		</post>
	<%} %>
</new>