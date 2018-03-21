package com.lding.wiqs.app.dict.domain;

import com.lding.wiqs.modular.dict.domain.Dict;

/**
 * <p>Title: AppDict</p>  
 * <p>Description: App用的数据字典</p>  
 * @author xixuguang 
 * @date 2018年3月8日 下午2:53:56
 *
 */
public class AppDict {

	/**
	 * 网络访问状态码
	 */
	private String stateCode;
	/**
	 * 数据字典
	 */
	private Dict dict;
	/**
	 * <p>Title: getStateCode</p>  
	 * <p>Description: 获取网络访问状态码</p>  
	 * @return
	 */
	public String getStateCode() {
		return stateCode;
	}
	/**
	 * <p>Title: setStateCode</p>  
	 * <p>Description: 设置网络访问状态码</p>  
	 * @param stateCode
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	/**
	 * <p>Title: getDict</p>  
	 * <p>Description: 获取数据字典</p>  
	 * @return
	 */
	public Dict getDict() {
		return dict;
	}
	/**
	 * <p>Title: setDict</p>  
	 * <p>Description: 设置数据字典</p>  
	 * @param dict
	 */
	public void setDict(Dict dict) {
		this.dict = dict;
	}
	
}
