function editUser(){
		var txt="";
		var nick, birth, about;
		var day, month, year;
		nick = document.getElementById("nick_user").innerHTML;
		birth = document.getElementById("birth_user").innerHTML;
		about = document.getElementById("about_user").innerHTML;
		
		day = document.getElementById("day").innerHTML;
		month = document.getElementById("month").innerHTML;
		year = document.getElementById("year").innerHTML;
		
		txt= 'Nick :<input type="text" id="nick_edit" value="'+nick+'"><br/><br/>';
		txt = txt + 'Birth :<input type="text" id="day_edit" size ="5" value="'+day+'">';
		txt = txt + '<input type="text" id="month_edit" size ="5" value="'+month+'">';
		txt = txt + '<input type="text" id="year_edit" size ="5" value="'+year+'"><br/><br/>';
		txt = txt + 'About : <textarea style="position: relative; width: 95%;" id="about_edit" rows="6" placeholder="'+about+'"></textarea> ';
		txt = txt + '<input type ="button" name="update" value="Up date " onclick="updateUser()" >';
		document.getElementById("profile").innerHTML = txt;
		
	}
function updateUser(){
		
		var nick, day, month, year, about;
		var data, success, user, username;
		
		xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
			
			if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			input=xmlhttp.responseXML;
			success = input.documentElement.getElementsByTagName("success");
			user = input.documentElement.getElementsByTagName("user");
			username = getCookie('username'); 
			nick = user[0].getElementsByTagName("nick")[0].childNodes[0].nodeValue;
			day = user[0].getElementsByTagName("day")[0].childNodes[0].nodeValue;
			month = user[0].getElementsByTagName("month")[0].childNodes[0].nodeValue;
			year = user[0].getElementsByTagName("year")[0].childNodes[0].nodeValue;
			about = user[0].getElementsByTagName("about")[0].childNodes[0].nodeValue;
			
			if(success[0].childNodes[0].nodeValue == 1){					
				document.getElementById("profile").innerHTML = '<br/> <h3 style="color: green; position: relative; left: 35%;">Success !	</h3>';			
			}else
				document.getElementById("profile").innerHTML = '<br/> <h3 style="color: green; position: relative; left: 30%;">Somthing wrong !	</h3>';	
			
			setTimeout(function(){
				document.getElementById("profile").innerHTML = '<div id="profile_content"> <a class="nick" id="nick_user" href="/user/'+username+'">'+nick+'</a><br/> <a href="#" style="color: black; text-decoration: underline;" onclick="editUser()">Edit Profile</a><br/> Birth day: <br/> <div id="birth_user"><div id="day" style="display: inline">'+day+'</div> - <div id="month" style="display: inline">'+month+'</div> - <div id="year" style="display: inline">'+year+'</div></div> About: <br/> <div id="about_user">'+about+'</div>';
			},2000);
		
			}
		};
		
		nick = document.getElementById("nick_edit").value;
		day = document.getElementById("day_edit").value;
		month =document.getElementById("month_edit").value;
		year =document.getElementById("year_edit").value;
		about = document.getElementById("about_edit").value;
		
		data='nick='+nick+'&day='+day+'&month='+month+'&year='+year+'&about='+about;
		xmlhttp.open("POST", "/user/function/edit", true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send(data);
	}