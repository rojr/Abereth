package com.gmail.robmadeyou.DataBase;

import java.io.Serializable;

/**
 * Created by Mrgadgetz
 * Date: 8/30/13
 * Time: 6:04 PM
 */
public class PlayerData implements Serializable {
    public int getId() {
        return id;
    }

    private int id;
    private String name;
    private long mobile;
    private String email;

    public PlayerData() {

    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {

        return name;
    }

    public long getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }
}
