$('#orgTree').treegrid({
	url	: urlBasePath+'/org/readAll.do',
	idField:'id',
	treeField:'text',
	toolbar : '#struTool',
	columns:[[
		{title:'ID',field:'id',checkbox:'true'},
		{title:'机构名称',field:'text'},
		{title:'机构编号',field:'val0'},
		{title:'机构类型',field:'val1'},
		{title:'联系方式',field:'val2'},
	]],
	striped:'true',
	loadMsg:'正在加载数据',
	
});

function editOrg() {
	if($('#orgTree').treegrid('getSelected')){
		$('#editf').form('clear');
		var org=$('#orgTree').treegrid('getSelected');
		$('#updateId').val(org.id);
		$('#updateName').val(org.text);
		$('#updateNo').val(org.val0);
		$('#updateType').combobox({
			valueField : 'dictDkey',
			textField : 'dictValue',
			url : urlBasePath+'/dict/readByType.do?dictType=orgtype',
			mode : 'remote',
		});
		$('#updateType').combobox('select',org.val1);
		$('#updateContant').val(org.val2);
		$("#updatePid").combotree({
			url : urlBasePath+'/org/readAll.do',
		});
		$("#updatePid").combotree('setValue',org.val3);
		$('#editForm').dialog('open');
	}else{
	    $.messager.alert({title:'提示',msg:'请先选择要操作的组织机构',icon:'error',});		
	}
}

function updateOrg(){
	if( ! $('#updateName').validatebox('isValid') ) {
		$('#updateName').focus();
	}else if (! $('#updateNo').validatebox('isValid')) {
		$('#updateNo').focus();
  	}else {
 		$.ajax({
			url : urlBasePath+'/org/updateByKey.do',
			headers:{Accept:"application/json;charset=utf-8"},
			type : 'post',
			data : {
				orgId : $('#updateId').val(),
				orgName : $('#updateName').val(),
				orgNo : $('#updateNo').val(),
				orgType : $('#updateType').combobox('getValue'),
				orgPid : $('#updatePid').combotree('getValue'),
				contant : $('#updateContant').val(),
			},
			beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
			success	: function (result) {
				$.messager.progress('close');
				showTips(result.respmesg);
				if (result.respcode==0) {
    				$('#editForm').dialog('close');
    				$('#orgTree').treegrid('reload');
				}
			},
		});//ajax	 
	}
}

function createOrg(){
	if( ! $('#createName').validatebox('isValid') ) {
		$('#createName').focus();
	}else if (! $('#createNo').validatebox('isValid')) {
		$('#createNo').focus();
  	}else {
 		$.ajax({
			url : urlBasePath+'/org/insert.do',
			headers:{Accept:"application/json;charset=utf-8"},
			type : 'post',
			data : {
				orgId : $('#createId').val(),
				orgName : $('#createName').val(),
				orgNo : $('#createNo').val(),
				orgType : $('#createType').combobox('getValue'),
				orgPid : $('#createPid').combotree('getValue'),
				contant : $('#createContant').val(),
			},
			beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
			success	: function (result) {
				$.messager.progress('close');
				showTips(result.respmesg);
				if (result.respcode==0) {
    				$('#createForm').dialog('close');
    				$('#orgTree').treegrid('reload');
    				$('#createf').form('reset');
				}
			},
		});//ajax	 
	}
}

function deleteOrg() {
	if($('#orgTree').treegrid('getSelected')){
		$.messager.confirm('删除机构提示','确定要删除【' + $('#orgTree').treegrid('getSelected').text + '】吗？<br/>注意：需先删除机构下的分支机构与用户！',function(r) {
			if(r) {
				$.ajax({
					url:urlBasePath+'/org/deleteByKey.do',
					headers:{Accept:"application/json;charset=utf-8"},
					type : 'post',
					data : {
						orgId : $('#orgTree').treegrid('getSelected').id,
					},
					beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
					success	: function (result) {
						$.messager.progress('close');
						showTips(result.respmesg);
						if (result.respcode==0) {
		    				$('#orgTree').treegrid('reload');
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