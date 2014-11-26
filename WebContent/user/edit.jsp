<%@page import="java.sql.Date"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
	response.setContentType("xml");
	String success = (String)request.getAttribute("success");
	User user = (User)request.getAttribute("user");
	Date date = user.getBirth();
%>
<edit_user>
	<success><%=success %></success>
	<user>
		<nick><%=user.getNick() %></nick>
		<day><%=date.getDate() %></day>
		<month><%=date.getMonth()+1 %></month>
		<year><%=date.getYear()+1900%></year>
		<about><%=user.getAbout() %></about>
	</user>
</edit_user>