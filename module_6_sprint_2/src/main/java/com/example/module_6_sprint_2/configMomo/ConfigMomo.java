package com.example.module_6_sprint_2.configMomo;

public class ConfigMomo {
    private String orderInfo;
    private long amount;
    private String partnerName;
    private String requestType;
    private String redirectUrl;
    private String ipnUrl;
    private String storeId;
    private String extraData;
    private String signature;
    private String partnerCode;
    private String requestId;
    private String orderId;
    private String lang;
    private int startTime;

    public ConfigMomo() {
    }

    public ConfigMomo(String orderInfo, long amount, String partnerName, String requestType, String redirectUrl, String ipnUrl, String storeId, String extraData, String signature, String partnerCode, String requestId, String orderId, String lang, int startTime) {
        this.orderInfo = orderInfo;
        this.amount = amount;
        this.partnerName = partnerName;
        this.requestType = requestType;
        this.redirectUrl = redirectUrl;
        this.ipnUrl = ipnUrl;
        this.storeId = storeId;
        this.extraData = extraData;
        this.signature = signature;
        this.partnerCode = partnerCode;
        this.requestId = requestId;
        this.orderId = orderId;
        this.lang = lang;
        this.startTime = startTime;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getIpnUrl() {
        return ipnUrl;
    }

    public void setIpnUrl(String ipnUrl) {
        this.ipnUrl = ipnUrl;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
}
