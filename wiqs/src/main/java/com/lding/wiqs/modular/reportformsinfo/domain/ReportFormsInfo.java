package com.lding.wiqs.modular.reportformsinfo.domain;

import javax.persistence.*;

@Table(name = "p_report_forms_info")
public class ReportFormsInfo {
    /**
     * 报表编号
     */
    @Id
    @Column(name = "rf_id")
    private String rfId;

    /**
     * 报表名称(机构+日期+报表)
     */
    @Column(name = "rf_name")
    private String rfName;

    /**
     * 问题组编号
     */
    @Column(name = "problem_group_id")
    private String problemGroupId;

    /**
     * 制作人
     */
    @Column(name = "make_id")
    private String makeId;

    /**
     * 制作时间
     */
    @Column(name = "make_time")
    private String makeTime;

    /**
     * 审核
     */
    @Column(name = "examine_user_id")
    private String examineUserId;

    /**
     * 审核时间
     */
    @Column(name = "examine_time")
    private String examineTime;

    /**
     * 获取报表编号
     *
     * @return rf_id - 报表编号
     */
    public String getRfId() {
        return rfId;
    }

    /**
     * 设置报表编号
     *
     * @param rfId 报表编号
     */
    public void setRfId(String rfId) {
        this.rfId = rfId;
    }

    /**
     * 获取报表名称(机构+日期+报表)
     *
     * @return rf_name - 报表名称(机构+日期+报表)
     */
    public String getRfName() {
        return rfName;
    }

    /**
     * 设置报表名称(机构+日期+报表)
     *
     * @param rfName 报表名称(机构+日期+报表)
     */
    public void setRfName(String rfName) {
        this.rfName = rfName;
    }

    /**
     * 获取问题组编号
     *
     * @return problem_group_id - 问题组编号
     */
    public String getProblemGroupId() {
        return problemGroupId;
    }

    /**
     * 设置问题组编号
     *
     * @param problemGroupId 问题组编号
     */
    public void setProblemGroupId(String problemGroupId) {
        this.problemGroupId = problemGroupId;
    }

    /**
     * 获取制作人
     *
     * @return make_id - 制作人
     */
    public String getMakeId() {
        return makeId;
    }

    /**
     * 设置制作人
     *
     * @param makeId 制作人
     */
    public void setMakeId(String makeId) {
        this.makeId = makeId;
    }

    /**
     * 获取制作时间
     *
     * @return make_time - 制作时间
     */
    public String getMakeTime() {
        return makeTime;
    }

    /**
     * 设置制作时间
     *
     * @param makeTime 制作时间
     */
    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime;
    }

    /**
     * 获取审核
     *
     * @return examine_user_id - 审核
     */
    public String getExamineUserId() {
        return examineUserId;
    }

    /**
     * 设置审核
     *
     * @param examineUserId 审核
     */
    public void setExamineUserId(String examineUserId) {
        this.examineUserId = examineUserId;
    }

    /**
     * 获取审核时间
     *
     * @return examine_time - 审核时间
     */
    public String getExamineTime() {
        return examineTime;
    }

    /**
     * 设置审核时间
     *
     * @param examineTime 审核时间
     */
    public void setExamineTime(String examineTime) {
        this.examineTime = examineTime;
    }
}