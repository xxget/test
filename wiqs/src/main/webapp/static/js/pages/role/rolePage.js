$('#showData').datagrid({
	url	: urlBasePath+'/role/selectByWhere.do',
	toolbar : '#toolsbar',
	idField:'id',
	columns:[[
		{title:'ID',field:'roleId',checkbox:'true'},
		{title:'名称',field:'roleName'},
		{title:'锁定',field:'roleIsLock'},
	]],
	singleSelect:true,
	pagination:true,
	striped:'true',
	loadMsg:'正在加载数据',
});

$('#showData').datagrid('getPager').pagination({
    pageSize:10,
    pageList:[10,20,30],
    beforePageText:'第',
    afterPageText:'页       共{pages}页',
    displayMsg:'当前显示{from}-{to}条记录      共{total}条记录',
   
});

//条件查询
//--个性化条件部分
function searchObject(){
	$('#showData').datagrid('load',{
		type:$("#searchType").val(),
	});
}

//--打开新增窗口，个性化title部分
function openCreateForm(){
	$('#editForm').dialog({
		title:'增加',
		buttons:[
					{text:'保存',iconCls:'icon-ok',handler:function() {createObject();}},
					{text:'取消',iconCls:'icon-cancel',handler:function() {$('#editForm').dialog('close');$('#editf').form('reset');}},
				],
	});
	$('#editForm').dialog('open');
}

//--打开修改窗口，个性化数据获取部分，title
function openEditForm(){
	if($('#showData').datagrid('getSelected')){
		var selectRow = $('#showData').datagrid('getSelected');
		$('#updateId').val(selectRow.roleId);
		$.ajax({
			url : urlBasePath+'/role/readPermissionArrayByRole.do',
			headers:{Accept:"application/json;charset=utf-8"},
			type : 'post',
			data : {
				roleId:$('#updateId').val(),
			},
			beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
			success	: function (result) {
				$.messager.progress('close');
				$('#updatePermissionList').combotree('setValues',result);
			},
		});//ajax	 
		//--需个性化部分
		$("#updateName").val(selectRow.roleName);
		$('#updateLocked').combobox('select',selectRow.roleIsLock);
		//--结束
		$('#editForm').dialog({
			title:'编辑',
			buttons:[
						{text:'保存',iconCls:'icon-ok',handler:function() {updateObject();}},
						{text:'取消',iconCls:'icon-cancel',handler:function() {$('#editForm').dialog('close');$('#editf').form('reset');}},
					],
		});
		$('#editForm').dialog('open');
	}else{
		$.messager.alert({title:'提示',msg:'请先选择要操作的对象',icon:'error',});	
	}
}

//--保存
//需个性化 1.数据校验  2.url 3.提交数据集data域
function createObject() {
	if( ! $('#updateName').validatebox('isValid') ) {
		$('#updateName').focus();
  	}else {
  		var pmlist = $('#updatePermissionList').combotree('getValues');
  		var pmstring = '';
  		for(var i=0;i<pmlist.length;i++) {
  			if(pmstring!='') pmstring += ',';
  			pmstring += pmlist[i];
  		}
 		$.ajax({
			url : urlBasePath+'/role/insert.do',
			headers:{Accept:"application/json;charset=utf-8"},
			type : 'post',
			data : {
				roleName:$('#updateName').val(),
				roleIsLock:$('#updateLocked').combobox('getValue'),
				pms:pmstring,
			},
			beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
			success	: function (result) {
				$.messager.progress('close');
				showTips(result.respmesg);
				if (result.respcode==0) {
					$('#showData').datagrid('reload');
					$('#editForm').dialog('close');
					$('#editf').form('reset');
				}
			},
		});//ajax	 
	}
}

//--保存修改
//需个性化 1.数据校验  2.url 3.提交数据集data域
function updateObject() {
	if( ! $('#updateName').validatebox('isValid') ) {
		$('#updateName').focus();
  	}else {
  		var pmlist = $('#updatePermissionList').combotree('getValues');
  		var pmstring = '';
  		for(var i=0;i<pmlist.length;i++) {
  			if(pmstring!='') pmstring += ',';
  			pmstring += pmlist[i];
  		}
 		$.ajax({
			url : urlBasePath+'/role/updateByKey.do',
			headers:{Accept:"application/json;charset=utf-8"},
			type : 'post',
			data : {
				roleId : $('#updateId').val(),
				roleName:$('#updateName').val(),
				roleIsLock:$('#updateLocked').combobox('getValue'),
				pms:pmstring,
			},
			beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
			success	: function (result) {
				$.messager.progress('close');
				showTips(result.respmesg);
				if (result.respcode==0) {
					$('#showData').datagrid('reload');
					$('#editForm').dialog('close');
					$('#editf').form('reset');
				}
			},
		});//ajax	 
	}
}

//--只需个性化 1.提示部分 2.ajax 的 url !
// $('#showData').datagrid('getSelected').xxx
function deleteObject() {
	if($('#showData').datagrid('getSelected')){
		$.messager.confirm('删除提示','确定要删除【' + $('#showData').datagrid('getSelected').roleName + '】吗？<br>注意，该角色下的权限和用户映射会同步删除！',function(r) {
			if(r) {
				$.ajax({
					url:urlBasePath+'/role/deleteByKey.do',
					headers:{Accept:"application/json;charset=utf-8"},
					type : 'post',
					data : {
						roleId : $('#showData').datagrid('getSelected').roleId,
					},
					beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
					success	: function (result) {
						$.messager.progress('close');
						showTips(result.respmesg);
						if (result.respcode==0) {
							$('#showData').datagrid('reload');
						}
					},
				});//ajax delete
			}
		});
	}else{
		$.messager.alert({title:'提示',msg:'请先选择要操作的对象',icon:'error',});	
	}
}