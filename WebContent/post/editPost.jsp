<%@page import="model.Post"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="util.MothHashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/stylesheet/main.css">
<link rel="stylesheet" type="text/css" href="/stylesheet/post.css">
<link rel="stylesheet" type="text/css" href="/stylesheet/noti.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	function updatePost(_id){
		
		xmlhttp = new  XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState==4 && xmlhttp.status==200) {
				
				input=xmlhttp.responseXML;
				success = input.documentElement.getElementsByTagName("success"); 
				if (success[0].childNodes[0].nodeValue == 1){
					alert("Success !");
					window.location.assign("/user/index");
				}else{
					alert("Something wrong here. Try again !");	
				}
			}
		}
		
		data= document.getElementById("txt_post").value;
		
		xmlhttp.open("POST", "/user/post/update", true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send("data="+data+"&id="+_id);
	}


</script>
<title>Edit Post</title>
</head>
<%
	Post e = new Post();
	e = (Post)request.getAttribute("post");
%>
<body>
	<div id="utilitytab">
		<h2 style="width: 30%; left: 20%;top:0px;position: absolute;" >Your Blog ! Stay touch with everyone </h2>
		<div id="friend_div"><a id="homebtn"href="/user/index"><input type="button" value="Home"></a></div>
	</div>
	<div id="profile"></div>
	<div id="listfriend"></div>
	<div id="main">
	<% Timestamp date = e.getTime_post();%>
		<div class="main_element"> 
			
			<div class="timepost">
				 Posted at: <%=MothHashMap.mothHM[date.getMonth()]%> <%=String.valueOf(date.getDate())%> <%= String.valueOf(date.getYear()+1900) %>						
			</div>
			<div class ="numberoflike">
				<img class="likeicon" src="/likeIcon.png"> <%=e.getNumber_of_like()%> people like this..
			</div><br/>
			<div class = "contentwraper">
				<textarea style="position: relative; width: 95%;" id="txt_post" rows="6"><%=e.getContent()%></textarea><br/>
				<input style="position: relative ;  left:68%;"type="button" name="update" value="Update" onclick="updatePost(<%=e.getId()%>)">
				<a href="/user/index"><input style="position:relative ; left:70%;"type="button" name="Cancel" value="Cancel"></a>				
			</div>
			
		</div>
	
	</div>
</body>
</html>