
package com.udaan18.udaan18.android.model.eventCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Manager {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
