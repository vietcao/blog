<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Post"%>
<%@page import="util.MothHashMap"%>
<%	response.setContentType("xml");
	String success = (String)request.getAttribute("success");
	Post post = (Post)request.getAttribute("post");
%>
<newpost>
	<success><%=success %></success>
	<post>
		<id><%=post.getId() %></id>
		<content><%=post.getContent() %></content>
		<day><%=post.getTime_post().getDate() %></day>
		<month><%=MothHashMap.mothHM[post.getTime_post().getMonth()] %></month>
		<year><%=post.getTime_post().getYear()+1900 %></year>
		<like><%=post.getNumber_of_like() %></like>
	</post>
</newpost>