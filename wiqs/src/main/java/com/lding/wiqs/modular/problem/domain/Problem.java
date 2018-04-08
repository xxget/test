package com.lding.wiqs.modular.problem.domain;

import javax.persistence.*;

@Table(name = "p_problem")
public class Problem {
    /**
     * 问题编号
     */
    @Id
    @Column(name = "problem_id")
    private String problemId;

    /**
     * 问题组编号
     */
    @Column(name = "problem_group_id")
    private String problemGroupId;

    /**
     * 问题类型
     */
    @Column(name = "problem_type")
    private String problemType;

    /**
     * 上报位置编号
     */
    @Column(name = "point_id")
    private String pointId;

    /**
     * 上报时间
     */
    @Column(name = "report_time")
    private String reportTime;

    /**
     * 上报人
     */
    @Column(name = "report_user")
    private String reportUser;

    /**
     * 是否内部处理
     */
    @Column(name = "is_inside")
    private String isInside;

    /**
     * 是否处理完成
     */
    @Column(name = "is_done")
    private String isDone;

    /**
     * 处理完成时间
     */
    @Column(name = "done_time")
    private String doneTime;

    /**
     * 处理人
     */
    @Column(name = "solve_user")
    private String solveUser;

    /**
     * 描述
     */
    @Column(name = "desc_info")
    private String descInfo;

    /**
     * 处理措施
     */
    @Column(name = "solve_mode")
    private String solveMode;

    /**
     * 获取问题编号
     *
     * @return problem_id - 问题编号
     */
    public String getProblemId() {
        return problemId;
    }

    /**
     * 设置问题编号
     *
     * @param problemId 问题编号
     */
    public void setProblemId(String problemId) {
        this.problemId = problemId;
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
     * 获取问题类型
     *
     * @return problem_type - 问题类型
     */
    public String getProblemType() {
        return problemType;
    }

    /**
     * 设置问题类型
     *
     * @param problemType 问题类型
     */
    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    /**
     * 获取上报位置编号
     *
     * @return point_id - 上报位置编号
     */
    public String getPointId() {
        return pointId;
    }

    /**
     * 设置上报位置编号
     *
     * @param pointId 上报位置编号
     */
    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    /**
     * 获取上报时间
     *
     * @return report_time - 上报时间
     */
    public String getReportTime() {
        return reportTime;
    }

    /**
     * 设置上报时间
     *
     * @param reportTime 上报时间
     */
    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    /**
     * 获取上报人
     *
     * @return report_user - 上报人
     */
    public String getReportUser() {
        return reportUser;
    }

    /**
     * 设置上报人
     *
     * @param reportUser 上报人
     */
    public void setReportUser(String reportUser) {
        this.reportUser = reportUser;
    }

    /**
     * 获取是否内部处理
     *
     * @return is_inside - 是否内部处理
     */
    public String getIsInside() {
        return isInside;
    }

    /**
     * 设置是否内部处理
     *
     * @param isInside 是否内部处理
     */
    public void setIsInside(String isInside) {
        this.isInside = isInside;
    }

    /**
     * 获取是否处理完成
     *
     * @return is_done - 是否处理完成
     */
    public String getIsDone() {
        return isDone;
    }

    /**
     * 设置是否处理完成
     *
     * @param isDone 是否处理完成
     */
    public void setIsDone(String isDone) {
        this.isDone = isDone;
    }

    /**
     * 获取处理完成时间
     *
     * @return done_time - 处理完成时间
     */
    public String getDoneTime() {
        return doneTime;
    }

    /**
     * 设置处理完成时间
     *
     * @param doneTime 处理完成时间
     */
    public void setDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }

    /**
     * 获取处理人
     *
     * @return solve_user - 处理人
     */
    public String getSolveUser() {
        return solveUser;
    }

    /**
     * 设置处理人
     *
     * @param solveUser 处理人
     */
    public void setSolveUser(String solveUser) {
        this.solveUser = solveUser;
    }

    /**
     * 获取描述
     *
     * @return desc_info - 描述
     */
    public String getDescInfo() {
        return descInfo;
    }

    /**
     * 设置描述
     *
     * @param descInfo 描述
     */
    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo;
    }

    /**
     * 获取处理措施
     *
     * @return solve_mode - 处理措施
     */
    public String getSolveMode() {
        return solveMode;
    }

    /**
     * 设置处理措施
     *
     * @param solveMode 处理措施
     */
    public void setSolveMode(String solveMode) {
        this.solveMode = solveMode;
    }
}