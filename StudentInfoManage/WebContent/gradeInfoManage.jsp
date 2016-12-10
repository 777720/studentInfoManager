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
	var url;
	function searchGrade() {
		$('#dg').datagrid('load', {
			gradeName : $('#s_gradeName').val()
		});

	}
	function deleteGrade() {
		var seletedRows = $("#dg").datagrid('getSelections');
		if (seletedRows.length == 0) {
			$.messager.alert("系统提示", "请选择你要删除的数据");
			return;
		}
		var strIds = [];
		for (var i = 0; i < seletedRows.length; i++) {
			strIds.push(seletedRows[i].id);
		}
		var ids = strIds.join(",");
		$.messager.confirm("系统提示", "您确认要删掉<font color=red>"
				+ seletedRows.length + "</font>条信息?", function(r) {
			if (r) {
				$.post("gradeDelete", {
					delids : ids
				}, function(result) {
					if (result.success) {
						$("#dg").datagrid("reload");
						$.messager.alert("系统提示", "您已删除<font color=red>"
								+ result.deleNum + "</font>条信息?");
					} else {
						$.messager.alert("系统提示", result.errorMsg);
					}
				}, "json");
			}
		});
	}

	//添加班级信息
	function openGradeAddDialog() {
		$('#dlg').dialog({
			title : '添加班级信息',
			width : 400,
			height : 300,
			closed : true,
			cache : false,
			modal : true,
		});
		$('#dlg').dialog('open');
		url="gradeSave";

	}
	//关闭对话框
	function closeGradeDialog() {
		$("#dlg").dialog("close");
		clearVal();
		//alert("滚");
	}
	//清除对话框中的内容
	function clearVal() {
		$("#gradeName").val("");
		$("#gradeDesc").val("");
		//alert("gun");
	}
	function saveGrade() {
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				if (result.errorMsg) {
					$.messager.alert("系统提示","添加失败");
				}else{
					$.messager.alert("系统提示","添加成功！");
					clearVal();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");//保存结束的时候要做的：1 关闭对话框  2 datagrid重新载入
				}
				
			}
		});
	}
	
	function modifyGrade() {
		var seletedRows = $("#dg").datagrid('getSelections');
		if (seletedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条数据");
			return;
		}
		var row=seletedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑班级信息");
		$("#fm").form("load",row);
		url="gradeSave?id="+row.id;
	}
</script>
<title>班级信息管理</title>
</head>
<body style="margin: 5px">
	<table id="dg" title="班级信息" class="easyui-datagrid" fitColumns="true" pagination="true"
		rownumber="true" fit="true" url="gradeList" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="id" width="50">编号</th>
				<th field="gradeName" width="100">班级名称</th>
				<th field="gradeDesc" width="250">班级描述</th>

			</tr>


		</thead>
		<div id="tb">
			<div>
				<a href="javascript:openGradeAddDialog()" class="easyui-linkbutton" iconCls="icon-add">添加</a>
				<a href="javascript:modifyGrade()" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
				<a href="javascript:deleteGrade()" class="easyui-linkbutton" iconCls="icon-remove">删除</a>

			</div>
			<div>
				&nbsp班级名称：&nbsp
				<input type="text" id="s_gradeName" name="s_gradeName" />
				<a href="javascript:searchGrade()" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			</div>
		</div>

	</table>
	<div id="dlg" class="easyui-dialog" style="width: 400px; height: 280px; padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">

		<form id="fm" method="post">
			<table>
				<tr>
					<td>班级名称：</td>
					<td><input type="text" name="gradeName" id="gradeName" class="easyui-validatebox"
							required="true" /></td>
				</tr>
				<tr>
					<td valign="top">班级描述：</td>
					<td><textarea rows="7" cols="30" name="gradeDesc" id="gradeDesc"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:saveGrade()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeGradeDialog()"class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>


</body>
</html>