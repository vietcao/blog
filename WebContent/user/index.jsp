<%@page import="model.User"%>
<%@page import="sun.font.EAttribute"%>
<%@page import="model.Post"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="stylesheet/main.css">
<style type="text/css">
	.userwraper{
		position: relative;
		width: 50%;
		left:  25%;
	}



</style>
<script src="javascript/jquery-1.10.2.js"></script>
<script language="javascript">
	window.onscroll = scroll;
	function scroll(){
			if($(window).scrollTop() + $(window).height() == $(document).height() ) {
			alert("bottom!");
		}
	
	}
	
	function search(){
		var searchkeyword = searchform.searchkeyword.value;
		var xmlhttp;
		xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
		  	if (xmlhttp.readyState==4 && xmlhttp.status==200) {
		   		document.getElementById("main").innerHTML=xmlhttp.responseXML;
		    }
		  }
		xmlhttp.open("GET","/user/function/search?searchkeyword="+searchkeyword,true);
		xmlhttp.send();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="utilitytab">
		<h2 style="width: 30%; left: 20%;top:0px;position: absolute;" >Your Blog ! Stay touch with everyone </h2>
		<button style="position: absolute; top: 10px;right: 30%;">Contact us !</button>
	</div>
	<div id="profile">
		<% User user = (User)request.getAttribute("user_logined"); %>
		<%= user.getNick() %><br/>
		<%= user.getUsername() %><br/>
		<%= user.getBirth() %><br/>
		<%= user.getAbout() %>
	</div>
	<div id="main">
		<% ArrayList<Post> arr_post = (ArrayList<Post>)request.getAttribute("arr_post");
			for(Post e : arr_post){ %>
				<div class="main_element"> 
					<div class = "userwraper">
						<a href="/user/<%=e.getUser().getUsername()%>?id=<%=e.getId() %>"><%= e.getUser().getNick() %> </a>
					</div>
					<div class = "conteentwaper"><%=  e.getContent()  %> </div>
				</div>
				<br/>
		<% } %>

		<div>
			<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		</div>
	</div>
	<div id="listfriend">
		<br/>
		<form name="searchform">
			<input type="text" name="searchkeyword" size="30"><br/>
			<input type="button" name="searchbtn" value="Get some friend" onclick="search()">
		</form>
		<% ArrayList<User> arr_user = (ArrayList<User>)request.getAttribute("arr_user"); %>
		<% if (arr_user == null) {%> null <%} %>
				<% for(User e : arr_user){ %>
			<div>
				<a href="/user/<%= e.getUsername()%>?id=<%=e.getId() %>"><%= e.getNick() %> </a>				
			</div><br/>
		<%} %>
	</div>
</body>
</html>