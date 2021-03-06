package com.live.model;

import java.util.Date;

public class Device {
    private String ip;
    private String mac;
    private String imei;
    private String gps;
    private String serviceName;
    private String username;
    private Integer status;
    private String pushAddr;
    private Date loginTime;

    public Device() {}

    public void setIp(String ip){
        this.ip = ip;
    }

    public String getIp(){
        return ip;
    }

    public void setMac(String mac){
        this.mac = mac;
    }

    public String getMac(){
        return mac;
    }

    public void setImei(String imei){
        this.imei = imei;
    }

    public String getImei(){
        return imei;
    }

    public void setGps(String gps){
        this.gps = gps;
    }

    public String getGps(){
        return gps;
    }

    public void setServiceName(String serviceName){
        this.serviceName = serviceName;
    }

    public String getServiceName(){
        return serviceName;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Integer getStatus(){
        return status;
    }

    public void setPushAddr(String pushAddr) {
        this.pushAddr = pushAddr;
    }

    public String getPushAddr() {
        return pushAddr;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    @Override
    public String toString(){
        return "Device{ip:" + ip + "mac:" + mac + "imei:" + imei
                + "gps:" + gps + "serviceName:" + serviceName
                + "username:" + username + "status:" + status
                + "pushAddr:" + pushAddr + "}";
    }
}
