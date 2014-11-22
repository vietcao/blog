<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
	response.setContentType("xml");
	String result = (String)request.getAttribute("result");
%>
<notif>
	<message>
		<result><%=result%></result>
	</message>
</notif>