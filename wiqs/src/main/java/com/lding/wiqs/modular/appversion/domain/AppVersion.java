package com.lding.wiqs.modular.appversion.domain;

import javax.persistence.*;

@Table(name = "c_app_version")
public class AppVersion {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 版本号
     */
    @Column(name = "version_code")
    private String versionCode;

    /**
     * 版本信息
     */
    @Column(name = "version_info")
    private String versionInfo;

    /**
     * 发布版本人
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 发布版本日期
     */
    @Column(name = "version_data")
    private String versionData;

    /**
     * 版本状态（1:最新，2:历史）
     */
    @Column(name = "versioin_state")
    private String versioinState;

    /**
     * app路径
     */
    @Column(name = "app_url")
    private String appUrl;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取版本号
     *
     * @return version_code - 版本号
     */
    public String getVersionCode() {
        return versionCode;
    }

    /**
     * 设置版本号
     *
     * @param versionCode 版本号
     */
    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    /**
     * 获取版本信息
     *
     * @return version_info - 版本信息
     */
    public String getVersionInfo() {
        return versionInfo;
    }

    /**
     * 设置版本信息
     *
     * @param versionInfo 版本信息
     */
    public void setVersionInfo(String versionInfo) {
        this.versionInfo = versionInfo;
    }

    /**
     * 获取发布版本人
     *
     * @return user_id - 发布版本人
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置发布版本人
     *
     * @param userId 发布版本人
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取发布版本日期
     *
     * @return version_data - 发布版本日期
     */
    public String getVersionData() {
        return versionData;
    }

    /**
     * 设置发布版本日期
     *
     * @param versionData 发布版本日期
     */
    public void setVersionData(String versionData) {
        this.versionData = versionData;
    }

    /**
     * 获取版本状态（1:最新，2:历史）
     *
     * @return versioin_state - 版本状态（1:最新，2:历史）
     */
    public String getVersioinState() {
        return versioinState;
    }

    /**
     * 设置版本状态（1:最新，2:历史）
     *
     * @param versioinState 版本状态（1:最新，2:历史）
     */
    public void setVersioinState(String versioinState) {
        this.versioinState = versioinState;
    }

    /**
     * 获取app路径
     *
     * @return app_url - app路径
     */
    public String getAppUrl() {
        return appUrl;
    }

    /**
     * 设置app路径
     *
     * @param appUrl app路径
     */
    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }
}