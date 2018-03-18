package com.udaan18.udaan18.android.model.eventCategory;

/**
 * Created by jack on 17-03-2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionCheck {

    @SerializedName("app_version")
    @Expose
    private Integer appVersion;
    @SerializedName("data_version")
    @Expose
    private Integer dataVersion;

    public Integer getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(Integer appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
    }

}
