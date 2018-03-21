/**
 * jQuery EasyUI 功能扩展
 * 
 * 2016-03-28 增加validate 校验规则
 * author yanglingsx@sina.com
 */
(function($) {
	$.extend($.fn.validatebox.defaults.rules, {
		checkNum:{
			validator:function(value,param) {
				return /^[0-9]$/.test(value);
			},
			message:'只允许输入数字'
		},
		checkCN:{
			validator:function(value,param) {
				var len=$.trim(value).length;
				if(len >= param[0] && len <=param[1])
					return /^[\u4e00-\u9fa5]+$/.test(value);
				else return false;
			},
			message:'只允许输入中文字符'
		},
		checkCEN:{
			validator:function(value,param) {
				var len=$.trim(value).length;
				if(len >= param[0] && len <=param[1])
					return /^[\u4e00-\u9fa5A-Za-z0-9_]+$/.test(value);
				else return false;
			},
			message:'只允许录入中英文及数字'
		},
		checkAmt:{
			validator:function(value,param) {
				return /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/.test(value);
			},
			message:'请输入金额'
		},
		equals:{
			validator:function(value,param) {
				return value==$(param[0]).val();
			},
			message:'两次录入不一致'
		},
		simplepassword:{
			validator:function(value,param) {
				var len=$.trim(value).length;
				if( len < 8 ) return false; 
				var passCnt=0;
				if(/[A-Z]/.test(value)) passCnt++;
				if(/[a-z]/.test(value)) passCnt++;
				if(/\d/.test(value)) passCnt++;
				if(/[-\`=\\\[\];',.\/\~!\@\#\$\%\^\&\*\(\)_+\|\{\}:"\<\>\?]/.test(value)) passCnt++;
				if(passCnt>2)
					return true;
				else
					return false;
			},
			message:'简单密码校验：密码长度8位以上 , 需包含数字、字母大、小写、符号4选3',
		}
		
	});
})(jQuery);