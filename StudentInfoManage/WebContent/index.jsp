<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function restValue() {
		document.getElementById("userName").value = "";
		document.getElementById("password").value = "";
	}
</script>
<title>学生信息管理系统</title>
</head>
<body>
	<form action="login" medthod="POST">
		<div align="center" style="padding-top: 50px">
			<table width="740" height="500" background="images/login.jpg">
				<tr height="180">
					<td colspan="4"></td>
				</tr>
				<tr height="10">
					<td width="40%"></td>
					<td width="10%">用户名</td>
					<td><input type="text" value="" id="userName" name="userName"></td>
					<td width="30%"></td>
				</tr>
				<tr height="10">
					<td width="40%"></td>
					<td width="10%">密码</td>
					<td><input type="password" value="" id="password" name="password"></td>
					<td width="30%"></td>
				</tr>
				<tr height="10">
					<td width="40%"></td>
					<td width="10%"><input type="submit" value="提交" id="submit"></td>
					<td><input type="button" value="重置" onclick="restValue()"></td>
					<td width="30%"></td>
				</tr>
				<tr height="10">
				<td width="40%"></td>
				<td colspan="3">
					<font color="red">${error }</font>
				</td>
			</tr>
				<tr>
					<td colspan="4"></td>
				</tr>

			</table>
		</div>
	</form>



</body>
</html>