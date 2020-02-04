package org.greencode.modules.app.controller;

import lombok.Data;

import java.util.Date;

@Data
public class BossVo {


    /**
     * 店名称
     */
    private String shopName;
    /**
     * user名称
     */
    private String userName;
    /**
     *
     */
    private Long id;
    /**
     * 捐赠人id
     */
    private Long userId;
    /**
     * 报名分店id
     */
    private Long shopId;
    /**
     * 值班日期
     */
    private Date dutyDate;
    /**
     * 1早班2晚班
     */
    private Integer dutyType;
    /**
     * 0为false,1为true审核通过(默认自动
     */
    private Integer dutyStatus;
    /**
     *
     */
    private Date dutySubmitTime;
    /**
     *
     */
    private String operator;
    /**
     *
     */
    private Date operationTime;
    /**
     *
     */
    private String operatorIp;
    /**
     *
     */
    private String memo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShopName() {
        return shopName;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public Date getDutyDate() {
        return dutyDate;
    }

    public Integer getDutyType() {
        return dutyType;
    }

    public Integer getDutyStatus() {
        return dutyStatus;
    }

    public Date getDutySubmitTime() {
        return dutySubmitTime;
    }

    public String getOperator() {
        return operator;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public String getOperatorIp() {
        return operatorIp;
    }

    public String getMemo() {
        return memo;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public void setDutyDate(Date dutyDate) {
        this.dutyDate = dutyDate;
    }

    public void setDutyType(Integer dutyType) {
        this.dutyType = dutyType;
    }

    public void setDutyStatus(Integer dutyStatus) {
        this.dutyStatus = dutyStatus;
    }

    public void setDutySubmitTime(Date dutySubmitTime) {
        this.dutySubmitTime = dutySubmitTime;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
