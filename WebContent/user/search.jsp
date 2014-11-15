<%@page import="java.util.ArrayList" import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% response.setContentType("xml"); ArrayList<User> arr_user= (ArrayList<User>)request.getAttribute("arr_user");%>
<search>
	<% for(User e: arr_user){ %>
			<user>
				<id> <%= e.getId() %></id>
				<username> <%= e.getUsername() %> </username>
				<nick>	<%= e.getNick() %> </nick>
				<birth> <%= e.getBirth() %> </birth>
				<about> <%= e.getAbout() %> </about>
			</user>
	<% } %>
</search>