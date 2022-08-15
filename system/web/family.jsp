<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>家庭</title>

		<style>
			html,body{
				width: 100%;
				height: 100%;
				background-color: #f5f5f7;
			}
			*{
				padding: 0;
				margin: 0;
			}
			div{

				color: #000000;
				font-size: 30px;
			}
			a{
				color: #000000;
				text-decoration: none;
				font-family: 'icomoon';
			}
			.box{
				box-shadow: 0 14px 28px rgba(0, 0, 0, .25), 0 10px 10px rgba(0, 0, 0, .22);
				margin: auto;
				overflow: hidden;
				width: 1580px;
				height: 810px;
				background-color: #f5f5f7;
			}
			.search{
				margin: auto;
				height: 130px;
				background-color:  beige ;

			}
			.sea{
				height: 30px;
				width: 250px;
				float: left;
				text-indent: 5px;
				outline: none;
				border: none;
				border-radius: 10px;
				margin-left: 620px;
				margin-top: 50px;

			}
			.add{
				background-color: white;
				float: left;
				height: 30px;
				width: 45px;
				margin-left: 945px;
				margin-top: -32px;
				font-size: 20px;
				border-radius: 5px 5px 5px 5px;
				padding-top: 2px;
				padding-left: 5px;
			}
			.sel{
				float: left;
				height: 30px;
				width: 45px;
				margin-left: 880px;
				margin-top: -30px;
				font-size: 20px;
				border-radius: 5px 5px 5px 5px;
				padding-top: 2px;
				padding-left: 5px;
				background-color: white;
			}
			.show{
				margin-top: 10px;
				background-color:  beige ;
				height: 670px;
			}
			.Info{
				margin-left: 290px;
				overflow: hidden;
				font-size: 16px;
			}
			.no{
				float: left;
				background-color: #fafafa;
				margin-top: 50px;
				width: 200px;
				height: 21px;
				font-size: 16px;
				text-indent: 84px;
			}
			.name{
				float: left;
				background-color: #fafafa;
				margin-top: 50px;
				width: 200px;
				height: 21px;
				font-size: 16px;
				text-indent: 84px;
			}
			.phone{
				float: left;
				background-color: #fafafa;
				margin-top: 50px;
				width: 200px;
				height: 21px;
				font-size: 16px;
				text-indent: 84px;
			}
			.age{
				float: left;
				background-color: #fafafa;
				margin-top: 50px;
				width: 200px;
				height: 21px;
				font-size: 16px;
				text-indent: 84px;
			}
			.fun{
				float: left;
				background-color: #fafafa;
				margin-top: 50px;
				width: 200px;
				height: 21px;
				font-size: 16px;
				text-indent: 84px;
			}
			.rno{
				float: left;
				background-color: #999999;
				width: 200px;
				height: 21px;
				font-size: 16px;
				text-indent: 92px;
			}
			.rname{
				float: left;
				background-color: #999999;
				width: 200px;
				height: 21px;
				font-size: 16px;
				text-indent: 75px;
			}
			.rphone{
				float: left;
				background-color: #999999;
				width: 200px;
				height: 21px;
				font-size: 16px;
				text-indent: 50px;
			}
			.rage{
				float: left;
				background-color: #999999;
				width: 200px;
				height: 21px;
				font-size: 16px;
				text-indent: 87px;
			}
			.rdel{
				float: left;
				background-color: #999999;
				width: 200px;
				height: 21px;
				font-size: 16px;
				text-indent: 62px;
			}
		</style>
		<script>
			function del(no){
				if (confirm("确认要删除本条数据吗？")){
					window.location.href = "/system/user/del?no=" + no
				}
			}
		</script>
	</head>
	<body>
		<div class="box">
			<div class="search">
				<input type="text" placeholder="Input the name" class="sea"/>
				<div class="sel"><a href="#">搜索</a></div>
				<div class="add"><a href="/system/userAdd.jsp">添加</a></div>
			</div>
			<div class="show">
				<div class="Info">
					<div class="no">序号</div>
					<div class="name">姓名</div>
					<div class="phone">电话</div>
					<div class="age">年龄</div>
					<div class="fun">功能</div>
				</div>
				<c:forEach items="${Users}" varStatus="userStatus" var="user">
					<div class="Info">
						<div class="rno">${user.no}</div>
						<div class="rname">${user.fname}</div>
						<div class="rphone">${user.phone}</div>
						<div class="rage">${user.age}</div>
						<div class="rdel"><a href="javascript:void(0)" onclick="del(${user.no})">删除</a>&nbsp;&nbsp;&nbsp;<a href="/system/user/modify?no=${user.no}">修改</a></div>
					</div>
				</c:forEach>
			</div>
		</div>
	</body>
</html>
