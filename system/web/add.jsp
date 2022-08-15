<%@ page import="java.util.List" %>
<%@ page import="com.javaweb.bean.Bill" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Lnapn
  Date: 2022/7/29
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
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
        .nt{
            height: 30px;
            width: 250px;
            float: left;
            text-indent: 5px;
            outline: none;
            border: none;
            border-radius: 5px;
            margin-left: 477.5px;
            margin-top: 50px;
            margin-right: 20px;
        }
        .ym{
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
            margin-left: 1085px;
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
            margin-left: 1020px;
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
            margin-left: 140px;
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
        .type{
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
            padding-top: 6px;
        }
        .rtime{
            float: left;
            background-color: #999999;
            width: 200px;
            padding-top: 6px;
            text-indent: 50px;
        }
        .rtype{
            float: left;
            background-color: #999999;
            width: 200px;
            padding-top: 6px;
        }
        .ramount{
            float: left;
            background-color: #999999;
            width: 200px;
            padding-top: 6px;
        }
        .rconsume{
            float: left;
            background-color: #999999;
            width: 200px;
            padding-top: 6px;
        }
        .but{
            float: left;
            background-color: #999999;
            width: 200px;
            text-indent: 0px;
            font-size: 16px;
        }
        input{
            border: none;
            text-indent: 84px;
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
    </style>
    <meta charset="utf-8">
    <title>consume</title>
    <link rel="stylesheet" href="./css/add.css"/>
    <script>
        window.onload = function () {
            var but = document.getElementById("submit");
            but.onclick = function () {
                var form = document.getElementById("form");
                form.submit();
            }
        }
    </script>
</head>
<body>
<div class="box">
    <div class="search">
        <input type="text" placeholder="Input the name/Type" class="nt"/>
        <input type="text" placeholder="Input the Year/Month" class="ym"/>
        <div class="add"><a href="">增加</a></div>
        <div class="sel"><a href="#">查找</a></div>
    </div>
    <div class="show">
        <div class="Info">
            <div class="no">序号</div>
            <div class="time">时间</div>
            <div class="type">类型</div>
            <div class="amount">金额</div>
            <div class="consume">消费人</div>
            <div class="del">功能</div>
        </div>
        <c:choose>
            <c:when test="${not empty Bill}">
                <c:forEach items="${Bill}" var="bill">
                    <div class="Info">
                        <form action="/system/consume/alter" id="form">
                            <input name = "no" class="rno" type="text" value="${bill.no}">
                            <input name ="date" class="rtime" type="text" value="${bill.date}">
                            <input name ="type" class="rtype" type="text" value="${bill.type}">
                            <input name ="amount" class="ramount" type="text" value="${bill.amount}">
                            <input name ="consumer" class="rconsume" type="text" value="${bill.consumer}">
                            <input class="but" type="submit" value="提交" id="submit"/>
                        </form>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="Info">
                    <form action="/system/consume/add" />
                        <input name = "no" class="rno" type="text" />
                        <input name ="date" class="rtime" type="text" />
                        <input name ="type" class="rtype" type="text" />
                        <input name ="amount" class="ramount" type="text" />
                        <input name ="consumer" class="rconsume" type="text" />
                        <input class="but" type="submit" value="提交" />
                    </form>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
    <div class="account">
        <div class="consumer">消费人:</div>
        <div class="ctype">消费类型:</div>
        <div class="total">月度消费总额:</div>
        <div class="total">年度消费总额:</div>
    </div>
</div>
</body>
</html>
