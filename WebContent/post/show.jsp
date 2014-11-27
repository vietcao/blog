<%@page import="util.GetCookie"%>
<%@page import="model.User"%>
<%@page import="model.Comment"%>
<%@page import="java.util.ArrayList"%>
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
<script src="/javascript/jquery-1.10.2.js"></script>
<script src="/javascript/getCookie.js"></script>
<script type="text/javascript">
	function append(x){
	
		var nt= document.getElementById("add_comment_area");
	    $(x).insertBefore(nt);
	}
	function addComment(_postid){
		xmlhttp = new  XMLHttpRequest();
		nick = getCookie('nick'); 
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState==4 && xmlhttp.status==200) {
				
				
				input=xmlhttp.responseXML;
				success = input.documentElement.getElementsByTagName("success"); 
				if (success[0].childNodes[0].nodeValue == 1){
					comment = input.documentElement.getElementsByTagName("comment");
					id = comment[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
					content = comment[0].getElementsByTagName("content")[0].childNodes[0].nodeValue;
					day = comment[0].getElementsByTagName("day")[0].childNodes[0].nodeValue;
					month = comment[0].getElementsByTagName("month")[0].childNodes[0].nodeValue;
					year = comment[0].getElementsByTagName("year")[0].childNodes[0].nodeValue;
					like = comment[0].getElementsByTagName("like")[0].childNodes[0].nodeValue;
					
					new_comment = '<div class="main_element" name="post_show"> <div class="comment_element"> <div style="display: inline;" class="nick">'+nick+' </div> <div class="comment_wraper" style="display: inline;">'+content+'</div> <div class="comment_info"> Posted at: '+month+' '+day+' '+year+'-<a href="#" onclick="likeComment('+id+')">+ Like this</a> -<div style="display: inline;" id="c_'+id+'">'+like+' likes.</div> </div>  </div> </div>';
					new_comment = new_comment +'<div style="position: absolute; right: 2%; display: inline;"> <a href = "/user/post/comment/edit?id='+id+'"> edit</a> <a role="button" href="#" onclick="delComment('+id+')"> delete</a> </div><br/>';
					append(new_comment); 
				}else{
					alert("Something wrong here. Try again !");	
				}
			}
		}
		data = document.getElementById("add_comment").value;
		xmlhttp.open("POST", "/user/post/comment/new", true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send("data="+data+"&postid="+_postid);
	}
</script>
<title>Insert title here</title>
</head>
<body>
<%
	Post e = new Post();
	ArrayList<Comment> arr_com = new ArrayList<Comment>();
	e = (Post)request.getAttribute("post");
	arr_com = (ArrayList<Comment>)request.getAttribute("arr_com");
	String sid_login = GetCookie.run(request, "id");
	int id_login = Integer.valueOf(sid_login);
%>
	<div id="utilitytab">
		<h2 style="width: 30%; left: 20%;top:0px;position: absolute;" >Your Blog ! Stay touch with everyone </h2>
		<div id="friend_div"><a id="homebtn"href="/user/index"><input type="button" value="Home"></a></div>
	</div>
	<div id="profile"></div>
	<div id="listfriend"></div>
	<div id="main">
		<br/>
		<div class="main_element">		
		<% Timestamp date = e.getTime_post();%>
			<div class="timepost">
				 Posted at: <%=MothHashMap.mothHM[date.getMonth()]%> <%=String.valueOf(date.getDate())%> <%= String.valueOf(date.getYear()+1900) %>						
			</div>
			<div class ="numberoflike" >
				<img class="likeicon" src="/likeIcon.png"> <%=e.getNumber_of_like()%> people like this..
			</div><br/>
			<div class = "contentwraper">
				<%=e.getContent()%>			
		</div><br/>
			
		<%for(Comment com : arr_com) {%>
			<div class="main_element" id ="post_show">	
			<div class="comment_element">
				<div style="display: inline;" class="nick"><%=com.getUser().getNick() %> : </div>
				<div class="comment_wraper" style="display: inline;"><%=com.getContent()%></div>
				<div class="comment_info" > Posted at: <%=MothHashMap.mothHM[date.getMonth()]%> <%=String.valueOf(date.getDate())%> <%= String.valueOf(date.getYear()+1900) %>
					- <a href="#" onclick="likeComment(<%=com.getId()%>)">+ Like this</a> - <div style="display: inline;" id="c_<%=com.getId() %>"><%=com.getLike()%></div> likes.						
				</div>
				<%if(id_login == com.getUser().getId()) {%>
				<div style="position: absolute; right: 2%; display: inline;">
				<a href = "/user/post/comment/edit?id=<%=com.getId() %>"> edit</a>
				<a role="button" href="#" onclick="delComment(<%=com.getId()%>)"> delete</a>
				</div>
				<%} %>
			</div>
			</div><br/>
		<%} %>
		<div id="add_comment_area">
		<input type="text" id="add_comment" size="50" style="position: relative; width: 70%;">
		<input type="button" name="add_comment_btn" value="add comment" onclick="addComment(<%=e.getId()%>)">
		</div>
		</div>
		
		
		
		
	</div>
	
	
	
	

</body>
</html>