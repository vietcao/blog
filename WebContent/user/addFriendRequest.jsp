<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% response.setContentType("xml"); String success = (String)request.getAttribute("success"); %>
<addfriend>
	<success>
		<value><%=success%></value>
	</success>
</addfriend>