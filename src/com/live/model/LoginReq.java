package com.live.model;

public class LoginReq {
    private String RequestType;
    private String name;
    private String pwd;

    public void setRequestType(String requestType){
        this.RequestType = requestType;
    }

    public String getRequestType(){
        return RequestType;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPwd(String pwd){
        this.pwd = pwd;
    }

    public String getPwd(){
        return pwd;
    }
}
