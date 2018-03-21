package com.lding.wiqs.app.tel.domain;

/**
 * <p>
 * Title: TelList
 * </p>
 * <p>
 * Description: 通讯录
 * </p>
 * 
 * @author xixuguang
 * @date 2018年3月8日 下午2:14:19
 *
 */
public class TelList {
	/**
	 * 网络访问状态码
	 */
	private String stateCode;
	/**
	 * 主键
	 */
	private String userId;

	/**
	 * 工号
	 */
	private String jobId;

	/**
	 * 部门名称
	 */
	private String orgName;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 联系方式
	 */
	private String address;

	/**
	 * 电话
	 */
	private String phone;

	/**
	 * 头像
	 */
	private String icon;

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
	 * <p>Title: getUserId</p>  
	 * <p>Description: 获取用户编号</p>  
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * <p>Title: setUserId</p>  
	 * <p>Description: 设置用户编号</p>  
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * <p>Title: getJobId</p>  
	 * <p>Description: 获取工号</p>  
	 * @return
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * <p>Title: setJobId</p>  
	 * <p>Description: 设置工号</p>  
	 * @param jobId
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * <p>Title: getOrgName</p>  
	 * <p>Description: 获取部门名称</p>  
	 * @return
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * <p>Title: setOrgName</p>  
	 * <p>Description: 设置部门名称</p>  
	 * @param orgName
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * <p>Title: getRealName</p>  
	 * <p>Description: 获取真实姓名</p>  
	 * @return
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * <p>Title: setRealName</p>  
	 * <p>Description: 设置真实姓名</p>  
	 * @param realName
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * <p>Title: getAddress</p>  
	 * <p>Description: 获取地址</p>  
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * <p>Title: setAddress</p>  
	 * <p>Description: 设置地址</p>  
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * <p>Title: getPhone</p>  
	 * <p>Description: 获取电话</p>  
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * <p>Title: setPhone</p>  
	 * <p>Description: 设置电话</p>  
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * <p>Title: getIcon</p>  
	 * <p>Description: 获取头像</p>  
	 * @return
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * <p>Title: setIcon</p>  
	 * <p>Description: 设置头像</p>  
	 * @param icon
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

}

