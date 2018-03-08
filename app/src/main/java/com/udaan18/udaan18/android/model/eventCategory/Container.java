
package com.udaan18.udaan18.android.model.eventCategory;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Container {

    @SerializedName("technical")
    @Expose
    private List<Department> departments = null;
    @SerializedName("adventure")
    @Expose
    private List<Event> adventure = null;
    @SerializedName("nonTechnical")
    @Expose
    private List<Event> nonTech = null;
    @SerializedName("cultural")
    @Expose
    private List<Event> cultural = null;
    @SerializedName("girls")
    @Expose
    private List<Event> girls = null;
    @SerializedName("treasureHunt")
    @Expose
    private List<Event> treasureHunt = null;

    public static Container getInstance(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Container.class);
    }

    public List<Event> getAdventure() {
        return adventure;
    }

    public void setAdventure(List<Event> adventure) {
        this.adventure = adventure;
    }

    public List<Event> getGirls() {
        return girls;
    }

    public void setGirls(List<Event> girls) {
        this.girls = girls;
    }

    public List<Event> getTreasureHunt() {
        return treasureHunt;
    }

    public void setTreasureHunt(List<Event> treasureHunt) {
        this.treasureHunt = treasureHunt;
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
