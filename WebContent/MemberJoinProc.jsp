<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<h2> 회원 정보 </h2>
				
					<table width="400" border="1" bordercolor="gray">
						<tr height="40">
							<td width="150" align="center"> 아이디 </td>
							<td width="250" align="center">${bean.id }</td>
						</tr>
						<tr height="40">
							<td width="150" align="center"> 패스워드 </td>
							<td width="250" align="center">${bean.password }</td>
						</tr>
						<tr height="40">
							<td width="150" align="center"> 이메일 </td>
							<td width="250" align="center">${bean.email }</td>
						</tr>
						<tr height="40">
							<td width="150" align="center"> 전화 </td>
							<td width="250" align="center">${bean.tel }</td>
						</tr>
						<tr height="40">
							<td width="150" align="center"> 주소 </td>
							<td width="250" align="center">${bean.address }</td>
						</tr>
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