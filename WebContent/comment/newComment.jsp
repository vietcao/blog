<%@page import="java.sql.Timestamp"%>
<%@page import="model.Comment"%>
<%@page import="util.MothHashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
	response.setContentType("xml");
	String success = (String)request.getAttribute("success");
	Comment com = new Comment();
	com = (Comment)request.getAttribute("com");
	Timestamp timepost = com.getTime_post();
%>
<newComment>
	<success><%=success %></success>
	<comment>
		<id><%=com.getId() %></id>
		<content><%=com.getContent() %></content>
		<day><%=timepost.getDate() %></day>
		<month><%=MothHashMap.mothHM[timepost.getMonth()] %></month>
		<year><%=timepost.getYear()+1900 %></year>
		<like><%=com.getLike() %></like>
	</comment>
</newComment>