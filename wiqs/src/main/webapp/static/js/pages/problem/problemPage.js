/* 启动时加载 */  
$('#movement_problemType').combobox({      
    	valueField : 'dictDkey',
	textField : 'dictValue',
	url : '${path}/dict/readByType.do?dictType=pdType'
});  
$("#goods_tab").datagrid({
    title: '货品移动填报',  
    checkOnSelect: false,  
    pagination:true,  
    pageSize:20,  
    pageNumber:1,  
    toolbar: '#tb',  
    url:'${path}/problem/selectByWhere.do',
    loadMsg:'加载中...',  
    fit: true,  
    columns : [[ 
    		{title : 'ID',field : 'problemId',checkbox : 'true'}, 
    		{field: 'problemId', title: 'ID', checkbox : 'true',  
            formatter: function(value,row,index){  
            		return '<a  href="javascript:void(0);" style="color: #000000;" onclick="updateDelFlag('+value+','+row.moveid+');">' + value + '</a>';  
            }  
        }, 
    		{title : '问题组编号',field : 'problemGroupId', width:60}, 
    		{field: 'problemType', title: '问题类型', width:60,  
            formatter: function(value,row,index){  
                if (value=='1'){  
                    return '漏气';  
                }   
                if (value=='2'){  
                    return '隐患';  
                }  
            }  
        },
    		{title : '描述',field : 'descInfo', width:60}, 
    		{title : '上报位置编号',field : 'pointId', width:60}, 
    		{title : '上报人',field : 'reportUser', width:60}, 
    		{title : '上报时间',field : 'reportTime', width:60}, 
    		{field: 'isInside', title: '是否内部处理', width:60,  
            formatter: function(value,row,index){  
                if (value=='1'){  
                    return '上报';  
                }   
                if (value=='2'){  
                    return '内报';  
                }  
            }  
        },
    		{title : '是否处理完成',field : 'isDone', width:60}, 
    		{title : '处理完成时间',field : 'doneTime', width:60}, 
    		{title : '处理人',field : 'solveUser', width:60}, 
    		{title : '处理措施', field : 'solveMode', width:60}, 
		]],     
});   
          
//        /* 选择ERP商品信息 */  
//        $("#select_erp_win").window({  
//            title:'选择ERP商品信息',  
//            width:640,      
//            height:400,      
//            modal:true     
//        }).window("close");    
//          
//        $("#select_erp_tab").datagrid({  
//            url:"/portal/queryerpgoodsAction.do",  
//            loadMsg:'加载中...',  
//            singleSelect:true,  
//            pagination:true,  
//            pageSize:10,  
//            pageNumber:1,  
//            columns:[[  
//                {field:'com_goods_id',title:'com_goods_id',width:100,checkbox:true},      
//                {field:'goods_opcode',title:'ERP商品操作码',width:100},      
//                {field:'goods_name',title:'ERP商品名称',width:100},      
//                {field:'goods_desc',title:'ERP商品规格',width:100},      
//                {field:'product_location',title:'ERP商品厂商',width:100},      
//                {field:'unit_name',title:'ERP商品单位',width:75},                 
//                {field:'package_num',title:'ERP商品包装数',width:75}       
//                  
//            ]]  
//        });  
//          
//        /* 选择商品批号 */  
//        $("#select_batchnumber_win").window({  
//            title:'选择ERP商品信息',  
//            width:480,      
//            height:400,      
//            modal:true     
//        }).window("close");    
//          
//        $("#select_batchnumber_tab").datagrid({  
//            url:"/portal/queryerpGoodsBatchNumberAction.do",  
//            loadMsg:'加载中...',  
//            singleSelect:true,  
//            pagination:true,  
//            pageSize:10,  
//            pageNumber:1,  
//            columns:[[  
//                {field:'com_lot_id',title:'com_lot_id',width:100,checkbox:true},      
//                {field:'lot_no',title:'商品批号',width:100},      
//                {field:'produce_date',title:'生效日期',width:150},                
//                {field:'expire_date',title:'到期日期',width:150}                  
//            ]]  
//        });  
//        /* 移动类型改变启用原因代码 */  
//        $('#tb_movement_code').combobox({             
//            onSelect: function(record){  
//                var value = $(this).combobox('getValue');   
//                $('#tb_reasoncode').combobox({      
//                    url:'/portal/getAllParameterAction.do?type=reason&movement_types='+value,      
//                    valueField:'reason_code',      
//                    textField:'reason_name'     
//                });  
//                $("#tb_reasoncode").combobox("enable");  
//            }  
//        });  
//  
//          
//    });  
//      
    /* 查询数据条件 */  
    function checkInputQuery(){  
        var problemType = $('#movement_problemType').datebox('getValue');  
        var reportUser = $('#movement_reportUser').datebox('getValue');  
        $('#goods_tab').datagrid('options').url=urlBasePath + '/problem/selectByWhere.do';  
        $('#goods_tab').datagrid('load',{  
	        	problemType:problemType,  
	        	reportUser:reportUser,  
        });          
    }  
//      
//    /* 修改记录状态 */  
//    function updateDelFlag(value,row){  
//        $.ajax({  
//            method : 'post',  
//            url : '/portal/updateGoodStatusAction.do',  
//            data:{   
//                type : "del",  
//                moveid : row,  
//                del_flag : value  
//            },   
//            async : false,  
//            dataType : 'json',  
//            success : function(data) {  
//                if(data){  
//                    $('#goods_tab').datagrid('reload');   
//                }else{  
//                    $.messager.alert('提示',"更改记录状态失败！");  
//                }  
//            },  
//            error : function() {  
//                $.messager.alert('异常','更改记录状态异常！');  
//            }  
//        });  
//    }  
//      
//    /* 下拉框元素填充 */  
//    function selectGood(){  
//        $('#tb_movement_code').combobox({      
//            url:'',      
//            valueField:'movement_types',      
//            textField:'summary'     
//        });  
//        $('#tb_fromstoragelocation').combobox({      
//            url:'',      
//            valueField:'freight_code',      
//            textField:'freight_info'     
//        });  
//        $('#tb_tostoragelocation').combobox({      
//            url:'',      
//            valueField:'freight_code',      
//            textField:'freight_info'     
//        });  
//        $('#tb_reasoncode').combobox({      
//            url:'',      
//            valueField:'reason_code',      
//            textField:'reason_name'     
//        });   
//        $("#frombatchnumberBtn").linkbutton("disable");  
//        $("#tb_reasoncode").combobox("disable");  
//    }  
//    var url;  
    
    /* 修改商品移动信息 */  
    function updateGood(){  
    		alert("修改");
//        selectGood();  
//        var checkedItems = $('#goods_tab').datagrid('getSelections');  
//        var moveIds = [];  
//        var num = 0;  
//        $.each(checkedItems, function(index, item){  
//            moveIds.push(item.moveid);  
//            num++;  
//        });   
//        if(num != 1){  
//            $.messager.alert('提示','请选择一条数据进行修改');  
//            return false;  
//        }  
//        var row = $("#goods_tab").datagrid("getSelected");  
//        if (row) {  
//            $("#enditTab").dialog("open").dialog('setTitle', '编辑货品移动维护');  
//            $("#fm").form("load", row);  
//            url = "/portal/addGoodMovingAction.do?type=update";  
//        }  
//        $('#tb_movement_code').combobox("select", row.movementcode);  
//        $('#tb_reasoncode').combobox("select", row.reasoncode);  
//        //修改商品时把编码和批号传入隐藏文本框（禁用后获取不到值）  
//        $('#tb_frommaterial_in').val(row.frommaterial);  
//        $('#tb_frombatchnumber_in').val(row.frombatchnumber);  
    }  
      
    /* 添加商品移动信息 */  
    function addGood(){  
    		alert("添加");
//        selectGood();  
//        $("#enditTab").dialog("open").dialog('setTitle', '添加货品移动维护');  
//        $("#fm").form("clear");   
//        url = "/portal/addGoodMovingAction.do?type=add";  
    }  
//      
//    /* 数据校验后提交 */  
//    function checkInputAdd(){  
//        var tb_postingdate = $('#tb_postingdate').datebox('getValue');  
//        if(tb_postingdate==''){  
//            $.messager.alert('提示','过帐日期不能为空');  
//            return false;  
//        }  
//        var tb_movement_code = $('#tb_movement_code').combobox('getValue');   
//        if(tb_movement_code==''){  
//            $.messager.alert('提示','移动类型不能为空');  
//            return false;  
//        }  
//        var tb_fromstoragelocation = $('#tb_fromstoragelocation').combobox('getValue');   
//        if(tb_fromstoragelocation==''){  
//            $.messager.alert('提示','来源库位不能为空');  
//            return false;  
//        }  
//        var tb_tostoragelocation = $('#tb_tostoragelocation').combobox('getValue');   
//        if(tb_tostoragelocation==''){  
//            $.messager.alert('提示','接收库位不能为空');  
//            return false;  
//        }  
//        var tb_frommaterial = $('#tb_frommaterial').val();   
//        if(tb_frommaterial==''){  
//            $.messager.alert('提示','商品编码不能为空');  
//            return false;  
//        }  
//        var tb_frombatchnumber = $('#tb_frombatchnumber').val();   
//        if(tb_frombatchnumber==''){  
//            $.messager.alert('提示','商批号不能为空');  
//            return false;  
//        }  
//        var tb_quantity = $('#tb_quantity').val();   
//        if(tb_quantity==''){  
//            $.messager.alert('提示','数量不能为空');  
//            return false;  
//        }  
//        /* var tb_reasoncode = $('#tb_reasoncode').combobox('getValue');  
//        if(tb_reasoncode==''){ 
//            $.messager.alert('提示','原因代码不能为空'); 
//            return false; 
//        } */  
//        $("#fm").form("submit", {  
//            url: url,  
//            onsubmit: function () {  
//                return $(this).form("validate");  
//            },  
//            success: function (result) {  
//                if (result == "true") {  
//                    $.messager.alert("提示信息", "操作成功");  
//                    $("#enditTab").dialog("close");  
//                    $("#goods_tab").datagrid("load");  
//                }  
//                else {  
//                    $.messager.alert("提示信息", "保存数据失败");  
//                }  
//            }  
//        });  
//    }  
//    /* 选择ERP商品 */  
//      
//    function selecterpapply(){  
//        $("#select_erp_win").window("open");  
//        $("#select_erp_tab").datagrid("load");  
//    }  
//      
//    function queryselecterp(){  
//        var apply_erp_name = $("#select_erp_name").val();  
//        var apply_erp_produce = $("#select_erp_produce").val();  
//        $("#select_erp_tab").datagrid("load",{erp_name:apply_erp_name,erp_produce:apply_erp_produce});  
//    }  
//      
//    function saveselecterp(){  
//        var select = $('#select_erp_tab').datagrid('getSelections');  
//        var goods_opcode = select[0].goods_opcode;  
//        var com_goods_id = select[0].com_goods_id;  
//        $("#tb_frommaterial").val(goods_opcode);  
//        $("#tb_frommaterial_in").val(goods_opcode);  
//        $("#com_goods_id").val(com_goods_id);  
//        $.messager.progress('close');  
//        $('#select_erp_win').window('close');  
//        /* 清空原有批号 */  
//        $("#tb_frombatchnumber").val("");     
//        $("#tb_frombatchnumber_in").val("");  
//        $("#frombatchnumberBtn").linkbutton("enable");  
//    }  
//  
//    /* 批号 */  
//    function queryFrombatchnumber(num){  
//        var com_goods_id = $("#com_goods_id").val();  
//        if(num == 1){  
//            $("#select_batchnumber_win").window("open");  
//            $("#select_batchnumber_tab").datagrid("load",{com_goods_id:com_goods_id});  
//        }  
//        if(num == 2){  
//            var link_goods_id = $("#link_goods_id").val();  
//            $("#select_batchnumber_tab").datagrid("load",{com_goods_id:com_goods_id,link_goods_id:link_goods_id});  
//        }  
//    }  
//      
//    function saveselectbatchnumber(){  
//        var select = $('#select_batchnumber_tab').datagrid('getSelections');  
//        var lot_no = select[0].lot_no;  
//        $("#tb_frombatchnumber").val(lot_no);  
//        $("#tb_frombatchnumber_in").val(lot_no);  
//        $.messager.progress('close');  
//        $('#select_batchnumber_win').window('close');  
//    }     