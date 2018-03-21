package com.lding.wiqs.general.web.vo;

import java.util.Map;

public class ResponseVO {
	/**
	 * 返回码
	 */
	private int respcode;
	/**
	 * 返回信息
	 */
	private String respmesg;
	private Object data = null;
	/**
	 * 其他参数
	 */
	private Map<String, Object> attributes;// 其他参数
	
	public ResponseVO() {
		respcode=9;
		respmesg="未知错误";
	}
	public ResponseVO(int respcode) {
		this.respcode=respcode;
		if(respcode==0)
			respmesg="处理成功";
		else
			respmesg="其他错误";
	}

	/**
	 * <p>Title: getRespcode</p>  
	 * <p>Description: 获取返回码</p>  
	 * @return
	 */
	public int getRespcode() {
		return respcode;
	}
	
	/**
	 * <p>Title: setRespcode</p>  
	 * <p>Description: 设置返回码</p>  
	 * @param respcode
	 */
	public void setRespcode(int respcode) {
		this.respcode = respcode;
		if(respcode==0)
			respmesg="处理成功";
		else if(respcode==1) 
			respmesg="关联信息不存在！";
		else if(respcode==2) 
			respmesg="逻辑错误";
		else if(respcode==3) 
			respmesg="数据已存在";
		else if(respcode==8) 
			respmesg="数据库操作错";
		else
			respmesg="其他错误";
	}
	/**
	 * <p>Title: getRespmesg</p>  
	 * <p>Description: 获取返回信息</p>  
	 * @return
	 */
	public String getRespmesg() {
		return respmesg;
	}
	/**
	 * <p>Title: setRespmesg</p>  
	 * <p>Description: 设置返回信息</p>  
	 * @param respmesg
	 */
	public void setRespmesg(String respmesg) {
		this.respmesg = respmesg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	/**
	 * <p>Title: getAttributes</p>  
	 * <p>Description: 获取其他参数</p>  
	 * @return
	 */
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	/**
	 * <p>Title: setAttributes</p>  
	 * <p>Description: 设置其他参数</p>  
	 * @param attributes
	 */
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	
	
	
}
