$('#typeDescTree').treegrid({
	url	: urlBasePath+'/typedesc/readAll.do',
	idField:'id',
	treeField:'text',
	toolbar : '#struTool',
	columns:[[
		{title:'ID',field:'id',checkbox:'true'},
		
		{title:'问题描述名称',field:'text'},
		{title:'问题类型编号',field:'val0'},
		{title:'问题类型名称',field:'val1'},
		{title:'备注/联系方式',field:'val2'},
	]],
	striped:'true',
	loadMsg:'正在加载数据',
	
});

//打开修改窗口
function editOrg() {
	if($('#typeDescTree').treegrid('getSelected')){
		$('#editf').form('clear');
		var org=$('#typeDescTree').treegrid('getSelected');
		
		$('#updatePdId').val(org.id);
		$('#updatePdName').val(org.text);
		$('#updatePdTypeId').combobox({
			valueField : 'dictDkey',
			textField : 'dictValue',
			url : urlBasePath+'/dict/readByType.do?dictType=pdType',
			mode : 'remote',
		});
		$('#updatePdTypeId').combobox('select',org.val1);
		$('#updatePdContant').val(org.val2);
		$("#updatePid").combotree({
			url : urlBasePath+'/typedesc/readAll.do',
		});
		$("#updatePid").combotree('setValue',org.val3);
		$('#editForm').dialog('open');
	}else{
	    $.messager.alert({title:'提示',msg:'请先选择要操作的组织机构',icon:'error',});		
	}
}

//更新数据
function updateOrg(){
	if( ! $('#updateName').validatebox('isValid') ) {
		$('#updateName').focus();
	}else if (! $('#updateNo').validatebox('isValid')) {
		$('#updateNo').focus();
  	}else {
 		$.ajax({
			url : urlBasePath+'/typedesc/updateByKey.do',
			headers:{Accept:"application/json;charset=utf-8"},
			type : 'post',
			data : {
				pdId : $('#updatePdId').val(),
				pdName : $('#updatePdName').val(),
				pdTypeId : $('#updatePdTypeId').combobox('getValue'),
				pdPid : $('#updatePdPid').combotree('getValue'),
				pdcontant : $('#updatePdContant').val(),
			},
			beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
			success	: function (result) {
				$.messager.progress('close');
				showTips(result.respmesg);
				if (result.respcode==0) {
    				$('#editForm').dialog('close');
    				$('#typeDescTree').treegrid('reload');
				}
			},
		});//ajax	 
	}
}

//提交要保存的数据
function createTypeDesc(){
	if( ! $('#createPdName').validatebox('isValid') ) {
		$('#createPdName').focus();
  	}else {
 		$.ajax({
			url : urlBasePath+'/typedesc/insert.do',
			headers:{Accept:"application/json;charset=utf-8"},
			type : 'post',
			data : {
				pdName : $('#createPdName').val(),
				pdTypeId : $('#createPdTypeId').combobox('getValue'),
				pdPid : $('#createPdPid').combotree('getValue'),
				pdcontant : $('#createPdContant').val(),
			},
			beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
			success	: function (result) {
				$.messager.progress('close');
				showTips(result.respmesg);
				if (result.respcode==0) {
    				$('#createForm').dialog('close');
    				$('#typeDescTree').treegrid('reload');
    				$('#createf').form('reset');
				}
			},
		});//ajax	 
	}
}

//删除数据
function deleteOrg() {
	if($('#typeDescTree').treegrid('getSelected')){
		var name = $('#typeDescTree').treegrid('getSelected').text;
		var id = $('#typeDescTree').treegrid('getSelected').id;
		
		$.messager.confirm('删除问题提示','确定要删除【' + id + '】吗？<br/>注意：需先删除该问题下的关联问题！',function(r) {
			if(r) {
				$.ajax({
					url:urlBasePath+'/typedesc/deleteByKey.do',
					headers:{Accept:"application/json;charset=utf-8"},
					type : 'post',
					data : {
						id : id,
					},
					beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
					success	: function (result) {
						$.messager.progress('close');
						showTips(result.respmesg);
						if (result.respcode==0) {
		    				$('#typeDescTree').treegrid('reload');
						}
					},
				});//ajax delete
			}
		});
	}else{
	    $.messager.alert({title:'提示',msg:'请先选择要操作的组织机构',icon:'error',});		
	}

}

/* end org js */