/* 用户管理部分 */
$('#userData').datagrid({
	url	: urlBasePath+'/userinfo/selectByWhere.do',
	toolbar : '#toolsbar',
	idField:'id',
	singleSelect:true,
	pagination:true,
	columns:[[
		{title:'ID',field:'userId',checkbox:'true'},
		{title:'所属部门',field:'orgId'},
		{title:'工号',field:'jobId'},
		{title:'条线',field:'lineId'},
		{title:'组编号',field:'groupId'},
		{title:'登陆名',field:'userName'},
		{title:'性别',field:'sex'},
		{title:'真实姓名',field:'realName'},
		{title:'电话',field:'phone'},
		{title:'地址',field:'address'},
		{title:'锁定标志',field:'locked'},
	]],
	striped:'true',
	loadMsg:'正在加载数据',
	
});

$('#userData').datagrid('getPager').pagination({
     pageSize:10,
     pageList:[10,20,30],
     beforePageText:'第',
     afterPageText:'页       共{pages}页',
     displayMsg:'当前显示{from}-{to}条记录      共{total}条记录',
    
});

function openCreateForm(){
	$('#editForm').dialog({
		title:'增加用户',
		buttons:[
					{text:'保存',iconCls:'icon-ok',handler:function() {createUser();}},
					{text:'取消',iconCls:'icon-cancel',handler:function() {$('#editForm').dialog('close');$('#editf').form('reset');}},
				],
	});
	$('#editForm').dialog('open');
}

function openEditForm(){
	if($('#userData').datagrid('getSelected')){
		var user = $('#userData').datagrid('getSelected');
		$('#updateId').val(user.id);
		$('#updateUsername').val(user.username);
		$('#updateRealname').val(user.realname);
		$('#updateOrgid').combotree('setValue',user.orgid);
		$('#updateContact').val(user.contact);
		$.ajax({
			url : urlBasePath+'/userinfo/readRoleByUser.do',
			headers:{Accept:"application/json;charset=utf-8"},
			type : 'post',
			data : {
				id:$('#updateId').val(),
			},
			beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
			success	: function (result) {
				$.messager.progress('close');
				$('#updateRoleList').combobox('setValues',result);
			},
		});//ajax
		
		$('#editForm').dialog({
			title:'编辑用户',
			buttons:[
						{text:'重置密码',iconCls:'icon-key',handler:function() {updateUserPassword();}},
						{text:'保存',iconCls:'icon-ok',handler:function() {updateUser();}},
						{text:'取消',iconCls:'icon-cancel',handler:function() {$('#editForm').dialog('close');$('#editf').form('reset');}},
					],
		});
		$('#editForm').dialog('open');
	}else{
		$.messager.alert({title:'提示',msg:'请先选择要操作的用户',icon:'error',});	
	}
}

function createUser() {
	
	if( ! $('#updateUsername').validatebox('isValid') ) {
		$('#updateUsername').focus();
	}else if (! $('#updateRealname').validatebox('isValid')) {
		$('#updateRealname').focus();
  	}else {
  		var rolelist = $('#updateRoleList').combobox('getValues');
  		var rolestring = '';
  		for(var i=0;i<rolelist.length;i++) {
  			if(rolestring!='') rolestring += ',';
  			rolestring += rolelist[i];
  		}
 		$.ajax({
			url : urlBasePath+'/userinfo/insert.do',
			headers:{Accept:"application/json;charset=utf-8"},
			type : 'post',
			data : {
				username : $('#updateUsername').val(),
				realname : $('#updateRealname').val(),
				orgid : $('#updateOrgid').combotree('getValue'),
				phone : $('#updatePhone').val(),
				roles:rolestring,
			},
			beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
			success	: function (result) {
				$.messager.progress('close');
				if (result.respcode==0) {
					$('#userData').datagrid('reload');
					$('#editForm').dialog('close');
					$('#editf').form('reset');
					$.messager.confirm('请登记重要信息',result.respmesg);
				}else{
					showTips(result.respmesg);
				}
			},
		});//ajax	 
	}
}

function updateUser() {
	if( ! $('#updateUsername').validatebox('isValid') ) {
		$('#updateUsername').focus();
	}else if (! $('#updateRealname').validatebox('isValid')) {
		$('#updateRealname').focus();
  	}else {
  		var rolelist = $('#updateRoleList').combobox('getValues');
  		var rolestring = '';
  		for(var i=0;i<rolelist.length;i++) {
  			if(rolestring!='') rolestring += ',';
  			rolestring += rolelist[i];
  		}
 		$.ajax({
			url : urlBasePath+'/userinfo/updateByKey.do',
			headers:{Accept:"application/json;charset=utf-8"},
			type : 'post',
			data : {
				id : $('#updateId').val(),
				username : $('#updateUsername').val(),
				realname : $('#updateRealname').val(),
				orgid : $('#updateOrgid').combotree('getValue'),
				contact : $('#updateContact').val(),
				roles:rolestring,
			},
			beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
			success	: function (result) {
				$.messager.progress('close');
				showTips(result.respmesg);
				if (result.respcode==0) {
					$('#userData').datagrid('reload');
					$('#editForm').dialog('close');
					$('#editf').form('reset');
				}
			},
		});//ajax	 
	}
}

function updateUserPassword() {
	$.ajax({
		url : urlBasePath+'/userinfo/updateUserResetPassword.do',
		headers:{Accept:"application/json;charset=utf-8"},
		type : 'post',
		data : {
			id : $('#updateId').val(),
		},
		beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
		success	: function (result) {
			$.messager.progress('close');
			if (result.respcode==0) {
				$.messager.confirm('请登记重要信息',result.respmesg);
			}else{
				showTips(result.respmesg);
			}
		},
	});//ajax	 
}

function deleteObject() {
	alert($('#userData').datagrid('getSelected').userId);
	if($('#userData').datagrid('getSelected')){
		$.messager.confirm('删除用户提示','确定要删除【' + $('#userData').datagrid('getSelected').realName + '】吗？',function(r) {
			if(r) {
				$.ajax({
					url:urlBasePath+'/userinfo/deleteByKey.do',
					headers:{Accept:"application/json;charset=utf-8"},
					type : 'post',
					data : {
						id : $('#userData').datagrid('getSelected').userId,
					},
					beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
					success	: function (result) {
						$.messager.progress('close');
						showTips(result.respmesg);
						if (result.respcode==0) {
							$('#userData').datagrid('reload');
						}
					},
				});//ajax delete
			}
		});
	}else{
		$.messager.alert({title:'提示',msg:'请先选择要操作的用户',icon:'error',});	
	}
}