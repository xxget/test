package com.lding.wiqs.modular.pointinfo.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;

import com.lding.wiqs.modular.imggroup.domain.ImgGroup;

/**
 * <p>
 * Title: PointAllInfo
 * </p>
 * <p>
 * Description: 设施点及相关图片详细信息
 * </p>
 * 
 * @author xixuguang
 * @date 2018年3月9日 上午8:30:36
 *
 */
public class PointAllInfo {

	/**
	 * 设施对于的图片信息
	 */
	private ImgGroup imgGroup;
	/**
	 * 位置点编号
	 */
	@Id
	private String pointId;

	/**
	 * 点类型（1:违建；2:凝水缸；3:阀门井；4:调压站；）
	 */
	private String pointType;

	/**
	 * 经度
	 */
	private BigDecimal lng;

	/**
	 * 纬度
	 */
	private BigDecimal lat;

	/**
	 * 经度改
	 */
	private String lngs;

	/**
	 * 纬度改
	 */
	private String lats;

	/**
	 * 设施归属单位
	 */
	private String unitName;

	/**
	 * 归属单位负责人
	 */
	private String headName;

	/**
	 * 负责人电话
	 */
	private String headPhone;

	/**
	 * 图片组编号
	 */
	private String imgGroupId;

	/**
	 * 提交人编号
	 */
	private String userId;

	/**
	 * 保存时间
	 */
	private Long saveTime;

	/**
	 * 更新时间
	 */
	private Long updateTime;

	/**
	 * 更新人编号
	 */
	private String updateUserId;

	/**
	 * 备注信息
	 */
	private String note;

	/**
	 * 所属巡检区域编号
	 */
	private String areaId;

	/**
	 * 设施状态（1、启用；0:停用）
	 */
	private String pointState;

	/**
	 * 位置点信息
	 */
	private String pointInfo;
	 /**
     * 设施点名称
     */
    private String pointName;

	/**
	 * 获取位置点编号
	 *
	 * @return point_id - 位置点编号
	 */
	public String getPointId() {
		return pointId;
	}

	/**
	 * 设置位置点编号
	 *
	 * @param pointId
	 *            位置点编号
	 */
	public void setPointId(String pointId) {
		this.pointId = pointId;
	}

	/**
	 * 获取点类型（1:违建；2:凝水缸；3:阀门井；4:调压站；）
	 *
	 * @return point_type - 点类型（1:违建；2:凝水缸；3:阀门井；4:调压站；）
	 */
	public String getPointType() {
		return pointType;
	}

	/**
	 * 设置点类型（1:违建；2:凝水缸；3:阀门井；4:调压站；）
	 *
	 * @param pointType
	 *            点类型（1:违建；2:凝水缸；3:阀门井；4:调压站；）
	 */
	public void setPointType(String pointType) {
		this.pointType = pointType;
	}

	/**
	 * 获取经度
	 *
	 * @return lng - 经度
	 */
	public BigDecimal getLng() {
		return lng;
	}

	/**
	 * 设置经度
	 *
	 * @param lng
	 *            经度
	 */
	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	/**
	 * 获取纬度
	 *
	 * @return lat - 纬度
	 */
	public BigDecimal getLat() {
		return lat;
	}

	/**
	 * 设置纬度
	 *
	 * @param lat
	 *            纬度
	 */
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	/**
	 * 获取经度改
	 *
	 * @return lngs - 经度改
	 */
	public String getLngs() {
		return lngs;
	}

	/**
	 * 设置经度改
	 *
	 * @param lngs
	 *            经度改
	 */
	public void setLngs(String lngs) {
		this.lngs = lngs;
	}

	/**
	 * 获取纬度改
	 *
	 * @return lats - 纬度改
	 */
	public String getLats() {
		return lats;
	}

	/**
	 * 设置纬度改
	 *
	 * @param lats
	 *            纬度改
	 */
	public void setLats(String lats) {
		this.lats = lats;
	}

	/**
	 * 获取设施归属单位
	 *
	 * @return unit_name - 设施归属单位
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 设置设施归属单位
	 *
	 * @param unitName
	 *            设施归属单位
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 获取归属单位负责人
	 *
	 * @return head_name - 归属单位负责人
	 */
	public String getHeadName() {
		return headName;
	}

	/**
	 * 设置归属单位负责人
	 *
	 * @param headName
	 *            归属单位负责人
	 */
	public void setHeadName(String headName) {
		this.headName = headName;
	}

	/**
	 * 获取负责人电话
	 *
	 * @return head_phone - 负责人电话
	 */
	public String getHeadPhone() {
		return headPhone;
	}

	/**
	 * 设置负责人电话
	 *
	 * @param headPhone
	 *            负责人电话
	 */
	public void setHeadPhone(String headPhone) {
		this.headPhone = headPhone;
	}

	/**
	 * 获取图片组编号
	 *
	 * @return img_group_id - 图片组编号
	 */
	public String getImgGroupId() {
		return imgGroupId;
	}

	/**
	 * 设置图片组编号
	 *
	 * @param imgGroupId
	 *            图片组编号
	 */
	public void setImgGroupId(String imgGroupId) {
		this.imgGroupId = imgGroupId;
	}

	/**
	 * 获取提交人编号
	 *
	 * @return user_id - 提交人编号
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 设置提交人编号
	 *
	 * @param userId
	 *            提交人编号
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 获取保存时间
	 *
	 * @return save_time - 保存时间
	 */
	public Long getSaveTime() {
		return saveTime;
	}

	/**
	 * 设置保存时间
	 *
	 * @param saveTime
	 *            保存时间
	 */
	public void setSaveTime(Long saveTime) {
		this.saveTime = saveTime;
	}

	/**
	 * 获取更新时间
	 *
	 * @return update_time - 更新时间
	 */
	public Long getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 *
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取更新人编号
	 *
	 * @return update_user_id - 更新人编号
	 */
	public String getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 设置更新人编号
	 *
	 * @param updateUserId
	 *            更新人编号
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * 获取备注信息
	 *
	 * @return note - 备注信息
	 */
	public String getNote() {
		return note;
	}

	/**
	 * 设置备注信息
	 *
	 * @param note
	 *            备注信息
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * 获取所属巡检区域编号
	 *
	 * @return area_id - 所属巡检区域编号
	 */
	public String getAreaId() {
		return areaId;
	}

	/**
	 * 设置所属巡检区域编号
	 *
	 * @param areaId
	 *            所属巡检区域编号
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	/**
	 * 获取设施状态（1、启用；0:停用）
	 *
	 * @return point_state - 设施状态（1、启用；0:停用）
	 */
	public String getPointState() {
		return pointState;
	}

	/**
	 * 设置设施状态（1、启用；0:停用）
	 *
	 * @param pointState
	 *            设施状态（1、启用；0:停用）
	 */
	public void setPointState(String pointState) {
		this.pointState = pointState;
	}

	/**
	 * 获取位置点信息
	 *
	 * @return point_info - 位置点信息
	 */
	public String getPointInfo() {
		return pointInfo;
	}

	/**
	 * 设置位置点信息
	 *
	 * @param pointInfo
	 *            位置点信息
	 */
	public void setPointInfo(String pointInfo) {
		this.pointInfo = pointInfo;
	}

	/**
	 * <p>
	 * Title: getImgGroup
	 * </p>
	 * <p>
	 * Description: 获取设施对于的图片信息
	 * </p>
	 * 
	 * @return
	 */
	public ImgGroup getImgGroup() {
		return imgGroup;
	}

	/**
	 * <p>
	 * Title: setImgGroup
	 * </p>
	 * <p>
	 * Description: 设置设施对于的图片信息
	 * </p>
	 * 
	 * @param imgGroup
	 */
	public void setImgGroup(ImgGroup imgGroup) {
		this.imgGroup = imgGroup;
	}

	/**
	 * <p>Title: getPointName</p>  
	 * <p>Description: 获取设施点名称</p>  
	 * @return
	 */
	public String getPointName() {
		return pointName;
	}

	/**
	 * <p>Title: setPointName</p>  
	 * <p>Description: 设置设施点名称</p>  
	 * @param pointName
	 */
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

}
