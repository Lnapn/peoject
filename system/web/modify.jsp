<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>register page</title>
		<link rel="stylesheet" href="./css/register.css"/>
		<script>
			window.onload = function(){
				var user = document.getElementById("username");
				var span = document.getElementById("nameError");
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
			}
			
		</script>
	</head>
	<body>
		<div class="box">
			<div class="left">
				<img src="img/pic1.png" />
			</div>
			<div class="right">
				<form action="">
					<h1>修改</h1>
					<div class="input">
						<input type="text" placeholder="username" class="user" id="username">
						<span class="error" id="nameError"></span>
					</div>
					<div class="input">
						<input type="password" placeholder="password" class="user">
						<span class="error"></span>
					</div>
					<div class="input">
						<input type="password" placeholder="phonenumber" class="user">
						<span class="error"></span>
					</div>
					<div class="input">
						<input type="password" placeholder="age" class="user">
						<span class="error"></span>
					</div>
					<button>修改完毕</button>
				</form>
			</div>
		</div>
	</body>
</html>
