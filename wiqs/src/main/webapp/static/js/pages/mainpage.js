
//实例化树形菜单
$('#nav').tree({
	url	: 'readMenuTree.do',
	lines	: true,
	onClick	: function (node) {
		if(node.attributes) {
//			if( $('#tabs').tabs('exists', node.text) ) {
//				$('#tabs').tabs('select', node.text);
//			}else{
//				$('#tabs').tabs('add', {
//					title : node.text,
//					closable : true,
//					iconCls : node.icon,
//					content : '<iframe scrolling="no" frameborder="0" src="'+  +urlpath + node.attributes + '" style="width:100%;height:100%;">',
//				});
//			}
			var treeUrl = urlpath + node.attributes;
			Open(node.text, treeUrl, node.icon);
		}
	}
});

//在右边center区域打开菜单，新增tab  
function Open(text, treeUrl, icon) { 
    if ($("#tabs").tabs('exists', text)) {  
        $('#tabs').tabs('select', text);  
    } else {  
        $('#tabs').tabs('add', {  
            title : text,  
            closable : true, 
            iconCls : icon,
            content : '<iframe scrolling="no" frameborder="0" src=' + treeUrl + ' style="width:100%;height:100%;">', 
        });  
    }  
} 

function changePassword() {
	$('#passwordform').dialog('open');
	$('#passf').form('reset');
}

function cancelPass() {
	$('#passwordform').dialog('close');
}

function savePassword(){
	if (! $('#oldpassword').validatebox('isValid')) {
		$('#oldpassword').focus();
	}else if ( $('#password').val() == $('#oldpassword').val() ) {
		
		showTips("新旧密码相同！");
		$('#password').focus();
	}else if ( ! $('#password').validatebox('isValid') ) {
		$('#password').focus();
	}else if ( ! $('#password_confirmation').validatebox('isValid') ) {
		$('#password_confirmation').focus();
	}else {
		$.ajax({
			url	: urlpath +'/user/updatePassword.do',
			type	: 'post',
			headers:{Accept:"application/json;charset=utf-8"},
			data	: {
				password : $('#oldpassword').val(),
				newpassword : $('#password').val(),
			},
			beforeSend	: function () {
				$.messager.progress({
					test	: '系统处理中，请等待......',
				});
			},
			success	: function (result) {
				$.messager.progress('close');
				showTips(result.respmesg);
				if (result.respcode==0) {
    				$('#passwordform').dialog('close');
				}
			},
		});//ajax	    		
	}
}

