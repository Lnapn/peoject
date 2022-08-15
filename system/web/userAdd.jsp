<%--
  Created by IntelliJ IDEA.
  User: Lnapn
  Date: 2022/8/1
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
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
            margin-left: 190px;
            overflow: hidden;
            font-size: 16px;
            outline: none;
            boder:none;
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
        .password{
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
            outline: none;
            border: none;
            float: left;
            background-color: #999999;
            width: 200px;
            height: 21px;
            font-size: 16px;
            text-indent: 92px;
        }
        .rname{
            outline: none;
            border: none;
            float: left;
            background-color: #999999;
            width: 200px;
            height: 21px;
            font-size: 16px;
            text-indent: 75px;
        }
        .rpassword{
            outline: none;
            border: none;
            float: left;
            background-color: #999999;
            width: 200px;
            height: 21px;
            font-size: 16px;
            text-indent: 75px;
        }
        .rphone{
            outline: none;
            border: none;
            float: left;
            background-color: #999999;
            width: 200px;
            height: 21px;
            font-size: 16px;
            text-indent: 50px;
        }
        .rage{
            outline: none;
            border: none;
            float: left;
            background-color: #999999;
            width: 200px;
            height: 21px;
            font-size: 16px;
            text-indent: 87px;
        }
        .rdel{
            outline: none;
            border: none;
            float: left;
            background-color: #999999;
            width: 200px;
            height: 21px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="box">
    <div class="search">
        <input type="text" placeholder="Input the name" class="sea"/>
        <div class="sel"><a href="#">搜索</a></div>
        <div class="add"><a href="/system/register.jsp">添加</a></div>
    </div>
    <div class="show">
        <div class="Info">
            <div class="no">序号</div>
            <div class="name">姓名</div>
            <div class="password">密码</div>
            <div class="phone">电话</div>
            <div class="age">年龄</div>
            <div class="fun">功能</div>
        </div>
        <c:choose>
            <c:when test="${not empty Users}">
                <c:forEach items="${Users}" varStatus="userStatus" var="user">
                    <div class="Info">
                        <form action="/system/user/alter">
                            <input name="no" class="rno" type="text" value="${user.no}">
                            <input name="name" class="rname" type="text" value="${user.fname}">
                            <input name="password" class="rpassword" type="text" value="${user.password}">
                            <input name="phone" class="rphone" type="text" value="${user.phone}">
                            <input name="age" class="rage" type="text" value="${user.age}">
                            <input class="rdel" type="submit" value="提交">
                        </form>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="Info">
                    <form action="/system/user/add">
                        <input name="no" class="rno" type="text" >
                        <input name="name" class="rname" type="text" >
                        <input name="password" class="rpassword" type="text" >
                        <input name="phone" class="rphone" type="text" >
                        <input name="age" class="rage" type="text" >
                        <input class="rdel" type="submit" value="提交">
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
