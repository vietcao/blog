<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% response.setContentType("xml"); ArrayList<User> arr_request = (ArrayList<User>)request.getAttribute("arr_request"); %>
<inforequest>
<% for(User e : arr_request){%>
	<user>
		<id><%=e.getId() %></id>
		<username><%= e.getUsername() %></username>
		<nick><%= e.getNick() %></nick>	
	</user>
<%} %>
</inforequest>