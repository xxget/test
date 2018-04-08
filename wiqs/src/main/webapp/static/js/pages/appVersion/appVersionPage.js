/**
 * APP版本信息js脚本
 */
// 显示列表信息
$('#showData').datagrid({
	url	: urlBasePath+'/appversion/selectByWhere.do',
	toolbar : '#toolsbar',
	idField:'id',
	columns:[[
		{title:'ID',field:'id',checkbox:'true'},
		{title:'版本号',field:'versionCode'},
		{title:'版本信息',field:'versionInfo'},
		{title:'发布人',field:'userId'},
		{title:'版本日期',field:'versionData'},
		{title:'版本状态',field:'versioinState'},
		{title:'下载路径',field:'appUrl'},
	]],
	singleSelect:true,
	pagination:true,
	striped:'true',
	loadMsg:'正在加载数据',
});

// 列数据格式化
function formatPrice(val,row){
	if (val == "0"){
		return '<span style="color:red;">历史</span>';
	} else if(val == "1") {
		return '<span style="color:red;">最新</span>';
	} else{
		return val;
	}
}

$('#showData').datagrid('getPager').pagination({
    pageSize:10,
    pageList:[10,20,30],
    beforePageText:'第',
    afterPageText:'页       共{pages}页',
    displayMsg:'当前显示{from}-{to}条记录      共{total}条记录',
   
});

// 条件查询
// --个性化条件部分
function searchObject(){
	$('#showData').datagrid('load',{
		type:$("#searchType").val(),
	});
}

// --打开新增窗口，个性化title部分
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

// --打开修改窗口，个性化数据获取部分，title
function openEditForm(){
	if($('#showData').datagrid('getSelected')){
		var selectRow = $('#showData').datagrid('getSelected');
		$('#updateId').val(selectRow.id);
		// --需个性化部分
		$('#updateVersionCode').val(selectRow.versionCode);
		$('#updateVersionInfo').val(selectRow.versionInfo);
		$('#updateAppUrl').val(selectRow.appUrl);
		// --结束
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

// --保存
// 需个性化 1.数据校验 2.url 3.提交数据集data域
function createObject() {
	if( ! $('#updateVersionCode').validatebox('isValid') ) {
		$('#updateVersionCode').focus();
	}else if (! $('#updateVersionInfo').validatebox('isValid')) {
		$('#updateVersionInfo').focus();
	}else if (! $('#updateAppUrl').validatebox('isValid')) {
		$('#updateAppUrl').focus();
  	}else {
 		$.ajax({
			url : urlBasePath+'/appversion/insert.do',
			headers:{Accept:"application/json;charset=utf-8"},
			type : 'post',
			data : {
				versionCode : $('#updateVersionCode').val(),
				versionInfo : $('#updateVersionInfo').val(),
				appUrl : $('#updateAppUrl').val(),
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
		});// ajax
	}
}

// --保存修改
// 需个性化 1.数据校验 2.url 3.提交数据集data域
function updateObject() {
	if( ! $('#updateVersionCode').validatebox('isValid') ) {
		$('#updateVersionCode').focus();
	}else if (! $('#updateVersionInfo').validatebox('isValid')) {
		$('#updateVersionInfo').focus();
	}else if (! $('#updateAppUrl').validatebox('isValid')) {
		$('#updateAppUrl').focus();
  	}else {
 		$.ajax({
			url : urlBasePath+'/appversion/updateByKey.do',
			headers:{Accept:"application/json;charset=utf-8"},
			type : 'post',
			data : {
				id : $('#id').val(),
				versionCode : $('#updateVersionCode').val(),
				versionInfo : $('#updateVersionInfo').val(),
				appUrl : $('#updateAppUrl').val(),
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
		});// ajax
	}
}

// --只需个性化 1.提示部分 2.ajax 的 url !
// $('#showData').datagrid('getSelected').xxx
function deleteObject() {
	if($('#showData').datagrid('getSelected')){
		$.messager.confirm('删除提示','确定要删除版本为【' + $('#showData').datagrid('getSelected').versionCode + '】的APP版本数据吗？',function(r) {
			if(r) {
				$.ajax({
					url:urlBasePath+'/appversion/deleteByKey.do',
					headers:{Accept:"application/json;charset=utf-8"},
					type : 'post',
					data : {
						id : $('#showData').datagrid('getSelected').id,
					},
					beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},
					success	: function (result) {
						$.messager.progress('close');
						showTips(result.respmesg);
						if (result.respcode==0) {
							$('#showData').datagrid('reload');
						}
					},
				});// ajax delete
			}
		});
	}else{
		$.messager.alert({title:'提示',msg:'请先选择要操作的对象',icon:'error',});	
	}
}