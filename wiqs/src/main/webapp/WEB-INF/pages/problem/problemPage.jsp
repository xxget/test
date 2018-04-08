<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/pages/include/baseEnv.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<link rel="stylesheet" type="text/css"  href="${webrs }/easyui/themes/gray/easyui.css">  
<link rel="stylesheet" type="text/css"  href="${webrs }/easyui/themes/icon.css">  
<script type="text/javascript" src="${webrs }/jquery/1.11.3/jquery.js"></script>  
<script type="text/javascript" src="${webrs }/easyui/jquery.easyui.min.js"></script> 
<title>${projname }-${mlenterprise.name }-问题管理</title>
</head>
    <body style="padding: 0;margin: 0;">  
        <table id="goods_tab" toolbar="#tb" ></table>  
        <div id="tb" style="padding:3px">
	        <div align="center" style="padding: 0; margin: 0;">
		        	<span>问题类型：</span><input  id="movement_problemType" name="problemType"  value="">
			    	<span>上报人：</span><input id="movement_reportUser" name="reportUser"  value="" >
			    	<a id="query" href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="checkInputQuery()">查询</a>
		    	</div>
		    	<hr style="height:1px;border:none;border-top:1px solid #EAEAEA;" />
		    	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:alert('Add')">添加</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:alert('Cut')">修改</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="javascript:alert('Save')">删除</a>
	    </div>
	<script type="text/javascript">
		var urlBasePath = "${path}";
	</script>
	<script type="text/javascript" src="${webrs }/js/pages/problem/comm.js"></script>
	<script type="text/javascript">
	$(function(){ 
		　　selectInfo(); 
		}); 
	function selectInfo(){
        $('#movement_problemType').combobox({      
		    	valueField : 'dictDkey',
			textField : 'dictValue',
			url : '${path}/dict/readByType.do?dictType=pdType'
        });  
        $("#goods_tab").datagrid({
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
	}
        
	/* 查询数据条件 */  
	function checkInputQuery(){  
		var problemType = $('#movement_problemType').datebox('getValue'); 
	    var reportUser = $("#movement_reportUser").val();
		if(problemType == ""){
			problemType = null;
		}
		if(reportUser == ""){
			reportUser = null;
		}
	    $('#goods_tab').datagrid('options').url= '${path}/problem/selectByWhere.do';  
	    $('#goods_tab').datagrid('load',{  
	     	problemType:problemType,  
	     	reportUser:reportUser,  
	    });   
	} 
	
	</script>
    </body>  
</html>