public class UserInfo {
    private String username;
    private String password;
    private String tel;
    private String name;
    private String address;
    private String idCardNum;
    private String pushAddress;
    private Integer autoStopPushMinutes;

    public UserInfo(){}

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setTel(String tel){
        this.tel = tel;
    }

    public String getTel(){
        return tel;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setIdCardNum(String idCardNum){
        this.idCardNum = idCardNum;
    }

    public String getIdCardNum(){
        return idCardNum;
    }

    public void setPushAddress(String pushAddress){
        this.pushAddress = pushAddress;
    }

    public String getPushAddress(){
        return pushAddress;
    }

    public void setAutoStopPushMinutes(Integer autoStopPushMinutes){
        this.autoStopPushMinutes = autoStopPushMinutes;
    }

    public Integer getAutoStopPushMinutes(){
        return autoStopPushMinutes;
    }

    @Override
    public String toString(){
        return "UserInfo [username:" + username + ", password:" + password + ", tel:" + tel
                + ", name:" + name + ", address:" + address + ", idCardNum:" + idCardNum
                + ", pushAddress:" + pushAddress + ", autoStopPushMinutes:" + autoStopPushMinutes;
    }
}
