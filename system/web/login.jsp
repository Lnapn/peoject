<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Login page</title>
		<link rel="stylesheet" href="./css/login.css"/>
		<script>
			window.onload = function(){
				if(window.top != window.self){
					window.top.location = window.self.location;
				}
				var user = document.getElementById("username");
				var span = document.getElementById("nameError");
				var submit = document.getElementById("submit");
				user.onblur = function(){
					username = user.value;
					username = username.trim();
					if(username == ""){
						span.innerHTML = "<font color='red'>用户名不能为空</font>"
					}
				}
				user.onfocus = function(){
					if(span.innerHTML != ""){
						user.value = "";
					}
					span.innerHTML = "";
				}
				submit.onclick = function(){
					user.focus();
					user.blur();
					if(span.innerHTML == ""){
						var form = document.getElementById("sub");
						form.submit();
						return true
					}
					return false
				}
			}
			
		</script>
	</head>
	<body>
		<div class="box">
			<div class="left">
				<img src="./img/pic1.png" />
			</div>
			<div class="right">
				<form action="/system/user/login" id="sub">
					<h1>登录</h1>
					<div class="input">
						<input name="user" type="text" placeholder="username" class="username" id="username">
						<span class="error" id="nameError"></span>
					</div>
					<div class="input">
						<input name="password" type="password" placeholder="password" class="userpassword">
						<span class="error"></span>
					</div>
					<a href="/system/register.jsp">注册用户</a>
					<span class="myspan">十天免登录<input name="free" value="1" type="checkbox" /></span>
					<button id="submit">登录</button>
				</form>
			</div>
		</div>
	</body>
</html>
