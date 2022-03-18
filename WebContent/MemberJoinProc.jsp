<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>

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

</body>
</html>