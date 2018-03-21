package com.lding.wiqs.modular.imggroup.domain;

import javax.persistence.*;

@Table(name = "r_img_group")
public class ImgGroup {
    /**
     * 图片编号
     */
    @Id
    @Column(name = "img_id")
    private String imgId;

    /**
     * 图片组编号
     */
    @Column(name = "img_group_id")
    private String imgGroupId;

    /**
     * 图片类型
     */
    @Column(name = "img_type")
    private String imgType;

    /**
     * 图片标题
     */
    @Column(name = "img_title")
    private String imgTitle;

    /**
     * 图片信息
     */
    @Column(name = "img_info")
    private String imgInfo;

    /**
     * 设施点编号
     */
    @Column(name = "point_id")
    private String pointId;

    /**
     * 图片base64
     */
    @Column(name = "img_base64")
    private String imgBase64;

    /**
     * 图片地址
     */
    @Column(name = "img_address")
    private String imgAddress;

    /**
     * 获取图片编号
     *
     * @return img_id - 图片编号
     */
    public String getImgId() {
        return imgId;
    }

    /**
     * 设置图片编号
     *
     * @param imgId 图片编号
     */
    public void setImgId(String imgId) {
        this.imgId = imgId;
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
     * @param imgGroupId 图片组编号
     */
    public void setImgGroupId(String imgGroupId) {
        this.imgGroupId = imgGroupId;
    }

    /**
     * 获取图片类型
     *
     * @return img_type - 图片类型
     */
    public String getImgType() {
        return imgType;
    }

    /**
     * 设置图片类型
     *
     * @param imgType 图片类型
     */
    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    /**
     * 获取图片标题
     *
     * @return img_title - 图片标题
     */
    public String getImgTitle() {
        return imgTitle;
    }

    /**
     * 设置图片标题
     *
     * @param imgTitle 图片标题
     */
    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    /**
     * 获取图片信息
     *
     * @return img_info - 图片信息
     */
    public String getImgInfo() {
        return imgInfo;
    }

    /**
     * 设置图片信息
     *
     * @param imgInfo 图片信息
     */
    public void setImgInfo(String imgInfo) {
        this.imgInfo = imgInfo;
    }

    /**
     * 获取设施点编号
     *
     * @return point_id - 设施点编号
     */
    public String getPointId() {
        return pointId;
    }

    /**
     * 设置设施点编号
     *
     * @param pointId 设施点编号
     */
    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    /**
     * 获取图片base64
     *
     * @return img_base64 - 图片base64
     */
    public String getImgBase64() {
        return imgBase64;
    }

    /**
     * 设置图片base64
     *
     * @param imgBase64 图片base64
     */
    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }

    /**
     * 获取图片地址
     *
     * @return img_address - 图片地址
     */
    public String getImgAddress() {
        return imgAddress;
    }

    /**
     * 设置图片地址
     *
     * @param imgAddress 图片地址
     */
    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }
}