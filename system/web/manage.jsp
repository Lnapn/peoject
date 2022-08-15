<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome To Consumption System</title>
		<link rel="stylesheet" href="./css/manage.css"/>
	</head>
		<div class="box">
			<div class="left">
				<div class="topic">
					<div class="pic1"><a href="welcome.jsp" target="content"></a></div>
					<div class="title">Consume Admin</div>
				</div>
				<div>
					<div class="people">
						<div class="head"></div>
						<div class="wel">
							<div class="welfont1">Welcome</div>
							<div class="welfont2">${User.fname}</div>
						</div>
					</div>
					<div class="function">
						<a class="fun" href="/system/user/show" target="content">家庭成员</a>
						<a class="fun" href="/system/consume/show" target="content">消费记录</a>
						<a class="fun" href="/system/user/exit">安全退出</a>
						<div >当前人数${Online}</div>
					</div>
				</div>
			</div>
			<div class="right">
				<div class="nav">
					<div class="part1">
						<div class="pic3"><a href=""></a></div>
						<div class="pic3"><a href=""></a></div>
						<div class="pic3"><a href=""></a></div>
						<div class="pic3"><a href=""></a></div>
					</div>
					<div class="part2">
						<div class="pic"><a href=""></a></div>
						<input type="text" placeholder="Search" class="search"/>
						<div class="pic" ><a href=""></a></div>
						<div class="pic2" ><a href=""></a></div>
						<div class="pic2" ><a href=""></a></div>
						<div class="pic2" ><a href=""></a></div>
					</div>
				</div>
				<div>
					<iframe class="detail" src="welcome.jsp" name="content"></iframe>
				</div>
			</div>
			<div class="bottom">
				<div class="copyright">更多查看方式:请致电 404-5000-2333</div>
				<hr />
				<div class="copyright">Copyright 2022 Lnapn. 保留所有权力。</div>
			</div>
		</div>
		
	</body>
</html>
