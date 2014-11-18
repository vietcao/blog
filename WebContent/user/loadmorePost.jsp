<%@page import="util.MothHashMap"%>
<%@page import="model.Post"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% response.setContentType("xml"); ArrayList<Post> arr_post = (ArrayList<Post>)request.getAttribute("arr_post"); %>
<morepost>
	<% for (Post e: arr_post){ %>
		<post>
			<uid><%=e.getUser().getId()%></uid>
			<username><%=e.getUser().getUsername()%></username>
			<nick><%=e.getUser().getNick()%></nick>
			<pid><%=e.getId() %></pid>
			<content><%=e.getContent()%></content>
			<month><%=MothHashMap.mothHM[e.getTime_post().getMonth()] %></month>
			<date><%=e.getTime_post().getDate() %></date>
			<year><%=e.getTime_post().getYear()+1900%></year>
			<numberoflike><%=e.getNumber_of_like()%></numberoflike>
		</post>
	<%}%>
</morepost>