
package com.udaan18.udaan18.android.model.eventCategory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Container {

    @SerializedName("tech")
    @Expose
    private List<Tech> tech = null;
    @SerializedName("nonTech")
    @Expose
    private List<NonTech> nonTech = null;
    @SerializedName("cultural")
    @Expose
    private List<Cultural> cultural = null;

    public List<Tech> getTech() {
        return tech;
    }

    public void setTech(List<Tech> tech) {
        this.tech = tech;
    }

    public List<NonTech> getNonTech() {
        return nonTech;
    }

    public void setNonTech(List<NonTech> nonTech) {
        this.nonTech = nonTech;
    }

    public List<Cultural> getCultural() {
        return cultural;
    }

    public void setCultural(List<Cultural> cultural) {
        this.cultural = cultural;
    }

}
