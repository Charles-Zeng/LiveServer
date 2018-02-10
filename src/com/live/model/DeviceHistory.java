package com.live.model;

import java.util.Date;

public class DeviceHistory {
    private String username;
    private String ip;
    private String mac;
    private String imei;
    private String gps;
    private String serviceName;
    private Date loginTime;
    private Date logoutTime;
    private long durationTime; //seconds

    public DeviceHistory() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getMac() {
        return mac;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImei() {
        return imei;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getGps() {
        return gps;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setDurationTime(long durationTime) {
        this.durationTime = durationTime;
    }

    public long getDurationTime() {
        return durationTime;
    }
}
