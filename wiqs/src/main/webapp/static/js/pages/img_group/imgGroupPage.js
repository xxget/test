//显示列表信息
$('#showData').datagrid({
	url	: urlBasePath+'/imggroup/selectByWhere.do',
	toolbar : '#toolsbar',
	idField:'id',
	columns:[[
		{title:'图片编号',field:'imgId',checkbox:'true'},
		{title:'图片组编号',field:'imgGroupId'},
		{title:'图片base64',field:'imgBase64'},
		{title:'图片地址',field:'imgAddress'},
		{title:'图片类型',field:'imgType'},
		{title:'图片标题',field:'imgTitle'},
		{title:'图片信息',field:'imgInfo'}
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
		$('#updateId').val(selectRow.dictId);
		//--需个性化部分
		$('#updateType').val(selectRow.dictType);
		$('#updateKey').val(selectRow.dictDkey);
		$('#updateValue').val(selectRow.dictValue);
		$('#updateText').val(selectRow.text);
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
	if( ! $('#updateType').validatebox('isValid') ) {
		$('#updateType').focus();
	}else if (! $('#updateKey').validatebox('isValid')) {
		$('#updateKey').focus();
	}else if (! $('#updateValue').validatebox('isValid')) {
		$('#updateValue').focus();
	}else if (! $('#updateText').validatebox('isValid')) {
		$('#updateText').focus();
  	}else {
 		$.ajax({
			url : urlBasePath+'/dict/insert.do',
			headers:{Accept:"application/json;charset=utf-8"},
			type : 'post',
			data : {
				dictType : $('#updateType').val(),
				dictDkey : $('#updateKey').val(),
				dictValue : $('#updateValue').val(),
				text : $('#updateText').val(),
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
	if( ! $('#updateType').validatebox('isValid') ) {
		$('#updateType').focus();
	}else if (! $('#updateKey').validatebox('isValid')) {
		$('#updateKey').focus();
	}else if (! $('#updateValue').validatebox('isValid')) {
		$('#updateValue').focus();
	}else if (! $('#updateText').validatebox('isValid')) {
		$('#updateText').focus();
  	}else {
 		$.ajax({
			url : urlBasePath+'/dict/updateByKey.do',
			headers:{Accept:"application/json;charset=utf-8"},
			type : 'post',
			data : {
				dictId : $('#updateId').val(),
				dictType : $('#updateType').val(),
				dictDkey : $('#updateKey').val(),
				dictValue : $('#updateValue').val(),
				text : $('#updateText').val(),
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
		$.messager.confirm('删除提示','确定要删除字典类型为【' + $('#showData').datagrid('getSelected').dictType + '】字典值为【' + $('#showData').datagrid('getSelected').dictValue + '】的数据吗？',function(r) {
			if(r) {
				$.ajax({
					url:urlBasePath+'/dict/deleteByKey.do',
					headers:{Accept:"application/json;charset=utf-8"},
					type : 'post',
					data : {
						id : $('#showData').datagrid('getSelected').dictId,
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