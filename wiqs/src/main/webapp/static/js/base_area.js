$('#showData').treegrid({
	url	: urlBasePath+'/area/readAllAsTree.do',
	idField:'val0',
	treeField:'text',
	toolbar : '#toolsbar',
	columns:[[
		{title:'ID',field:'val0',checkbox:'true'},
		{title:'地区名称',field:'text'},
		{title:'地区编号',field:'id'},
		{title:'级别',field:'val1'},
		{title:'状态',field:'val2'},
	]],
	striped:'true',
	loadMsg:'正在加载数据',
	
});

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
		//--开始
		$('#updateId').val(selectRow.val0);
		$("#updateName").val(selectRow.text);
		$("#updateAreano").val(selectRow.id);
		$("#updateLevel").combobox('select',selectRow.val1);
		$('#updateState').combobox('select',selectRow.val2);
		$("#updateParent").combotree('setValue',selectRow.val3);
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
	}else if( ! $('#updateAreano').validatebox('isValid') ) {
		$('#updateAreano').focus();
	}else {
		$.ajax({
			url : urlBasePath+'/area/insert.do',
			headers:{Accept:"application/json;charset=utf-8"},
			type : 'post',
			data : {
				areano:$('#updateAreano').val(),
				name:$('#updateName').val(),
				level:$('#updateLevel').combobox('getValue'),
				parentno : $('#updateParent').combotree('getValue'),
				state:$('#updateState').combobox('getValue'),
			},
			beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
			success	: function (result) {
				$.messager.progress('close');
				showTips(result.respmesg);
				if (result.respcode==0) {
					$('#showData').treegrid('reload');
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
		$.ajax({
			url : urlBasePath+'/area/updateByKey.do',
			headers:{Accept:"application/json;charset=utf-8"},
			type : 'post',
			data : {
				id : $('#updateId').val(),
				areano:$('#updateAreano').val(),
				name:$('#updateName').val(),
				state:$('#updateState').combobox('getValue'),
				level:$('#updateLevel').combobox('getValue'),
				parentno : $('#updateParent').combotree('getValue'),
			},
			beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
			success	: function (result) {
				$.messager.progress('close');
				showTips(result.respmesg);
				if (result.respcode==0) {
					$('#showData').treegrid('reload');
					$('#editForm').dialog('close');
					$('#editf').form('reset');
				}
			},
		});//ajax	 
	}
}

//--只需个性化 1.提示部分 2.ajax 的 url !
//$('#showData').datagrid('getSelected').xxx
function deleteObject() {
	if($('#showData').datagrid('getSelected')){
		$.messager.confirm('删除提示','确定要删除【' + $('#showData').treegrid('getSelected').text + '】吗？',function(r) {
			if(r) {
				$.ajax({
					url:urlBasePath+'/area/deleteByKey.do',
					headers:{Accept:"application/json;charset=utf-8"},
					type : 'post',
					data : {
						id : $('#showData').treegrid('getSelected').val0,
					},
					beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
					success	: function (result) {
						$.messager.progress('close');
						showTips(result.respmesg);
						if (result.respcode==0) {
							$('#showData').treegrid('reload');
						}
					},
				});//ajax delete
			}
		});
	}else{
		$.messager.alert({title:'提示',msg:'请先选择要操作的对象',icon:'error',});	
	}
}

/* end file base_area.js */