package com.example.zhiyin;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.listener.UpdateListener;


public class Student extends BmobObject{

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}