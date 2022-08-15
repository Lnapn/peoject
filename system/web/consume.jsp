<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>consume</title>
		<link rel="stylesheet" type="text/css" href="./css/consume.css"/>
		<style>
			html,body{
				width: 100%;
				height: 100%;
				background-color: #f5f5f7;
				font-family: Arial;
			}
			*{
				padding: 0;
				margin: 0;
			}
			a{
				color: #000000;
				text-decoration: none;
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
			.name{
				height: 30px;
				width: 250px;
				float: left;
				text-indent: 5px;
				outline: none;
				border: none;
				border-radius: 5px;

				margin-top: 50px;
				margin-right: 20px;
			}
			.type{
				height: 30px;
				width: 250px;
				float: left;
				text-indent: 5px;
				outline: none;
				border: none;
				border-radius: 5px;
				margin-left: 195px;
				margin-top: 50px;
				margin-right: 20px;
			}
			.year{
				height: 30px;
				width: 250px;
				float: left;
				text-indent: 5px;
				outline: none;
				border: none;
				border-radius: 5px;

				margin-top: 50px;
			}
			.month{
				margin-left: 20px;
				height: 30px;
				width: 250px;
				float: left;
				text-indent: 5px;
				outline: none;
				border: none;
				border-radius: 5px;
				margin-top: 50px;
			}
			.add{
				float: left;
				height: 30px;
				width: 45px;
				margin-left: 1339px;
				margin-top: -30px;
				font-size: 20px;
				background-color: white;
				border-radius: 5px 5px 5px 5px;
				padding-left: 5px;
			}
			.sel{
				float: left;
				height: 30px;
				width: 45px;
				margin-left: 1275px;
				margin-top: -30px;
				font-size: 20px;
				background-color: white;
				border-radius: 5px 5px 5px 5px;
				padding-left: 5px;
			}
			.show{
				margin-top: 10px;
				background-color:  beige ;
				height: 520px;
			}
			.Info{
				margin-left: 190px;
				overflow: hidden;
			}
			.no{
				float: left;
				background-color: #fafafa;
				margin-top: 50px;
				width: 200px;
				text-indent: 84px;
			}
			.time{
				float: left;
				background-color: #fafafa;
				margin-top: 50px;
				width: 200px;
				text-indent: 84px;
			}
			.type2{
				float: left;
				background-color: #fafafa;
				margin-top: 50px;
				width: 200px;
				text-indent: 84px;
			}
			.amount{
				float: left;
				background-color: #fafafa;
				margin-top: 50px;
				width: 200px;
				text-indent: 84px;
			}
			.consume{
				float: left;
				background-color: #fafafa;
				margin-top: 50px;
				width: 200px;
				text-indent: 76px;
			}
			.del{
				float: left;
				background-color: #fafafa;
				margin-top: 50px;
				width: 200px;
				text-indent: 84px;
			}
			.rno{
				float: left;
				background-color: #999999;
				width: 200px;
				text-indent: 92px;
				padding-top: 3px;
			}
			.rtime{
				float: left;
				background-color: #999999;
				width: 200px;
				text-indent: 50px;
				padding-top: 3px;
			}
			.rtype{
				float: left;
				background-color: #999999;
				width: 200px;
				text-indent: 84px;
			}
			.ramount{
				float: left;
				background-color: #999999;
				width: 200px;
				text-indent: 84px;
				padding-top: 3px;
			}
			.rconsume{
				float: left;
				background-color: #999999;
				width: 200px;
				text-indent: 84px;
				padding-top: 3px;
			}
			.rdel{
				float: left;
				background-color: #999999;
				width: 200px;
				text-indent: 68px;
			}
			.account{
				margin-top: -10px;
				height: 150px;
				background-color:  beige ;

			}
			.consumer{
				margin-top: 20px;
				margin-left: 140px;
				font-size: 20px;
				font-weight: bold;
				letter-spacing: 30px;
				padding-top: 5px;
			}
			.ctype{
				margin-left: 140px;
				font-size: 20px;
				font-weight: bold;
				letter-spacing: 17.5px;
				padding-top: 5px;
			}
			.total{
				margin-left: 140px;
				font-size: 20px;
				font-weight: bold;
				letter-spacing: 5px;
				padding-top: 5px;
			}
			.select{
				height: 30px;
				width: 45px;
				font-size: 20px;
				outline: none;
				border: none;
				background-color: white;
				border-radius: 5px 5px 5px 5px;
				margin-left: -2px;
			}
		</style>
		<script>
			function del(no){
				if (confirm("确认删除吗？")){
					document.location.href = "/system/consume/del?no=" + no
				}
			}
		</script>
	</head>
	<body>
		<div class="box">
			<div class="search">
				<form action="/system/consume/select">
					<input name="seltype" type="text" placeholder="Input the Type" class="type"/>
					<input name="selname" type="text" placeholder="Input the Name" class="name"/>
					<input name="selyear" type="text" placeholder="Input the Year" class="year"/>
					<input name="selmonth" type="text" placeholder="Input the Month" class="month"/>
					<div class="sel"><input class="select" type="submit" value="查找"></div>
				</form>
				<div class="add"><a href="/system/add.jsp">增加</a></div>
			</div>
			<div class="show">
				<div class="Info">
					<div class="no">序号</div>
					<div class="time">时间</div>
					<div class="type2">类型</div>
					<div class="amount">金额</div>
					<div class="consume">消费人</div>
					<div class="del">功能</div>
				</div>
				<c:forEach items="${Bill}" var="bill">
					<div class="Info">
						<div class="rno">${bill.no}</div>
						<div class="rtime">${bill.date}</div>
						<div class="rtype">${bill.type}</div>
						<div class="ramount">${bill.amount}</div>
						<div class="rconsume">${bill.consumer}</div>
						<div class="rdel"><a href="javascript:void(0)" onclick="del(${bill.no})">删除</a>&nbsp;&nbsp;&nbsp;<a href="/system/consume/modify?no=${bill.no}">修改</a></div>
					</div>
				</c:forEach>
			</div>
			<div class="account">
				<div class="consumer">消费人:${name}</div>
				<div class="ctype">消费类型:${type}</div>
				<div class="total">月度消费总额:${sumMonth}</div>
				<div class="total">年度消费总额:${sumYear}</div>
			</div>
		</div>
	</body>
</html>
