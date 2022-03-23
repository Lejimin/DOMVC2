<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <style>
        *{padding: 0px; margin: 0px 0px;}

        #header{width: 100%; height: 60px; background-color: black; color: white;}

        #nav{width: 100%; height: 50px; background-color: dimgray;}
        #navlist{list-style: none; display: inline-block;}
        #navlist>li{display: inline-block; margin-top: 10px; margin-left: 40px;}
        #navlist>li>a{text-decoration: none; color: white; font-size: 20px; margin-left: 160px; margin-right: 160px;}

        #contdiv{ width: 100%; height: 700px; margin-top: 2px;}
        
        #footdiv{background-color: black; width: 100%; height: 400px; margin-top: 3px;}
    </style>
<body>
    <div id="wrapper">
        <div id="header">
            <center>
                <h1>WELCOME HOME</h1>
            </center>
        </div>
        <div id="nav">
            <div id="navdiv">
                <ul id="navlist">
                        <li><a href="Login.jsp">Login</a></li>
                        <li><a href="MemberJoin.jsp">Join</a></li>
                        <li><a href="MemberJoinProc.jsp">MyPage</a></li>
                        <li><a href="#">board</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div id="content">
        <div id="contdiv">
			<center>
				<h2> 회원 리스트 </h2>
						
				<table width="800" border="1" bordercolors="gray">
					<tr height ="40">
						<td align="center" width="50"> 아이디 </td>
						<td align="center" width="50"> 이름 </td>
						<td align="center" width="150"> 이메일 </td>
						<td align="center" width="150"> 주소 </td>
					</tr>
				<c:forEach var="v" items="${v }">
					<tr height ="40">
						<td align="center" width="50">${v.id }</td>
						<td align="center" width="50">${v.name }</td>
						<td align="center" width="150"><a href="#">${v.email }</a></td>
						<td align="center" width="150">${v.address }</td>
					</tr>
				</c:forEach>
				</table>
			</center>
        </div>
    </div>

    <div id="footer">
        <div id="footdiv">

        </div>

    </div>
</body>
</html>