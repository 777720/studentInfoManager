<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

</script>
<title>学生信息管理</title>
</head>
<body style="margin: 5px">
	<table id="sg" title="学生信息" class="easyui-datagrid" fitColumns="true" pagination="true"
		rownumber="true" fit="true" url="studentList" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="id" width="50">编号</th>
				<th field="stuNo" width="50">学号</th>
				<th field="stuName" width="100">学生姓名</th>
				<th field="sex" width="250">性别</th>
				<th field="birthday" width="250">生日</th>
				<th field="gradeId" width="250">班级</th>
				<th field="email" width="250">邮箱</th>
				<th field="stuDesc" width="250">学生描述</th>
			</tr>
		</thead>
		<div id="tb">
			<div>
				<a href="#" class="easyui-linkbutton" iconCls="icon-add">添加</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove">删除</a>

			</div>
			<div>
				&nbsp学生名称：&nbsp
				<input type="text" id="s_studentName" name="s_studentName" />
				<a href="#" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			</div>
		</div>

	</table>
	<div id="dlg" class="easyui-dialog" style="width: 400px; height: 280px; padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">

		<form id="fm" method="post">
			<table>
				<tr>
					<td>学号：</td>
					<td><input type="text" name="stutNo" id="stuNo" class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td>名称：</td>
					<td><input type="text" name="stuName" id="stuName" class="easyui-validatebox"
							required="true" /></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td><input type="text" name="stuSex" id="stuSex" class="easyui-validatebox"
							required="true" /></td>
				</tr>
				<tr>
					<td>生日：</td>
					<td><input type="text" name="stuBrithday" id="stuBrithday" class="easyui-validatebox"
							required="true" /></td>
				</tr>
				<tr>
					<td>班级：</td>
					<td><input type="text" name="stuGradeId" id="stuGradeId" class="easyui-validatebox"
							required="true" /></td>
				</tr>
				<tr>
					<td>Email：</td>
					<td><input type="text" name="stuEmail" id="stuEmail" class="easyui-validatebox"
							required="true" /></td>
				</tr>
				<tr>
					<td>描述</td>
					<td><textarea name="stuDesc" id="stuDesc"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>


</body>
</html>