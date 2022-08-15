<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>register page</title>
		<link rel="stylesheet" href="./css/register.css"/>
		<style>
			html{
				padding: 0;
				margin: 0;
			}
			body {
				background: linear-gradient(100deg, #a29bfe, #fd79a8);
			}
			img{
				background-position: center;
				height: 500px;
				width: 400px;
			}
			.error{
				display: inline-block;
				width: 120px;
				float: right;
				margin-top: -20px;
				margin-right: 25px;
				font-size: 14px;
			}
			a{
				display: block;
				color: #333;
				font-size: 14px;
				text-decoration: none;
				float: right;
				height: 40px;
				width: 100px;
				margin-right: 100px;
				margin-top: 20px;
				color: #333;
			}
			.myspan{
				display: block;
				color: #333;
				font-size: 14px;
				margin-top: 20px;
				margin-bottom: 20px;
				float: left;
				margin-left: 145px;
			}
			.box{
				position:absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;
				box-shadow: 0 14px 28px rgba(0, 0, 0, .25), 0 10px 10px rgba(0, 0, 0, .22);
				flex-direction: column;
				justify-content: center;
				overflow: hidden;
				height: 500px;
				width: 1000px;
				border-radius: 20px 20px 20px 20px;
			}
			.left{
				height: 100%;
				width: 400px;
				float: left;
				background-color: beige;
			}
			h1{
				text-align: center;
				margin: 0;
				font-weight: bold;
				margin-top: 60px;
			}
			p {
				font-size: 20px;
				line-height: 20px;
				letter-spacing: .5px;
				margin: 20px 0 30px;
				text-align: center;
				color: white;
			}
			.user{
				outline: none;
				border: none;
				border-bottom: 1px solid black;
				margin-top: 20px;
				margin-left: 150px;
				font-size: 15px;
				color: #333;
				width: 300px;
				background: none;
				padding: 0 3px;
				height: 35px;
			}
			button {
				border-radius: 20px;
				border: none;
				background: linear-gradient(120deg, #3498db, #8e44ad);
				width: 300px;
				color: #fff;
				font-size: 12px;
				font-weight: bold;
				padding: 12px 100px;
				letter-spacing: 3px;
				margin-left: 150px;
				margin-top: 30px;
			}
			.right{
				height: 100%;
				width: 600px;
				float: left;
				background-color: #F5F5DC;
			}

		</style>
		<script>
			window.onload = function(){
				var user = document.getElementById("username");
				var span = document.getElementById("nameError");
				var pwd = document.getElementById("password");
				var pwdspan = document.getElementById("passwordError");
				var phone = document.getElementById("phone");
				var phoneError = document.getElementById("phoneError");
				var age = document.getElementById("age");
				var ageError = document.getElementById("ageError");
				var but = document.getElementById("but");
				user.onblur = function(){
					username = user.value;
					username = username.trim();
					if(username == ""){
						span.innerHTML = "<font color='red'>用户名不能为空</font>";
					}else{
						if(username.length < 4 || username > 9){
							span.innerHTML = "<font color='red'>用户名需在[4-8]</font>";
						}else{
							var regExp = /^[0-9a-zA-Z]+$/;
							if( regExp.test(username)){
								
							}else{
								span.innerHTML = "<font color='red'>用户名有特殊字符</font>";
							}
						}
					}
				}
				user.onfocus = function(){
					if(span.innerHTML != ""){
						user.value = "";
					}
					span.innerHTML = "";
				}
				
				pwd.onblur = function(){
					password = pwd.value;
					password = password.trim();
					if(password == ""){
						pwdspan.innerHTML = "<font color='red'>密码不能为空</font>"
					}else{
						if(password.length < 4 ){
							pwdspan.innerHTML = "<font color='red'>密码过短</font>"
						}
					}
				}
				
				pwd.onfocus = function(){
					if(pwdspan.innerHTML !=""){
						pwd.value = "";
					}
					pwdspan.innerHTML = "";
				}
				
				phone.onblur = function(){
					var regExp = /^1[34578]\d{9}$/;
					if(regExp.test(phone.value)){
						
					}else{
						phoneError.innerHTML = "<font color='red'>电话号码错误</font>"
					}
				}
				phone.onfocus = function(){
					phoneError.innerHTML = "";
				}

				age.onblur = function(){
					rage = age.value;
					var regExp = /^(?:[1-9][0-9]?|1[01][0-9]|120)$/;
					if(regExp.test(rage)){
						
					}else{
						ageError.innerHTML = "<font color='red'>年龄有误</font>"
					}
				}
				age.onfocus = function(){
					if(ageError.innerHTML != ""){
						age.value = "";
					}
					ageError.innerHTML = "";
				}
				
				but.onclick = function(){
					user.focus();
					user.blur();
					pwd.focus();
					pwd.blur();
					phone.focus();
					phone.blur();
					age.focus();
					age.blur();
					if(span.innerHTML == "" && pwdspan.innerHTML == "" && phoneError.innerHTML == "" && ageError.innerHTML == ""){
						var form = document.getElementById("submit")
						form.submit();
						return true;
					}
					return false;
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
				<form action="login.html" id="submit">
					<h1>注册</h1>
					<div class="input">
						<input name="user" type="text" placeholder="username" class="user" id="username">
						<span class="error" id="nameError"></span>
					</div>
					<div class="input">
						<input name="password" type="password" placeholder="password" class="user" id="password">
						<span class="error" id="passwordError"></span>
					</div>
					<div class="input">
						<input name="phone" type="text" placeholder="phonenumber" class="user" id="phone">
						<span class="error" id="phoneError"></span>
					</div>
					<div class="input">
						<input name="age" type="text" placeholder="age" class="user" id="age">
						<span class="error" id="ageError"></span>
					</div>
					<button id="but">注册</button>
				</form>
			</div>
		</div>
	</body>
</html>
