<%@page import="com.sun.xml.internal.ws.api.ha.StickyFeature"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="stylesheet/main.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
</head>
<body>
	<div id="utilitytab">
		<h2 style="width: 30%; left: 20%;top:0px;position: absolute;" >Your Blog ! Stay touch with everyone </h2>
	</div>
	<div id="profile"></div>
	<div id="listfriend"></div>
	<div id="main">
		<div class="main_element">
			<% 
				String error = (String)request.getAttribute("error");	
			%>
			<%= error %>
			<input type="button" name="back" value="Go back" onclick="{window.history.back()}">	
			<div style="top: 1000px;position: relative;"></div>
		</div>
	</div>
	
</body>
</html>