package com.example.csse;

import com.google.gson.annotations.SerializedName;

public class LoginResults {

    private String name;

    @SerializedName("email")
    private  String email;

    private String is_change;

    private String type;

    public String getName() {
        return name;
    }

    public String getIs_change() {
        return is_change;
    }

    public void setIs_change(String is_change) {
        this.is_change = is_change;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
