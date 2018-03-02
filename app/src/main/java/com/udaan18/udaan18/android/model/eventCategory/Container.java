
package com.udaan18.udaan18.android.model.eventCategory;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Container {

    @SerializedName("tech")
    @Expose
    private List<Department> departments = null;
    @SerializedName("nonTech")
    @Expose
    private List<Event> nonTech = null;
    @SerializedName("cultural")
    @Expose
    private List<Event> cultural = null;

    public static Container getInstance(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Container.class);
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Event> getNonTech() {
        return nonTech;
    }

    public void setNonTech(List<Event> nonTech) {
        this.nonTech = nonTech;
    }

    public List<Event> getCultural() {
        return cultural;
    }

    public void setCultural(List<Event> cultural) {
        this.cultural = cultural;
    }

    @Override
    public String toString() {
        return (new Gson()).toJson(this);
    }
}
